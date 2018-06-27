package ua.nure.dlubovskyi.Clinic.web.commands;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.dlubovskyi.Clinic.constants.Urls;
import ua.nure.dlubovskyi.Clinic.entity.managers.DoctorManager;
import ua.nure.dlubovskyi.Clinic.entity.staff.Doctor;

/**
 * 
 * @author Dlubovskyi Oleg
 *
 */
public class GetDoctorInfoCommand extends AbstractCommand {
	private static final Logger LOGGER = Logger.getLogger(GetDoctorInfoCommand.class);

	@Override
	public String executeCommand(HttpServletRequest request, HttpServletResponse response, String method)
			throws IOException {
		LOGGER.debug("Start executing GetDoctorInfoCommand");
		String path = null;
		if (method.equals("GET")) {
			path = doGet(request);
		}
		LOGGER.debug("GetDoctorInfoCommand has been exectuted ");
		return path;
	}

	/**
	 * 
	 * @param request
	 * @return path to page file
	 */
	private String doGet(HttpServletRequest request) {
		LOGGER.debug("Obtain id");
		String param = request.getParameter("id");
		if (!Objects.isNull(param) && !param.isEmpty()) {
			int id = Integer.parseInt(param);
			LOGGER.debug("Got id: " + id);
			Doctor doc = DoctorManager.getDoctorById(id);
			LOGGER.debug("Found doctor: " + doc.getSecondName());
			if (!Objects.isNull(doc)) {
				request.getSession().setAttribute("doctor", doc);
				request.getSession().setAttribute("patients", DoctorManager.getPatientsByDoctorId(doc.getDocId()));
				return Urls.PAGE_DOCTOR_INFO;
			} else {
				return Urls.PAGE_LIST_DOCTORS;

			}
		} else {
			return Urls.PAGE_LIST_DOCTORS;
		}
	}
}
