package ua.nure.dlubovskyi.Clinic.web.commands;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.dlubovskyi.Clinic.constants.Urls;
import ua.nure.dlubovskyi.Clinic.entity.managers.PatientManager;
import ua.nure.dlubovskyi.Clinic.entity.patient.Patient;

/**
 * 
 * @author Dlubovskyi Oleg
 *
 */
public class PatiensListCommand extends AbstractCommand {
	private final List<String> sortParams = Arrays
			.asList(new String[] { "firstName", "secondName", "dateOfBirth", "diagnosis", "doctor" });

	@Override
	public String executeCommand(HttpServletRequest request, HttpServletResponse response, String method)
			throws IOException {
		return doGet(request);
	}

	/**
	 * Mathod getting sort param if present and forward path to page file
	 * 
	 * @param request
	 * @return path to forward
	 */
	private String doGet(HttpServletRequest request) {
		// getting sort param
		String sortOption = request.getParameter("sort");
		// obtain list with Patient entity
		List<Patient> patients = PatientManager.getAllPatients();
		// sort by parma if present and validate
		if (!Objects.isNull(sortOption) && sortOption.contains(sortOption)) {
			// TODO sort
			sorter(sortOption, patients);
		}
		request.getSession().setAttribute("patients", patients);
		return Urls.PAGE_LIST_PATIENTS;
	}

	/**
	 * Method for sortin patients by sort option
	 * 
	 * @param param
	 */
	private void sorter(String param, List<Patient> patients) {

	}
}
