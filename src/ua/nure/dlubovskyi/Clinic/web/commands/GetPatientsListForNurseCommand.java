package ua.nure.dlubovskyi.Clinic.web.commands;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.dlubovskyi.Clinic.constants.Urls;
import ua.nure.dlubovskyi.Clinic.entity.managers.NurseManager;

/**
 * 
 * @author Dlubovskyi Oleg
 *
 */
public class GetPatientsListForNurseCommand extends AbstractCommand {
	@Override
	public String executeCommand(HttpServletRequest request, HttpServletResponse response, String method)
			throws IOException {
		String path = null;
		if (method.equals("GET")) {
			path = doGet(request);
		} else {
			path = null;
		}
		return path;
	}

	/**
	 * 
	 * @param request
	 * @return path
	 */
	private String doGet(HttpServletRequest request) {
		request.getSession().setAttribute("patients", NurseManager.getPatientsForNurse());
		return Urls.PAGE_NURSE_PATIENTS;
	}
}
