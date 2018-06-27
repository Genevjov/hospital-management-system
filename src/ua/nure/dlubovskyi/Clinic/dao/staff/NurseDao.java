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
import ua.nure.dlubovskyi.Clinic.entity.patient.Patient;
import ua.nure.dlubovskyi.Clinic.entity.patient.Procedure;
import ua.nure.dlubovskyi.Clinic.entity.patient.Treatmant;
import ua.nure.dlubovskyi.Clinic.entity.staff.Nurse;
import ua.nure.dlubovskyi.Clinic.entity.staff.Staff;

/**
 * 
 * @author Dlubovskyi Oleg
 *
 */
public class NurseDao {
	private static final Logger LOGGER = Logger.getLogger(NurseDao.class);

	// db connection
	private static Connection connection;

	/**
	 * Getting list of Ners entity
	 * 
	 * @see Nurse
	 * @return Nurse entity list
	 */
	public static List<Nurse> getAllNurses() {
		LOGGER.debug("Obtain all nurses");
		connection = ConnectingPool.getConnection();
		List<Nurse> nurses = new ArrayList<>();
		try (PreparedStatement preparedStatement = connection.prepareStatement(Query.SQL_GET_ALL_NURSES)) {
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Nurse nurse = new Nurse(resultSet.getString("first_Name"), resultSet.getString("second_name"),
						resultSet.getString("login"));
				StaffDao.setLoginDataByStaffId(nurse, resultSet.getInt("staff_id"));
				nurses.add(nurse);
			}
			LOGGER.debug("Found: " + nurses.size() + " nurses");

			resultSet.close();
		} catch (SQLException e) {
			LOGGER.error(e.getLocalizedMessage());
		} finally {
			closeConnection(connection);
		}
		return nurses;
	}

	/**
	 * 
	 * @param sortParam
	 * @return -sorted list by sortParam
	 */
	public static List<Nurse> getSortedNurseList(String sortParam) {
		LOGGER.debug("Sorting nurses by: " + sortParam);
		String query = null;
		switch (sortParam) {
		case "firstNameUp":
			query = Query.SQL_GET_NURSES_SORTED_BY_NAME_UP;
			break;
		case "firstNameDown":
			query = Query.SQL_GET_NURSES_SORTED_BY_NAME_DOWN;
			break;
		case "secondNameUp":
			query = Query.SQL_GET_NURSES_SORTED_BY_SECOND_NAME_UP;
			break;
		case "secondNameDown":
			query = Query.SQL_GET_NURSES_SORTED_BY_SECOND_NAME_DOWN;
			break;
		case "loginUp":
			query = Query.SQL_GET_NUSRES_SORTED_BY_LOGIN_UP;
			break;
		case "loginDown":
			query = Query.SQL_GET_NURSES_SORTED_BY_LOGIN_DOWN;
			break;
		default:
			return getAllNurses();
		}
		connection = ConnectingPool.getConnection();
		List<Nurse> nurses = new ArrayList<>();
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Nurse nurse = new Nurse(resultSet.getString("first_Name"), resultSet.getString("second_name"),
						resultSet.getString("login"));
				StaffDao.setLoginDataByStaffId(nurse, resultSet.getInt("staff_id"));
				nurses.add(nurse);
			}
			LOGGER.debug("Sorted");
			resultSet.close();
		} catch (SQLException e) {
			LOGGER.error(e.getLocalizedMessage());
		} finally {
			closeConnection(connection);
		}
		return nurses;
	}

	/**
	 * Method for adding new nurse to database
	 * 
	 * @param nurse
	 */
	public static void addNewNurse(Staff nurse) {
		connection = ConnectingPool.getConnection();
		try {
			LOGGER.debug("Adding staff data for new nurse");
			PreparedStatement ps = connection.prepareStatement(Query.SQL_ADD_NURSE);
			ps.setString(1, nurse.getFirstName());
			ps.setString(2, nurse.getSecondName());
			ps.executeUpdate();
			// obtain this staff id
			ps = connection.prepareStatement(Query.SQL_GET_LAST_INDEX);
			ResultSet resultSet = ps.executeQuery();
			int id = 0;
			while (resultSet.next()) {
				id = resultSet.getInt("id");
			}
			LOGGER.debug("New Nurse has been added with id: " + id);
			// setting login data
			LOGGER.debug("Adding login data for new Nurse");
			ps = connection.prepareStatement(Query.SQL_ADD_LOGIN_DATA);
			ps.setInt(1, id);
			ps.setString(2, nurse.getLogin());
			ps.setString(3, nurse.getPassword());
			ps.executeUpdate();
			resultSet.close();
			ps.close();
		} catch (SQLException e) {
			LOGGER.error("Failed to add new nurse.");
		} finally {
			closeConnection(connection);
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
	 * Getting patients for nurse
	 * 
	 * @return -list of Patient entity
	 */
	public static List<Patient> getPatientsForNurse() {
		connection = ConnectingPool.getConnection();
		List<Patient> patients = new ArrayList<>();
		LOGGER.debug("Obtain patients for nurse");
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(Query.SQL_GET_PATIENTS_FOR_NURSE);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Patient patient = new Patient(resultSet.getString("first_name"), resultSet.getString("second_name"),
						resultSet.getString("diagnosis_name"), resultSet.getInt("patient_id"),
						resultSet.getString("date_of_birth"));
				patient.setProcedure(new Procedure(resultSet.getInt("procedure_id"), null, resultSet.getString("info"),
						new Treatmant(resultSet.getInt("treatment_id"), resultSet.getString("treatment_name")), 0));
				patients.add(patient);
			}
			LOGGER.debug("Found: " + patients.size());

		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		} finally {
			closeConnection(connection);
		}
		return patients;
	}

}
