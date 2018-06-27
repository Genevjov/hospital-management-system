package ua.nure.dlubovskyi.Clinic.web.commands;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.dlubovskyi.Clinic.constants.Urls;
import ua.nure.dlubovskyi.Clinic.dao.staff.DoctorDao;
import ua.nure.dlubovskyi.Clinic.dao.staff.StaffDao;
import ua.nure.dlubovskyi.Clinic.entity.managers.DoctorManager;
import ua.nure.dlubovskyi.Clinic.entity.managers.NurseManager;
import ua.nure.dlubovskyi.Clinic.entity.managers.StaffManager;
import ua.nure.dlubovskyi.Clinic.entity.staff.Doctor;
import ua.nure.dlubovskyi.Clinic.entity.staff.Specialization;
import ua.nure.dlubovskyi.Clinic.entity.staff.Staff;
import ua.nure.dlubovskyi.Clinic.web.Utils.Validator;

/**
 * 
 * @author Dlubovskyi Oleg
 *
 */
public class AddStaffCommand extends AbstractCommand {
	private final List<String> roles = Arrays.asList(new String[] { "Admin", "Doctor", "Nurse" });
	private final List<Specialization> specs = DoctorManager.getAllSpecification();
	private final Logger LOGGER = Logger.getLogger(AddStaffCommand.class);

	@Override
	public String executeCommand(HttpServletRequest request, HttpServletResponse response, String method)
			throws IOException {
		LOGGER.debug("Executing add staff command. Method " + method);
		String path = null;
		if (method.equals("POST")) {
			path = doPost(request, response);
		} else if (method.equals("GET")) {
			request.getSession().setAttribute("specializations", specs);

			path = Urls.PAGE_ADD_STAFF;
		}
		LOGGER.debug("Add staff command has been executed");

		return path;
	}

	/**
	 * Method for validation input role type and call other one for each role
	 * 
	 * @param request
	 * @param response
	 * @return string with path to redirect (PRG)
	 */
	private String doPost(HttpServletRequest request, HttpServletResponse response) {
		String path = null;
		String roleParam = request.getParameter("role");
		if (Objects.isNull(roleParam) || !roles.contains(roleParam)) {
			request.getSession().setAttribute("emptyInput", true);

			return Urls.REDIRECT_ADD_STAFF_PAGE;
		} else {
			switch (roleParam) {
			case "Admin":
				path = addAdmin(request);
				break;
			case "Doctor":
				path = addDoctor(request);
				break;
			case "Nurse":
				path = addNurses(request);
				break;

			}
		}
		return path;
	}

	/**
	 * Method for validation input and adding new doctor entity to db
	 * 
	 * @see Validator
	 * @see Doctor
	 * @see DoctorDao
	 * @param request
	 * @return string with path to redirect (PRG)
	 */
	private String addDoctor(HttpServletRequest request) {
		// result path
		String path = null;
		// obtain data
		String firstName = request.getParameter("firstName");
		String secondName = request.getParameter("secondName");
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String rePassword = request.getParameter("rePassword");
		String specName = request.getParameter("spec");
		// validation for user code injection
		if (Objects.isNull(firstName) || Objects.isNull(secondName) || Objects.isNull(login) || Objects.isNull(password)
				|| Objects.isNull(rePassword) || Objects.isNull(specName)) {
			// setting error
			request.getSession().setAttribute("emptyInput", true);
			path = Urls.REDIRECT_ADD_STAFF_PAGE;
		} else {
			// value validation
			if (isValidInp(firstName) && isValidInp(secondName) && isValidInp(login) && isValidInp(password)
					&& password.equals(rePassword)) {
				// create spec. entity
				Specialization specialization = DoctorManager.getSpecIdByName(specName);
				Doctor doctor = new Doctor(firstName, secondName, login, password, specialization);
				// call manager-class for adding to db
				DoctorManager.addDoctor(doctor);
				request.getSession().removeAttribute("emptyInput");

				path = Urls.REDIRECT_DOCTORS_LIST;

			} else {
				// setting error
				request.getSession().setAttribute("emptyInput", true);
				path = Urls.REDIRECT_ADD_STAFF_PAGE;

			}
		}
		return path;

	}

	/**
	 * Method for validation input and adding new nurse entity to db
	 * 
	 * @see Validator
	 * @see Nurse
	 * @see DoctorDao
	 * @param request
	 * @return string with path to redirect (PRG)
	 */
	private String addNurses(HttpServletRequest request) {
		String path = null;
		String firstName = request.getParameter("firstName");
		String secondName = request.getParameter("secondName");
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String rePassword = request.getParameter("rePassword");
		if (Objects.isNull(firstName) || Objects.isNull(secondName) || Objects.isNull(login) || Objects.isNull(password)
				|| Objects.isNull(rePassword)) {
			request.getSession().setAttribute("emptyInput", true);
			path = Urls.REDIRECT_ADD_STAFF_PAGE;
		} else {
			if (isValidInp(firstName) && isValidInp(secondName) && isValidInp(login) && isValidInp(password)
					&& password.equals(rePassword)) {
				Staff staff = new Staff(firstName, secondName, "Nurse");
				staff.setLogin(login);
				staff.setPassword(password);
				NurseManager.addNewNurse(staff);
				request.getSession().removeAttribute("emptyInput");

				path = Urls.REDIRECT_NURSES_LIST;
			} else {
				request.getSession().setAttribute("emptyInput", true);
				path = Urls.REDIRECT_ADD_STAFF_PAGE;
			}
		}
		return path;

	}

	/**
	 * Method for validation input and adding new admin entity to db
	 * 
	 * @see Validator
	 * @see Staff
	 * @see StaffDao
	 * @param request
	 * @return string with path to redirect (PRG)
	 */
	private String addAdmin(HttpServletRequest request) {
		String path = null;
		String firstName = request.getParameter("firstName");
		String secondName = request.getParameter("secondName");
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String rePassword = request.getParameter("rePassword");
		if (Objects.isNull(firstName) || Objects.isNull(secondName) || Objects.isNull(login) || Objects.isNull(password)
				|| Objects.isNull(rePassword)) {
			request.getSession().setAttribute("emptyInput", true);
			path = Urls.REDIRECT_ADD_STAFF_PAGE;
		} else {
			if (isValidInp(firstName) && isValidInp(secondName) && isValidInp(login) && isValidInp(password)
					&& password.equals(rePassword)) {
				Staff staff = new Staff(firstName, secondName, "Admin");
				staff.setLogin(login);
				staff.setPassword(password);
				StaffManager.addNewAdmin(staff);
				request.getSession().removeAttribute("emptyInput");
				path = Urls.REDIRECT_DOCTORS_LIST;
			} else {
				request.getSession().setAttribute("emptyInput", true);

				path = Urls.REDIRECT_ADD_STAFF_PAGE;

			}
		}
		return path;
	}

	/**
	 * @see Validator
	 * @param string
	 * @return validation result
	 */
	private boolean isValidInp(String string) {
		return Validator.validateInput(string);
	}
}
