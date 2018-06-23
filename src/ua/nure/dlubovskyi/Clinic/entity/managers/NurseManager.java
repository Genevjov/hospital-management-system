package ua.nure.dlubovskyi.Clinic.entity.managers;

import java.util.List;

import ua.nure.dlubovskyi.Clinic.dao.staff.NurseDao;
import ua.nure.dlubovskyi.Clinic.entity.staff.Nurse;
import ua.nure.dlubovskyi.Clinic.entity.staff.Staff;

public class NurseManager {

	public static List<Nurse> getAllNurses() {
		return NurseDao.getAllNurses();
	}

	public static void addNewNurse(Staff staff) {
		NurseDao.addNewNurse(staff);
	}
}
