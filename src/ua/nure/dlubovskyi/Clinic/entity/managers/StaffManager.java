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

	public static void setLoginData(Staff staff, int id) {
		StaffDao.setLoginDataByStaffId(staff, id);
	}

	public static void carryOutProcedure(int id, int staff_id) {
		StaffDao.carryOutProc(id, staff_id);
	}

}
