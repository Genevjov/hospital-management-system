package ua.nure.dlubovskyi.Clinic.entity.managers;

import java.util.List;

import ua.nure.dlubovskyi.Clinic.dao.patient.PatientDao;
import ua.nure.dlubovskyi.Clinic.entity.patient.Patient;
import ua.nure.dlubovskyi.Clinic.entity.patient.Procedure;

public class PatientManager {

	/**
	 * @see Patient
	 * @see PatientDao
	 * @return list of patient entity
	 */
	public static List<Patient> getAllPatients() {
		return PatientDao.getAllPatients();
	}

	/**
	 * Add patient to database
	 * 
	 * @see Patient
	 * @see PatientDao
	 * @param patient
	 */
	public static void addPatient(Patient patient) {
		PatientDao.addPatient(patient);
	}

	/**
	 * Getting list of procedures for patient by id
	 * 
	 * @see Procedure
	 * @see PatientDao
	 * @param id
	 * @return Procedure list
	 */
	public static List<Procedure> getProceduresByPatientId(int id) {
		return PatientDao.getProceduresByPatientId(id);
	}

	/**
	 * Getting patient by id
	 * 
	 * @see PatientDao
	 * @see Patient
	 * @param id
	 * @return Patient entity
	 */
	public static Patient getPatientById(int id) {
		return PatientDao.getPatientById(id);
	}

	/**
	 * Getting sorted list of patients by parameter
	 * 
	 * @see PatientDao
	 * @see Patient
	 * @param sortParam
	 * @return
	 */
	public static List<Patient> getPatientsSorted(String sortParam) {
		return PatientDao.getPatientsSorted(sortParam);
	}

	public static void dischargePatient(int patientId) {
		PatientDao.dischargePatient(patientId);
	}
}
