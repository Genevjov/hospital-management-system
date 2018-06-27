package ua.nure.dlubovskyi.Clinic.web.commands;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.dlubovskyi.Clinic.entity.managers.DoctorManager;

public class SetDiagnosisToPatientCommand extends AbstractCommand {
	@Override
	public String executeCommand(HttpServletRequest request, HttpServletResponse response, String method)
			throws IOException {
		String path = null;
		if (method.equals("POST")) {
			path = doPost(request);
		}
		return path;
	}

	private String doPost(HttpServletRequest request) {
		int doctorId = DoctorManager.getDoctorIdByStaffId(Integer.parseInt(request.getParameter("doctor")));
		int patientId = Integer.parseInt(request.getParameter("patient"));
		String info = request.getParameter("info");
		if (info.isEmpty()) {
			return "controller?command=patientCard&patient=" + patientId;
		}
		DoctorManager.setDiagnosis(doctorId, patientId, info);
		return "controller?command=patientCard&patient=" + patientId;
	}
}
