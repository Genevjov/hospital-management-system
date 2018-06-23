package ua.nure.dlubovskyi.Clinic.web.commands;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.dlubovskyi.Clinic.constants.Urls;
import ua.nure.dlubovskyi.Clinic.entity.managers.PatientManager;

public class GetPatienMedicalCardCommand extends AbstractCommand {

	@Override
	public String executeCommand(HttpServletRequest request, HttpServletResponse response, String method)
			throws IOException {
		String path = null;
		if (method.equals("GET")) {
			path = doGet(request);
		}
		return path;
	}

	private String doGet(HttpServletRequest request) {
		String path = null;
		String idString = request.getParameter("patient");
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
