package ua.nure.dlubovskyi.Clinic.dao.staff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.dlubovskyi.Clinic.constants.Query;
import ua.nure.dlubovskyi.Clinic.dao.ConnectingPool;
import ua.nure.dlubovskyi.Clinic.entity.patient.Treatmant;
import ua.nure.dlubovskyi.Clinic.entity.staff.Doctor;

/**
 * 
 * @author Dlubovskyi Oleg
 *
 */
public class DoctorDao {
	private static final Logger LOGGER = Logger.getLogger(DoctorDao.class);
	private static Connection connection;

	/**
	 * Method for getting
	 * 
	 * @see Doctor
	 * @return list with doctor obj
	 */
	public static List<Doctor> getAllDoctors() {
		List<Doctor> doctors = new ArrayList<>();
		LOGGER.debug("Getting connection");
		connection = ConnectingPool.getConnection();
		LOGGER.debug("Connected!");
		try (PreparedStatement preparedStatement = connection.prepareStatement(Query.SQL_GET_ALL_DOCTORS)) {
			ResultSet resultSet = preparedStatement.executeQuery();
			LOGGER.debug("Executing query");
			while (resultSet.next()) {
				Specialization specialization = new Specialization(resultSet.getInt("specialization_id"),
						resultSet.getString("specialization_name"));
				Doctor doctor = new Doctor(resultSet.getString("first_name"), resultSet.getString("second_name"),
						specialization);
				doctor.setId(resultSet.getInt("staff_id"));
				doctor.setDocId(resultSet.getInt("doctor_id"));
				doctors.add(doctor);
			}
			LOGGER.debug("Query has been executed.");
			LOGGER.info(doctors.size() + " doctors found.");
			closeConnection(connection);
			resultSet.close();
		} catch (SQLException e) {
			LOGGER.error(e.getLocalizedMessage());
		}

		return doctors;
	}

