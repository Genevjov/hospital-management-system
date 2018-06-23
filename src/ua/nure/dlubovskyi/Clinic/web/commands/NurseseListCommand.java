package ua.nure.dlubovskyi.Clinic.web.commands;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.dlubovskyi.Clinic.constants.Urls;
import ua.nure.dlubovskyi.Clinic.entity.managers.NurseManager;
import ua.nure.dlubovskyi.Clinic.entity.staff.Nurse;

/**
 * 
 * @author Dlubovskyi Oleg
 *
 */
public class NurseseListCommand extends AbstractCommand {
	@Override
	public String executeCommand(HttpServletRequest request, HttpServletResponse response, String method)
			throws IOException {
		String path = null;
		if (method.equals("GET")) {
			path = doGet(request, response);
		}
		return path;
	}

	/**
	 * Getting Nurse entity list
	 * 
	 * @see Nurse
	 * @param request
	 * @param response
	 * @return path to forward jsp file
	 */
	private String doGet(HttpServletRequest request, HttpServletResponse response) {
		// obtian list
		List<Nurse> nurses = NurseManager.getAllNurses();
		request.getSession().setAttribute("nurses", nurses);
		return Urls.PAGE_LIST_NURSES;
	}
}
