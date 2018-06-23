package ua.nure.dlubovskyi.Clinic.web.commands;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Abstract parent for all commands.
 * 
 * @author Dlubovskyi Oleg
 *
 */
public abstract class AbstractCommand {

	/**
	 * Abstract method for command executing. Need to be realized for executing
	 * child command.
	 * 
	 * @param request
	 * @param response
	 * @param method
	 * @return string url to redirect
	 * @throws IOException
	 */
	public abstract String executeCommand(HttpServletRequest request, HttpServletResponse response, String method)
			throws IOException;

}
