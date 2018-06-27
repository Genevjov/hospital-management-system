package ua.nure.dlubovskyi.Clinic.web.commands;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.dlubovskyi.Clinic.constants.Urls;
import ua.nure.dlubovskyi.Clinic.entity.managers.DoctorManager;
import ua.nure.dlubovskyi.Clinic.entity.managers.PatientManager;

public class GetPatienMedicalCardCommand extends AbstractCommand {
	private static final Logger LOGGER = Logger.getLogger(GetPatienMedicalCardCommand.class);

	@Override
	public String executeCommand(HttpServletRequest request, HttpServletResponse response, String method)
			throws IOException {
		LOGGER.info("Executing GetPatienMedicalCardCommand");
		String path = null;
		if (method.equals("GET")) {
			path = doGet(request);
		}
		LOGGER.info("Executed GetPatienMedicalCardCommand");

		return path;
	}

	/**
	 * 
	 * @param request
	 * @return path
	 */
	private String doGet(HttpServletRequest request) {
		String path = null;
		String idString = request.getParameter("patient");
		request.setAttribute("treatments", DoctorManager.getAllTreatments());

		if (!Objects.isNull(idString)) {
			int id = Integer.parseInt(idString);
			request.getSession().setAttribute("patient", PatientManager.getPatientById(id));
			path = Urls.PAGE_PATIENT_CARD;
		} else {
			path = Urls.PAGE_LIST_PATIENTS;
		}
		return path;
	}

}
