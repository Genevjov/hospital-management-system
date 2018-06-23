package ua.nure.dlubovskyi.Clinic.web.commands;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.dlubovskyi.Clinic.constants.Urls;
import ua.nure.dlubovskyi.Clinic.entity.managers.DoctorManager;
import ua.nure.dlubovskyi.Clinic.entity.staff.Doctor;

/**
 * Doctors by specialization command
 * 
 * @author Dlubosvskyi Oleg
 *
 */
public class DoctorsListBySpecializationCommand extends AbstractCommand {
	private static final Logger LOGGER = Logger.getLogger(DoctorsListBySpecializationCommand.class);

	@Override
	public String executeCommand(HttpServletRequest request, HttpServletResponse response, String method)
			throws IOException {
		LOGGER.debug("Executing DoctorsListBySpec. command.");
		String path = null;
		if (method.equals("GET")) {
			path = doGet(request, response);
		}
		LOGGER.debug("DoctorsListBySpec. command has been executed.");
		return path;
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @return path to redirect
	 */
	private String doGet(HttpServletRequest request, HttpServletResponse response) {
		String path = null;
		int specId = Integer.parseInt(request.getParameter("spec"));
		// validating
		if (!Objects.isNull(specId)) {
			List<Doctor> doctors = DoctorManager.getDoctorsBySpecId(specId);
			request.setAttribute("doctors", doctors);
			path = Urls.PAGE_LIST_DOCTORS;
		}
		return path;
	}

}
