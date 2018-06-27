package ua.nure.dlubovskyi.Clinic.web.commands;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

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
			path = doGet(request);
		}
		return path;
	}

	/**
	 * Getting Nurse entity list and sort if sort option is present as parameter
	 * 
	 * @see Nurse
	 * @param request
	 * @param response
	 * @return path to forward jsp file
	 */
	private String doGet(HttpServletRequest request) {
		String sort = request.getParameter("sort");
		// obtian list
		List<Nurse> nurses;
		if (!Objects.isNull(sort)) {
			nurses = NurseManager.getSortedNurseList(sort);
		} else {
			nurses = NurseManager.getAllNurses();
		}
		request.getSession().setAttribute("nurses", nurses);
		return Urls.PAGE_LIST_NURSES;
	}
}
