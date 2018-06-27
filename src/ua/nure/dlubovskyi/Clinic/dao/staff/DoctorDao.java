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
import ua.nure.dlubovskyi.Clinic.entity.managers.PatientManager;
import ua.nure.dlubovskyi.Clinic.entity.managers.StaffManager;
import ua.nure.dlubovskyi.Clinic.entity.patient.Patient;
import ua.nure.dlubovskyi.Clinic.entity.patient.Procedure;
import ua.nure.dlubovskyi.Clinic.entity.patient.Treatmant;
import ua.nure.dlubovskyi.Clinic.entity.staff.Doctor;
import ua.nure.dlubovskyi.Clinic.entity.staff.Specialization;

/**
 * @author Dlubovskyi Oleg
 *
 */
public class DoctorDao {
	private static final Logger LOGGER = Logger.getLogger(DoctorDao.class);

	/**
	 * Method for getting
	 * 
	 * @see Doctor
	 * @return list with doctor obj
	 */
	public List<Doctor> getAllDoctors() {
		List<Doctor> doctors = new ArrayList<>();
		LOGGER.debug("Getting connection");
		Connection connection = ConnectingPool.getConnection();
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
				doctor.setPatientCount(getCountPatientsByDoctorId(doctor.getDocId()));
				doctor.setLogin(resultSet.getString("login"));
				doctor.setPassword(resultSet.getString("password"));
				doctors.add(doctor);
			}
			LOGGER.debug("Query has been executed.");
			LOGGER.info(doctors.size() + " doctors found.");
		} catch (SQLException e) {
			LOGGER.error(e.getLocalizedMessage());
		} finally {
			closeConnection(connection);

		}
		return doctors;
	}

	/**
	 * Util meth for close connection.
	 * 
	 * @param connection
	 */
	private void closeConnection(Connection connection) {
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
	public List<Specialization> getAllSpecializations() {
		List<Specialization> specializations = new ArrayList<>();
		LOGGER.debug("Getting connection");
		Connection connection = ConnectingPool.getConnection();
		LOGGER.debug("Connected!");
		try (PreparedStatement preparedStatement = connection.prepareStatement(Query.SQL_GET_ALL_SPECIALIZATIONS)) {
			ResultSet resultSet = preparedStatement.executeQuery();
			LOGGER.debug("Executing query");
			while (resultSet.next()) {
				specializations.add(new Specialization(resultSet.getInt("specialization_id"),
						resultSet.getString("specialization_name")));
			}
		} catch (SQLException e) {
			LOGGER.error(e.getLocalizedMessage());
		} finally {
			closeConnection(connection);
		}
		return specializations;
	}

	/**
	 * Method that returns doctors list for each specialization
	 * 
	 * @param id
	 * @return doctors List
	 */
	public List<Doctor> getDoctorsBySpecId(int id) {
		List<Doctor> doctors = new ArrayList<>();
		Connection connection = ConnectingPool.getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(Query.SQL_GET_DOCTORS_BY_SPEC_ID)) {
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Doctor doctor = new Doctor(resultSet.getString("first_name"), resultSet.getString("second_name"),
						new Specialization(resultSet.getInt("specialization_id"),
								resultSet.getString("specialization_name")));
				doctor.setId(resultSet.getInt("staff_id"));
				doctor.setLogin(resultSet.getString("login"));
				doctor.setDocId(resultSet.getInt("doctor_id"));
				doctor.setPatientCount(getCountPatientsByDoctorId(doctor.getDocId()));
				doctors.add(doctor);
			}
		} catch (SQLException e) {
			LOGGER.error(e.getLocalizedMessage());
		} finally {
			closeConnection(connection);
		}
		return doctors;
	}

	/**
	 * Add new doctor method
	 * 
	 * @param doctor
	 */
	public void addDoctor(Doctor doctor) {

		Connection connection = ConnectingPool.getConnection();
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

			LOGGER.debug("Add doctor transaction has been commited.");
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		} finally {
			closeConnection(connection);
		}
	}

	/**
	 * Method for specialization entity by name
	 * 
	 * @see Specialization
	 * @param name
	 * @return specialization entity
	 */
	public Specialization getSpecByName(String name) {
		LOGGER.debug("Getting specialization by name: " + name);

		Specialization specialization = null;
		LOGGER.debug("Getting id of specialization: " + name);
		Connection connection = ConnectingPool.getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(Query.SQL_GET_SPEC_ID_BY_NAME)) {
			preparedStatement.setString(1, name);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				specialization = new Specialization(resultSet.getInt("specialization_id"), name);
			}
		} catch (SQLException e) {
			LOGGER.error("Failed to get specialization id by name: " + name + " " + e.getLocalizedMessage());
		} finally {
			closeConnection(connection);
		}
		return specialization;
	}

	/**
	 * Method for doctor entity by name
	 * 
	 * @see Doctor
	 * @param doctorId
	 * @return doctor entity
	 */
	public Doctor getDoctorById(int doctorId) {
		LOGGER.debug("Getting doctor bu id: " + doctorId);
		Connection connection = ConnectingPool.getConnection();
		Doctor doctor = null;
		try (PreparedStatement preparedStatement = connection.prepareStatement(Query.SQL_GET_DOCTOR_BY_ID)) {
			preparedStatement.setInt(1, doctorId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				doctor = new Doctor(resultSet.getString("first_name"), resultSet.getString("second_name"),
						new Specialization(resultSet.getInt("specialization_id"),
								resultSet.getString("specialization_name")));
				doctor.setDocId(doctorId);
				StaffManager.setLoginData(doctor, resultSet.getInt("staff_id"));
			}
		} catch (SQLException e) {
			LOGGER.error(e.getLocalizedMessage());
		} finally {
			closeConnection(connection);
		}
		return doctor;
	}

	/**
	 * Get treatmant entity by id
	 * 
	 * @param id
	 * @return Treatmant entity
	 */
	public Treatmant getTreatmantById(int id) {
		LOGGER.debug("Getting treatment by ib: " + id);

		Connection connection = ConnectingPool.getConnection();
		Treatmant treatmant = null;
		try (PreparedStatement preparedStatement = connection.prepareStatement(Query.SQL_GET_TREATMENT_BY_ID)) {
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				treatmant = new Treatmant(resultSet.getInt("treatment_id"), resultSet.getString("treatment_name"));
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		} finally {
			closeConnection(connection);
		}
		return treatmant;
	}

	/**
	 * Method for getting all patients by doctor id for database
	 * 
	 * @param id
	 * @return list of patient entity
	 */
	public List<Patient> getPatientsByDoctorId(int id) {
		Connection connection = ConnectingPool.getConnection();
		LOGGER.debug("Getting patients by doctor id: " + id);
		List<Patient> patients = new ArrayList<>();
		try (PreparedStatement preparedStatement = connection.prepareStatement(Query.SQL_GET_PATIENTS_BY_DOCTOR_ID)) {
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				patients.add(PatientManager.getPatientById(resultSet.getInt("patient_id")));
			}
			LOGGER.trace("Found: " + patients.size() + "for doctor id: " + id);
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		} finally {
			closeConnection(connection);
		}
		return patients;
	}

	/**
	 * Method for getting patients count for doctor
	 * 
	 * @param id
	 *            -doctor id for patients search
	 * @return
	 */
	public int getCountPatientsByDoctorId(int id) {
		Connection connection = ConnectingPool.getConnection();
		LOGGER.debug("Getting patients count by doctor id: " + id);
		int result = 0;
		try (PreparedStatement preparedStatement = connection.prepareStatement(Query.SQL_COUNT_PATIENTS_BY_DOCTOR_ID)) {
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				result = resultSet.getInt("COUNT(*)");
			}
			LOGGER.trace("Found: " + result + "for doctor id: " + id);
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		} finally {
			closeConnection(connection);
		}
		return result;
	}

	/**
	 * Obtain sorted list of Doctor entity
	 * 
	 * @param option
	 * @return list of doctors
	 */
	public List<Doctor> getAllDoctorsSorted(String option) {
		LOGGER.debug("Sorting doctors");
		String query = null;
		switch (option) {
		case "firstNameUp":
			query = Query.SQL_GET_DOCTORS_SORTED_BY_NAME_UP;
			break;
		case "firstNameDown":
			query = Query.SQL_GET_DOCTORS_SORTED_BY_NAME_DOWN;
			break;
		case "secondNameUp":
			query = Query.SQL_GET_DOCTORS_SORTED_BY_SECOND_NAME_UP;
			break;
		case "secondNameDown":
			query = Query.SQL_GET_DOCTORS_SORTED_BY_SECOND_NAME_DOWN;
			break;
		case "loginUp":
			query = Query.SQL_GET_DOCTORS_SORTED_BY_LOGIN_UP;
			break;
		case "loginDown":
			query = Query.SQL_GET_DOCTORS_SORTED_BY_LOGIN_DOWN;
			break;
		default:
			return getAllDoctors();
		}
		Connection connection = ConnectingPool.getConnection();
		List<Doctor> doctors = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			LOGGER.debug("Executing query");
			while (resultSet.next()) {
				Specialization specialization = new Specialization(resultSet.getInt("specialization_id"),
						resultSet.getString("specialization_name"));
				Doctor doctor = new Doctor(resultSet.getString("first_name"), resultSet.getString("second_name"),
						specialization);
				doctor.setId(resultSet.getInt("staff_id"));
				doctor.setDocId(resultSet.getInt("doctor_id"));
				doctor.setPatientCount(getCountPatientsByDoctorId(doctor.getDocId()));
				doctor.setLogin("login");
				doctor.setPassword("password");
				doctors.add(doctor);
			}
			LOGGER.debug("Query has been executed.");
			LOGGER.info(doctors.size() + " doctors found.");
		} catch (SQLException e) {
			LOGGER.error(e.getLocalizedMessage());
		} finally {
			closeConnection(connection);

		}
		return doctors;
	}

	/**
	 * Obtain sorted list of doctors bu specialization
	 * 
	 * @param id
	 * @param option
	 * @return list of sorted doctors bu specialization
	 */
	public List<Doctor> getDoctorsBySpecSorted(int id, String option) {
		String query = null;
		LOGGER.debug("Sorting doctors by spce id: " + id);
		switch (option) {
		case "firstNameUp":
			query = Query.SQL_SPC_GET_DOCTORS_SORTED_BY_NAME_UP;
			break;
		case "firstNameDown":
			query = Query.SQL_SPC_GET_DOCTORS_SORTED_BY_NAME_DOWN;
			break;
		case "secondNameUp":
			query = Query.SQL_GET_SPC_DOCTORS_SORTED_BY_SECOND_NAME_UP;
			break;
		case "secondNameDown":
			query = Query.SQL_GET_SPC_DOCTORS_SORTED_BY_SECOND_NAME_DOWN;
			break;
		case "loginUp":
			query = Query.SQL_GET_SPC_DOCTORS_SORTED_BY_LOGIN_UP;
			break;
		case "loginDown":
			query = Query.SQL_GET_SPC_DOCTORS_SORTED_BY_LOGIN_DOWN;
			break;
		default:
			return getDoctorsBySpecId(id);
		}
		Connection connection = ConnectingPool.getConnection();
		List<Doctor> doctors = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			LOGGER.debug("Executing query");
			while (resultSet.next()) {
				Specialization specialization = new Specialization(resultSet.getInt("specialization_id"),
						resultSet.getString("specialization_name"));
				Doctor doctor = new Doctor(resultSet.getString("first_name"), resultSet.getString("second_name"),
						specialization);
				doctor.setId(resultSet.getInt("staff_id"));
				doctor.setDocId(resultSet.getInt("doctor_id"));
				doctor.setPatientCount(getCountPatientsByDoctorId(doctor.getDocId()));
				doctor.setLogin("login");
				doctor.setPassword("password");
				doctors.add(doctor);
			}
			LOGGER.debug("Query has been executed.");
			LOGGER.info(doctors.size() + " doctors found.");
		} catch (SQLException e) {
			LOGGER.error(e.getLocalizedMessage());
		} finally {
			closeConnection(connection);

		}
		return doctors;
	}

	/**
	 * Obtain doctor by id
	 * 
	 * @param id
	 * @return Doctor entity
	 */
	public int getDoctorIdByStaffId(int id) {
		LOGGER.debug("Obtain doctor by staff id: " + id);
		Connection connection = ConnectingPool.getConnection();
		int docId = 0;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(Query.SQL_GET_DOC_ID_BY_STAFF_ID);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				docId = resultSet.getInt("doctor_id");
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		} finally {
			closeConnection(connection);

		}
		return docId;
	}

	/**
	 * Obtan all traetment
	 * 
	 * @return -list of Treatment entity
	 */
	public List<Treatmant> getAllTreatmants() {
		LOGGER.debug("Obtian treatment list");
		Connection connection = ConnectingPool.getConnection();
		List<Treatmant> treatmants = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(Query.SQL_GET_ALL_TREATMENTS);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Treatmant treatmant = new Treatmant(resultSet.getInt("treatment_id"),
						resultSet.getString("treatment_name"));
				treatmants.add(treatmant);
			}
			LOGGER.debug("Found: " + treatmants.size());
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		} finally {
			closeConnection(connection);
		}
		return treatmants;
	}

	/**
	 * Setting patient to doctor
	 * 
	 * @param doctorId
	 * @param patientId
	 * @param treatmentId
	 * @param info
	 */
	public void setProcToPatient(int doctorId, int patientId, int treatmentId, String info) {
		LOGGER.debug("Setting patient " + patientId + " to doctor " + doctorId);
		Connection connection = ConnectingPool.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(Query.SQL_SET_PROC_TO_PATIENT);
			preparedStatement.setInt(1, doctorId);
			preparedStatement.setInt(2, patientId);
			preparedStatement.setInt(3, treatmentId);
			preparedStatement.setString(4, info);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		} finally {
			closeConnection(connection);
		}
	}

	/**
	 * Setting diagnosis to patient
	 * 
	 * @param doctorId
	 * @param patientId
	 * @param diagnosis
	 */
	public void setDiagnosis(int doctorId, int patientId, String diagnosis) {
		LOGGER.debug("Setting diagnosis to " + patientId);
		Connection connection = ConnectingPool.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(Query.SQL_CREATE_DIAGNOSIS);
			preparedStatement.setString(1, diagnosis);
			preparedStatement.executeUpdate();
			int id = 0;
			preparedStatement = connection.prepareStatement(Query.SQL_GET_LAST_INDEX);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				id = resultSet.getInt("id");
			}
			preparedStatement = connection.prepareStatement(Query.SQL_SET_DIAGNOSIS);
			preparedStatement.setInt(1, id);
			preparedStatement.setInt(2, doctorId);
			preparedStatement.setInt(3, patientId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		} finally {
			closeConnection(connection);
		}
	}

	/**
	 * Getting procedures for doctor
	 * 
	 * @param docId
	 * @return -list of patient entity
	 */
	public List<Patient> getProcForDoctor(int docId) {
		Connection connection = ConnectingPool.getConnection();
		List<Patient> patients = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(Query.SQL_GET_PROCEDURES_FOR_DOCTOR);
			preparedStatement.setInt(1, docId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Patient patient = PatientManager.getPatientById(resultSet.getInt("patient_id"));

				patient.setProcedure(new Procedure(resultSet.getInt("procedure_id"), null, resultSet.getString("info"),
						getTreatmantById(resultSet.getInt("treatment_id")), 0));
				patients.add(patient);
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		} finally {
			closeConnection(connection);
		}
		return patients;

	}
}
