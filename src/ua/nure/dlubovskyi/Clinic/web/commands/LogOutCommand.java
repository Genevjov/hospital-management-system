package ua.nure.dlubovskyi.Clinic.web.commands;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.dlubovskyi.Clinic.constants.Urls;

public class LogOutCommand extends AbstractCommand {
	@Override
	public String executeCommand(HttpServletRequest request, HttpServletResponse response, String method)
			throws IOException {
		String path = null;
		if (method.equals("GET")) {
			path = doGet(request);
		}
		return path;
	}

	private String doGet(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		return Urls.PAGE_LOGIN;
	}
}
