package ua.nure.dlubovskyi.Clinic.web.commands;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.dlubovskyi.Clinic.constants.Urls;
import ua.nure.dlubovskyi.Clinic.entity.managers.StaffManager;
import ua.nure.dlubovskyi.Clinic.entity.staff.Staff;
import ua.nure.dlubovskyi.Clinic.web.controller.MainControllerServlet;

/**
 * Login command realization
 * 
 * @author Dlubovskyi Oleg
 * @see AbstractCommand
 */
public class LoginCommand extends AbstractCommand {
	public final static Logger LOGGER = Logger.getLogger(LoginCommand.class);

	@Override
	public String executeCommand(HttpServletRequest request, HttpServletResponse response, String method)
			throws IOException {
		LOGGER.debug("Executing login command.");
		// path for redirecting
		String path = null;
		if (method.equals("POST")) {
			path = doPost(request, response);
		}
	
		LOGGER.debug("Login command has been executed.");
		return path;

	}

	/**
	 * Command executing method if command was with POST maethod.
	 * 
	 * @param request
	 * @param response
	 * @return path to redirect
	 * @throws IOException
	 * @see {@link MainControllerServlet}
	 */
	private String doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		String result = null;
		// getting atribs and params for req. executing
		String userLang = (String) session.getAttribute("userLang");
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		Staff staff = StaffManager.getStaffByLogin(login);
		// validating to null and password eq.
		if (Objects.isNull(staff.getRole()) || !staff.getPassword().equals(password)) {
			session.setAttribute("errorMessage", getInvalidDataLocalMessage(userLang));
			LOGGER.trace("Failed to login: Login: " + login + " Password: " + password);
			// redirect to login page
			result = Urls.REDIRECT_LOGIN_PAGE;
		} else {
			session.removeAttribute("errorMessage");
			LOGGER.trace(
					staff.getRole() + " " + staff.getSecondName() + " " + staff.getFirstName() + " has logged in.");
			result = redirectByRole(staff);
			session.setAttribute("staff", staff);
			// for role filter
			session.setAttribute("role", staff.getRole());

		}
		return result;
	}

	/**
	 * Method for generating error message using user's language.
	 * 
	 * @param lang
	 *            value from session
	 * @return localizaed error message
	 */
	private String getInvalidDataLocalMessage(String lang) {
		String message = null;
		// validating
		if (Objects.isNull(lang) || lang.equals("en")) {
			message = "Invalid data. Try again";
		} else if (lang == "ru") {
			message = "Данные некорректны. Попробуйте ещё раз.";
		}
		return message;
	}

	/**
	 * Method returns path by role
	 * 
	 * @param logged
	 *            in staff obj.
	 * @see Staff
	 * @return redirect path for each role
	 **/
	private String redirectByRole(Staff staff) {
		String path = null;
		if (staff.getRole().equals("Admin")) {
			path = Urls.REDIRECT_DOCTORS_LIST;
			/*
			 * Nurse can see all patients in own dept
			 * 
			 * Doctor can see own patients
			 */
		} else if (staff.getRole().equals("Nures") || staff.getRole().equals("Doctor")) {
			path = Urls.REDIRECT_PATIENTS_LIST;
		}
		return path;
	}

}
