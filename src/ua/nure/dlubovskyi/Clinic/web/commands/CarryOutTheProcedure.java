package ua.nure.dlubovskyi.Clinic.web.commands;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.dlubovskyi.Clinic.constants.Urls;
import ua.nure.dlubovskyi.Clinic.entity.managers.StaffManager;

/**
 * 
 * @author Dlubovskyi Oleg
 *
 */
public class CarryOutTheProcedure extends AbstractCommand {
	private final Logger LOGGER = Logger.getLogger(CarryOutTheProcedure.class);

	@Override
	public String executeCommand(HttpServletRequest request, HttpServletResponse response, String method)
			throws IOException {
		LOGGER.debug("Executing CarryOutTheProcedure command");

		if (method.equals("POST")) {

			return doPost(request);
		}

		return null;
	}

	/**
	 * DoPost realization
	 * 
	 * @param request
	 * @return
	 */
	private String doPost(HttpServletRequest request) {
		int procId = Integer.parseInt(request.getParameter("proc"));

		String doctor = request.getParameter("doctor");
		StaffManager.carryOutProcedure(procId);
		if (!Objects.isNull(doctor)) {
			return Urls.REDIRECT_PATIENTS_BY_DOC_ID + doctor;
		} else {
			return Urls.REDIRECT_TO_NURSE_PATIENTS;
		}
	}
}
