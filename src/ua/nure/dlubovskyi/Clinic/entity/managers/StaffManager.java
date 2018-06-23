package ua.nure.dlubovskyi.Clinic.entity.managers;

import ua.nure.dlubovskyi.Clinic.dao.staff.StaffDao;
import ua.nure.dlubovskyi.Clinic.entity.staff.Staff;

public class StaffManager {

	/**
	 * Returns found staff with entered login
	 * 
	 * @see StaffDao
	 * @param login
	 * @return staff obj
	 */
	public static Staff getStaffByLogin(String login) {
		return StaffDao.getStaffByLogin(login);
	}

	public static void addNewAdmin(Staff staff) {
		StaffDao.addNewAdmin(staff);
	}

}
