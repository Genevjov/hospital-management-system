package ua.nure.dlubovskyi.Clinic.web.commands;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.dlubovskyi.Clinic.constants.Urls;
import ua.nure.dlubovskyi.Clinic.entity.managers.PatientManager;
import ua.nure.dlubovskyi.Clinic.web.Utils.ClinicCertificateGenerator;

/**
 * 
 * @author Dlubovskyi Oleg
 *
 */
public class DischargePatientCommand extends AbstractCommand {
	private static final Logger LOGGER = Logger.getLogger(DischargePatientCommand.class);

	@Override
	public String executeCommand(HttpServletRequest request, HttpServletResponse response, String method)
			throws IOException {
		LOGGER.debug("Executing DischargePatientCommand");
		if (method.equals("POST")) {
			return doPost(request);
		}
		return null;
	}

	/**
	 * 
	 * @param request
	 * @return path to redirect
	 */
	private String doPost(HttpServletRequest request) {
		int patientId = Integer.parseInt(request.getParameter("patient"));
		String doctor = request.getParameter("doctor");
		if (!Objects.isNull(patientId)) {

			// generate file
			try {
				ClinicCertificateGenerator.generate(PatientManager.getPatientById(patientId),
						PatientManager.getProceduresByPatientId(patientId));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// delete from db
			PatientManager.dischargePatient(patientId);
			if (!Objects.isNull(doctor)) {
				return Urls.REDIRECT_PATIENTS_BY_DOC_ID + doctor;
			} else {
				return Urls.REDIRECT_PATIENTS_LIST;
			}
		}
		return null;
	}

}
