package ua.nure.dlubovskyi.Clinic.dao.patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.dlubovskyi.Clinic.constants.Query;
import ua.nure.dlubovskyi.Clinic.dao.ConnectingPool;
import ua.nure.dlubovskyi.Clinic.entity.managers.DoctorManager;
import ua.nure.dlubovskyi.Clinic.entity.patient.Patient;
import ua.nure.dlubovskyi.Clinic.entity.patient.Procedure;
import ua.nure.dlubovskyi.Clinic.entity.patient.Treatmant;
import ua.nure.dlubovskyi.Clinic.entity.staff.Doctor;
import ua.nure.dlubovskyi.Clinic.tags.PatientInfoTag;

/**
 * 
 * @author Dlubovskyi Oleg
 *
 */
public class PatientDao {
	public final static Logger LOGGER = Logger.getLogger(PatientDao.class);
	private static Connection connection;

	/**
	 * Getiing all patients from database
	 * 
	 * @see Patient
	 * @return list of patient entity
	 * 
	 */
	public static List<Patient> getAllPatients() {
		LOGGER.debug("Getting patients list");
		List<Patient> patients = new ArrayList<>();
		connection = ConnectingPool.getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(Query.SQL_GET_ALL_PATIENTS)) {
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Patient patient = new Patient(resultSet.getString("first_name"), resultSet.getString("second_name"),
						resultSet.getString("diagnosis_name"), resultSet.getInt("patient_id"),
						resultSet.getString("date_of_birth"));
				patient.setDoctor(DoctorManager.getDoctorById(resultSet.getInt("doctor_id")));
				patients.add(patient);
			}
			LOGGER.debug("Found: " + patients.size() + " patients");
			closeConnection(connection);
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return patients;
	}

	/**
	 * Adding patient to database
	 * 
	 * @param patient
	 */
	public static void addPatient(Patient patient) {
		connection = ConnectingPool.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(Query.SQL_ADD_NEW_PATIENT);
			preparedStatement.setString(1, patient.getFirstName());
			preparedStatement.setString(2, patient.getSecondName());
			preparedStatement.setString(3, patient.getDateOfBirth());
			preparedStatement.executeUpdate();
			preparedStatement = connection.prepareStatement(Query.SQL_GET_LAST_INDEX);
			ResultSet resultSet = preparedStatement.executeQuery();
			int id = 0;
			while (resultSet.next()) {
				id = resultSet.getInt("id");
			}
			preparedStatement = connection.prepareStatement(Query.SQL_SET_DOCTOR_TO_PATIENT);
			preparedStatement.setInt(1, id);
			preparedStatement.setInt(2, patient.getDoctorId());
			preparedStatement.executeUpdate();
			closeConnection(connection);
			resultSet.close();
			preparedStatement.close();
			LOGGER.info("Patient with id:" + id + " has been added");
		} catch (SQLException e) {
			LOGGER.error(e.getLocalizedMessage());
		}
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
	 * Getting all procedures by patient id
	 * 
	 * @see Treatmant
	 * @see Patient
	 * @see PatientInfoTag
	 * @see Procedure
	 * @param id
	 * @return list of procedure entity
	 */
	public static List<Procedure> getProceduresByPatientId(int id) {
		List<Procedure> procedures = new ArrayList<>();
		connection = ConnectingPool.getConnection();
		try (PreparedStatement preparedStatement = connection
				.prepareStatement(Query.SQL_GET_PROCEDURES_BY_PATIENT_ID)) {
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Doctor doctor = DoctorManager.getDoctorById(resultSet.getInt("doctor_id"));
				Treatmant treatmant = DoctorManager.geTreatmantById(resultSet.getInt("treatmant_id"));
				Procedure procedure = new Procedure(doctor, resultSet.getString("info"), treatmant,
						resultSet.getInt("is_done"));
				procedures.add(procedure);
			}
			closeConnection(connection);
			resultSet.close();
		} catch (SQLException e) {
			LOGGER.error(e.getLocalizedMessage());
			e.printStackTrace();
		}
		return procedures;
	}

	public static Patient getPatientById(int id) {
		Patient patient = null;
		connection = ConnectingPool.getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(Query.SQL_GET_PATIENT_BY_ID)) {
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				patient = new Patient(resultSet.getString("first_name"), resultSet.getString("second_name"),
						resultSet.getString("diagnosis_name"), resultSet.getInt("patient_id"),
						resultSet.getString("date_of_birth"));
				patient.setDoctor(DoctorManager.getDoctorById(resultSet.getInt("doctor_id")));
			}
			LOGGER.debug("Found: " + patient.getPatientId());
			closeConnection(connection);
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return patient;

	}
}
