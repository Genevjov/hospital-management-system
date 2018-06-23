package ua.nure.dlubovskyi.Clinic.entity.managers;

import java.util.List;

import ua.nure.dlubovskyi.Clinic.dao.patient.PatientDao;
import ua.nure.dlubovskyi.Clinic.entity.patient.Patient;
import ua.nure.dlubovskyi.Clinic.entity.patient.Procedure;
import ua.nure.dlubovskyi.Clinic.entity.patient.Treatmant;

public class PatientManager {
	public static List<Patient> getAllPatients() {
		return PatientDao.getAllPatients();
	}

	public static void addPatient(Patient patient) {
		PatientDao.addPatient(patient);
	}

	public static List<Procedure> getProceduresByPatientId(int id) {
		return PatientDao.getProceduresByPatientId(id);
	}

	public static Patient getPatientById(int id) {
		return PatientDao.getPatientById(id);
	}
}
