package ua.nure.dlubovskyi.Clinic.web.commands;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.dlubovskyi.Clinic.constants.Urls;
import ua.nure.dlubovskyi.Clinic.entity.managers.DoctorManager;

/**
 * 
 * @author Dlubovskyi Oleg
 *
 */
public class GetPatientsByDoctor extends AbstractCommand {
	private static final Logger LOGGER = Logger.getLogger(GetPatientsByDoctor.class);

	@Override
	public String executeCommand(HttpServletRequest request, HttpServletResponse response, String method)
			throws IOException {
		LOGGER.debug("Executing GetPatientsByDoctor command");

		if (method.equals("GET")) {
			return doGet(request);
		}
		return null;
	}

	/**
	 * Getting patient and display
	 * 
	 * @param request
	 * @return path
	 */
	private String doGet(HttpServletRequest request) {
		int id = DoctorManager.getDoctorIdByStaffId(Integer.parseInt(request.getParameter("id")));
		request.getSession().setAttribute("patients", DoctorManager.getPatientsByDoctorId(id));
		return Urls.PAGE_LIST_PATIENTS;
	}
}
