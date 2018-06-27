package ua.nure.dlubovskyi.Clinic.web.commands;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.dlubovskyi.Clinic.constants.Urls;
import ua.nure.dlubovskyi.Clinic.entity.managers.DoctorManager;
public class ProceduresForDoctor extends AbstractCommand {
	@Override
	public String executeCommand(HttpServletRequest request, HttpServletResponse response, String method)
			throws IOException {
		if (method.equals("GET")) {
			return doGet(request);
		}
		return null;
	}

	/**
	 * 
	 * @param request
	 * @return path
	 */
	private String doGet(HttpServletRequest request) {
		request.getSession().setAttribute("patients", DoctorManager
				.getProcForDoctor(DoctorManager.getDoctorIdByStaffId(Integer.parseInt(request.getParameter("id")))));
		return Urls.PAGE_DOCTOR_PROC;
	}
}
