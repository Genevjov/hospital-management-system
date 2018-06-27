package ua.nure.dlubovskyi.Clinic.web.commands;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.dlubovskyi.Clinic.entity.managers.DoctorManager;

public class SetProcedureToPatient extends AbstractCommand {
	@Override
	public String executeCommand(HttpServletRequest request, HttpServletResponse response, String method)
			throws IOException {
		String path = null;
		if (method.equals("POST")) {
			path = doPost(request);
		}
		return path;
	}

	/**
	 * 
	 * @param request
	 * @return path
	 */
	private String doPost(HttpServletRequest request) {
		int doctorId = DoctorManager.getDoctorIdByStaffId(Integer.parseInt(request.getParameter("doctor")));
		int patientId = Integer.parseInt(request.getParameter("patient"));
		int treatmentId = Integer.parseInt(request.getParameter("procedure"));
		String info = request.getParameter("info");
		DoctorManager.setProcToPatient(doctorId, patientId, treatmentId, info);
		return "controller?command=patientCard&patient=" + patientId;
	}
}
