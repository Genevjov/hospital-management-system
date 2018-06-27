package ua.nure.dlubovskyi.Clinic.web.controller;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.dlubovskyi.Clinic.constants.Urls;
import ua.nure.dlubovskyi.Clinic.web.commands.AbstractCommand;
import ua.nure.dlubovskyi.Clinic.web.commands.CommandManager;

/**
 * Servlet implementation class MainControllerServlet
 */
@WebServlet
public class MainControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(MainControllerServlet.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MainControllerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOGGER.info("Obtain GET query");
		process(request, response, "GET");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOGGER.info("Obtain POST query");
		process(request, response, "POST");
	}

	private void process(HttpServletRequest request, HttpServletResponse response, String method)
			throws ServletException, IOException {
		CommandManager manager = new CommandManager();
		String commandName = request.getParameter("command");
		if (!Objects.isNull(commandName)) {

			AbstractCommand command = manager.getCommand(commandName);
			if (!Objects.isNull(command)) {
				String path = command.executeCommand(request, response, method);
				if (!Objects.isNull(path)) {
					if (method.equals("POST")) {
						LOGGER.trace("Redirect to: " + path);
						response.sendRedirect(path);
						LOGGER.debug("Controller has finished command executing");
					} else if (method.equals("GET")) {

						LOGGER.trace("Forward to: " + path);
						request.getRequestDispatcher(path).forward(request, response);
						LOGGER.debug("Controller has finished command executing");
					}
				}
			} else {
				if (commandName.equals("logout")) {
					request.getSession().invalidate();
					response.sendRedirect(Urls.REDIRECT_LOGIN_PAGE);
				} else {
					LOGGER.trace("Got bad command");
					request.getRequestDispatcher(Urls.PAGE_BAD_COMMAND).forward(request, response);
				}
			}
		}

	}

}
