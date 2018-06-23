package ua.nure.dlubovskyi.Clinic.web.commands;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.dlubovskyi.Clinic.constants.Urls;
import ua.nure.dlubovskyi.Clinic.entity.managers.DoctorManager;
import ua.nure.dlubovskyi.Clinic.entity.managers.PatientManager;
import ua.nure.dlubovskyi.Clinic.entity.patient.Patient;
import ua.nure.dlubovskyi.Clinic.web.Utils.Validator;

/**
 * 
 * @author Dlubovskyi Oleg
 *
 */
public class AddPatientCommand extends AbstractCommand {
	@Override
	public String executeCommand(HttpServletRequest request, HttpServletResponse response, String method)
			throws IOException {
		String path = null;
		if (method.equals("GET")) {
			request.setAttribute("doctors", DoctorManager.getAllDoctors());
			path = Urls.PAGE_ADD_PATIENT;
		} else if (method.equals("POST")) {
			path = doPost(request);

		}
		return path;
	}

	/**
	 * 
	 * @param request
	 * @return path to redirect
	 */
	private String doPost(HttpServletRequest request) {
		// obtain params
		String path = null;
		String firstName = request.getParameter("firstName");
		String secondName = request.getParameter("secondName");
		String dateOfBirth = request.getParameter("dateOfBirth");
		String stringDocId = request.getParameter("doctor");
		// validation for code injection
		if (!Objects.isNull(firstName) && !Objects.isNull(secondName) && !Objects.isNull(dateOfBirth)
				&& !Objects.isNull(stringDocId)) {
			int docId = Integer.parseInt(stringDocId);
			// validation for correct input
			if (isValid(firstName) && isValid(secondName) && !dateOfBirth.isEmpty()) {
				// removing atribute if present
				request.getSession().removeAttribute("emptyFields");
				// filling patient entity
				Patient patient = new Patient(firstName, secondName, docId);
				patient.setDateOfBirth(dateOfBirth);
				// adding to database
				PatientManager.addPatient(patient);
				path = Urls.REDIRECT_PATIENTS_LIST;
				// setting error status
			} else {

				request.getSession().setAttribute("emptyFields", true);
				path = Urls.REDIRECT_ADD_PATINET;
			}
		} else {
			request.getSession().setAttribute("emptyFields", true);
			path = Urls.REDIRECT_ADD_PATINET;
		}
		return path;
	}

	/**
	 * 
	 * @return path to forward
	 */

	private boolean isValid(String input) {
		return Validator.validateInput(input);
	}
}
