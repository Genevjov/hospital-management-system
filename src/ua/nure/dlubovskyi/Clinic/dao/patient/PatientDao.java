
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
		Connection connection = ConnectingPool.getConnection();
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

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return patients;
	}

	/**
	 * Adding patient to database
	 * 
	 * @param patient
	 */
	public static void addPatient(Patient patient) {
		LOGGER.debug("Adding new patient.");

		Connection connection = ConnectingPool.getConnection();
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
			resultSet.close();
			preparedStatement.close();
			LOGGER.info("Patient with id:" + id + " has been added");
		} catch (SQLException e) {
			LOGGER.error(e.getLocalizedMessage());
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
		LOGGER.debug("Getting procedure by id: " + id);
		List<Procedure> procedures = new ArrayList<>();
		try (Connection connection = ConnectingPool.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(Query.SQL_GET_PROCEDURES_BY_PATIENT_ID);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Doctor doctor = DoctorManager.getDoctorById(resultSet.getInt("doctor_id"));
				Treatmant treatmant = DoctorManager.geTreatmantById(resultSet.getInt("treatment_id"));
				Procedure procedure = new Procedure(resultSet.getInt("procedure_id"), doctor,
						resultSet.getString("info"), treatmant, resultSet.getInt("is_done"));
				procedures.add(procedure);
			}
			resultSet.close();
			preparedStatement.close();
		} catch (SQLException e) {
			LOGGER.error(e.getLocalizedMessage());
		}
		return procedures;
	}

	/**
	 * Method for getting patient entity form database by id
	 * 
	 * @param id
	 * @return
	 */
	public static Patient getPatientById(int id) {
		LOGGER.debug("Getting patient by id: " + id);

		Patient patient = null;
		Connection connection = ConnectingPool.getConnection();
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
			resultSet.close();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return patient;

	}

	/**
	 * Method for getting sorted list of Patient entity by sort param if it valid,
	 * else result of method which returns all patients will bee returned.
	 * 
	 * @param sortParam
	 * @return
	 */
	public static List<Patient> getPatientsSorted(String sortParam) {
		String query = null;
		switch (sortParam) {
		case "firstNameUp":
			query = Query.SQL_GET_PATIENTS_SORT_BY_NAME_UP;
			break;
		case "firstNameDown":
			query = Query.SQL_GET_PATIENTS_SORT_BY_NAME_DOWN;
			break;
		case "secondNameUp":
			query = Query.SQL_GET_PATIENTS_SORT_BY_SECOND_NAME_UP;
			break;
		case "secondNameDown":
			query = Query.SQL_GET_PATIENTS_SORT_BY_SECOND_NAME_DOWN;
			break;
		case "ageUp":
			query = Query.SQL_GET_PATIENTS_SORT_BY_AGE_UP;
			break;
		case "ageDown":
			query = Query.SQL_GET_PATIENTS_SORT_BY_AGE_DOWN;
			break;
		case "diagnosisUp":
			query = Query.SQL_GET_PATIENTS_SORT_BY_DIAGNOSIS_UP;
			break;
		case "diagnosisDown":
			query = Query.SQL_GET_PATIENTS_SORT_BY_DIAGNOSIS_DOWN;
			break;
		default:
			return getAllPatients();
		}
		Connection connection = ConnectingPool.getConnection();
		List<Patient> patients = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Patient patient = new Patient(resultSet.getString("first_name"), resultSet.getString("second_name"),
						resultSet.getString("diagnosis_name"), resultSet.getInt("patient_id"),
						resultSet.getString("date_of_birth"));
				patient.setDoctor(DoctorManager.getDoctorById(resultSet.getInt("doctor_id")));
				patients.add(patient);
			}
			LOGGER.debug("Found: " + patients.size() + " patients");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return patients;
	}

	/**
	 * Method for patients discharging
	 * 
	 * @param patientId
	 */
	public static void dischargePatient(int patientId) {
		Connection connection = ConnectingPool.getConnection();
		LOGGER.debug("Descharge patient with login:" + patientId);
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(Query.SQL_DELETE_PATIENT);
			preparedStatement.setInt(1, patientId);
			preparedStatement.executeUpdate();
			preparedStatement = connection.prepareStatement(Query.SQL_DELETE_PROC);
			preparedStatement.setInt(1, patientId);
			preparedStatement.executeUpdate();
			LOGGER.debug("Patient with login:" + patientId + "has been discharged");

		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		} finally {

			closeConnection(connection);
		}
	}

}
