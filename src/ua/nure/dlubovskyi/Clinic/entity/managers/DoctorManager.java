package ua.nure.dlubovskyi.Clinic.entity.managers;

import java.util.List;

import ua.nure.dlubovskyi.Clinic.dao.staff.DoctorDao;
import ua.nure.dlubovskyi.Clinic.entity.patient.Patient;
import ua.nure.dlubovskyi.Clinic.entity.patient.Treatmant;
import ua.nure.dlubovskyi.Clinic.entity.staff.Doctor;
import ua.nure.dlubovskyi.Clinic.entity.staff.Specialization;

public class DoctorManager {
	private static DoctorDao docDao = new DoctorDao();

	public static List<Doctor> getAllDoctors() {
		return docDao.getAllDoctors();
	}

	public static List<Specialization> getAllSpecification() {
		return docDao.getAllSpecializations();
	}

	public static List<Doctor> getDoctorsBySpecId(int id) {
		return docDao.getDoctorsBySpecId(id);
	}

	public static void addDoctor(Doctor doctor) {
		docDao.addDoctor(doctor);
	}

	public static Specialization getSpecIdByName(String name) {
		return docDao.getSpecByName(name);
	}

	public static Doctor getDoctorById(int id) {
		return docDao.getDoctorById(id);
	}

	public static Treatmant geTreatmantById(int id) {
		return docDao.getTreatmantById(id);
	}

	public static List<Patient> getPatientsByDoctorId(int id) {
		return docDao.getPatientsByDoctorId(id);
	}

	public static List<Doctor> getAllDoctorsSorted(String option) {
		return docDao.getAllDoctorsSorted(option);
	}

	public static List<Doctor> getDoctorsBySpcSorted(int specId, String option) {
		return docDao.getDoctorsBySpecSorted(specId, option);
	}

	public static int getDoctorIdByStaffId(int id) {
		return docDao.getDoctorIdByStaffId(id);

	}

	public static List<Treatmant> getAllTreatments() {
		return docDao.getAllTreatmants();
	}

	public static void setProcToPatient(int doctorId, int patientId, int treatmentId, String info) {
		docDao.setProcToPatient(doctorId, patientId, treatmentId, info);
	}

	public static void setDiagnosis(int doctorId, int patientId, String info) {
		docDao.setDiagnosis(doctorId, patientId, info);
	}

	public static List<Patient> getProcForDoctor(int doctorIdByStaffId) {
		return docDao.getProcForDoctor(doctorIdByStaffId);
	}
}
