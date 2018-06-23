package ua.nure.dlubovskyi.Clinic.entity.managers;

import java.util.List;

import ua.nure.dlubovskyi.Clinic.dao.staff.DoctorDao;
import ua.nure.dlubovskyi.Clinic.dao.staff.Specialization;
import ua.nure.dlubovskyi.Clinic.entity.patient.Treatmant;
import ua.nure.dlubovskyi.Clinic.entity.staff.Doctor;

public class DoctorManager {

	public static List<Doctor> getAllDoctors() {
		return DoctorDao.getAllDoctors();
	}

	public static List<Specialization> getAllSpecification() {
		return DoctorDao.getAllSpecializations();
	}

	public static List<Doctor> getDoctorsBySpecId(int id) {
		return DoctorDao.getDoctorsBySpecId(id);
	}

	public static void addDoctor(Doctor doctor) {
		DoctorDao.addDoctor(doctor);
	}

	public static Specialization getSpecIdByName(String name) {
		return DoctorDao.getSpecByName(name);
	}

	public static Doctor getDoctorById(int id) {
		return DoctorDao.getDoctorById(id);
	}

	public static Treatmant geTreatmantById(int id) {
		return DoctorDao.getTreatmantById(id);
	}
}
