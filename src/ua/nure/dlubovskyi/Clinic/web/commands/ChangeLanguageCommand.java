package ua.nure.dlubovskyi.Clinic.web.commands;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.dlubovskyi.Clinic.constants.Urls;

/**
 * 
 * @author Dlubovskyi Oleg
 *
 */
public class ChangeLanguageCommand extends AbstractCommand {
	private static final Logger LOGGER = Logger.getLogger(ChangeLanguageCommand.class);

	@Override
	public String executeCommand(HttpServletRequest request, HttpServletResponse response, String method)
			throws IOException {
		if (method.equals("POST")) {
			return doPost(request);
		}
		return null;
	}

	/**
	 * 
	 * @param request
	 * @return -psth to redirect
	 */
	private String doPost(HttpServletRequest request) {
		String sendFrom = request.getParameter("url");
		String lang = request.getParameter("lang");
		request.getSession().setAttribute("lang", lang);
		LOGGER.trace("Language changed to: " + lang);
		if (sendFrom.equals("")) {
			return Urls.REDIRECT_LOGIN_PAGE;
		}
		return "controller?" + sendFrom;
	}
}