	/**
	 * Util meth for close connection.
	 * 
	 * @param connection
	 */
	private static void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			LOGGER.error("Close connection error.");
		}
		LOGGER.debug("Connection has been closed.");

	}

	/**
	 * Getting specification from database as list.
	 * 
	 * @see Specialization
	 * @return list with all existing specifications
	 */
	public static List<Specialization> getAllSpecializations() {
		List<Specialization> specializations = new ArrayList<>();
		LOGGER.debug("Getting connection");
		connection = ConnectingPool.getConnection();
		LOGGER.debug("Connected!");
		try (PreparedStatement preparedStatement = connection.prepareStatement(Query.SQL_GET_ALL_SPECIALIZATIONS)) {
			ResultSet resultSet = preparedStatement.executeQuery();
			LOGGER.debug("Executing query");
			while (resultSet.next()) {
				specializations.add(new Specialization(resultSet.getInt("specialization_id"),
						resultSet.getString("specialization_name")));
			}
			resultSet.close();
			closeConnection(connection);
		} catch (SQLException e) {
			LOGGER.error(e.getLocalizedMessage());
		}
		return specializations;
	}

	/**
	 * Method that returns doctors list for each specialization
	 * 
	 * @param id
	 * @return doctors List
	 */
	public static List<Doctor> getDoctorsBySpecId(int id) {
		List<Doctor> doctors = new ArrayList<>();
		connection = ConnectingPool.getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(Query.SQL_GET_DOCTORS_BY_SPEC_ID)) {
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Doctor doctor = new Doctor(resultSet.getString("first_name"), resultSet.getString("second_name"),
						new Specialization(resultSet.getInt("specialization_id"),
								resultSet.getString("specialization_name")));
				doctor.setId(resultSet.getInt("staff_id"));
				doctor.setLogin(resultSet.getString("login"));
				doctors.add(doctor);
			}
			closeConnection(connection);
			resultSet.close();
		} catch (SQLException e) {
			LOGGER.error(e.getLocalizedMessage());
		}
		return doctors;

	}

	/**
	 * Add new doctor method
	 * 
	 * @param doctor
	 */
	public static void addDoctor(Doctor doctor) {

		connection = ConnectingPool.getConnection();
		LOGGER.info("Adding new doctor.");
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(Query.SQL_ADD_STAFF_INFO);
			LOGGER.debug("Adding staff data to doctor");
			preparedStatement.setString(1, doctor.getFirstName());
			preparedStatement.setString(2, doctor.getSecondName());
			preparedStatement.setInt(3, 2);
			preparedStatement.executeUpdate();
			// obtain doc id
			preparedStatement = connection.prepareStatement(Query.SQL_GET_LAST_INDEX);
			ResultSet resultSet = preparedStatement.executeQuery();
			int id = 0;
			while (resultSet.next()) {
				id = resultSet.getInt("id");
			}
			connection.setAutoCommit(false);
			// setting doc info
			try {
				LOGGER.debug("Adding doctor's info to doctor");
				preparedStatement = connection.prepareStatement(Query.SQL_ADD_DOC_INFO);
				preparedStatement.setInt(1, id);
				preparedStatement.setInt(2, doctor.getSpecialization().getId());
				preparedStatement.executeUpdate();
			} catch (SQLException e) {
				LOGGER.error("Transaction falied: " + e.getMessage());
				connection.rollback();
			}
			// setting login data

			try {
				LOGGER.debug("Adding staff login data");

				preparedStatement = connection.prepareStatement(Query.SQL_ADD_LOGIN_DATA);
				preparedStatement.setInt(1, id);
				preparedStatement.setString(2, doctor.getLogin());
				preparedStatement.setString(3, doctor.getPassword());
				preparedStatement.executeUpdate();
			} catch (SQLException e) {
				LOGGER.error("Transaction falied: " + e.getMessage());
				connection.rollback();
			}
			connection.commit();
			closeConnection(connection);
			resultSet.close();
			preparedStatement.close();
			LOGGER.debug("Add doctor transaction has been commited.");
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}
	}

	public static Specialization getSpecByName(String name) {
		Specialization specialization = null;
		LOGGER.debug("Getting id of specialization: " + name);
		connection = ConnectingPool.getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(Query.SQL_GET_SPEC_ID_BY_NAME)) {
			preparedStatement.setString(1, name);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				specialization = new Specialization(resultSet.getInt("specialization_id"), name);
			}
			resultSet.close();
			closeConnection(connection);
		} catch (SQLException e) {
			LOGGER.error("Failed to get specialization id by name: " + name + " " + e.getLocalizedMessage());
		}
		return specialization;
	}

	public static Doctor getDoctorById(int doctorId) {
		connection = ConnectingPool.getConnection();
		Doctor doctor = null;
		try (PreparedStatement preparedStatement = connection.prepareStatement(Query.SQL_GET_DOCTOR_BY_ID)) {
			preparedStatement.setInt(1, doctorId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				doctor = new Doctor(resultSet.getString("first_name"), resultSet.getString("second_name"),
						new Specialization(resultSet.getInt("specialization_id"),
								resultSet.getString("specialization_name")));
				doctor.setDocId(doctorId);

			}
			resultSet.close();
			closeConnection(connection);
		} catch (SQLException e) {
			LOGGER.error(e.getLocalizedMessage());
		}
		return doctor;
	}

	/**
	 * Get treatmant entity by id
	 * 
	 * @param id
	 * @return Treatmant entity
	 */
	public static Treatmant getTreatmantById(int id) {
		connection = ConnectingPool.getConnection();
		Treatmant treatmant = null;
		try (PreparedStatement preparedStatement = connection.prepareStatement(Query.SQL_GET_TREATMENT_BY_ID)) {
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				treatmant = new Treatmant(resultSet.getInt("treatment_id"), resultSet.getString("treatmant_name"));
			}
			closeConnection(connection);
			resultSet.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}
		return treatmant;
	}
}
