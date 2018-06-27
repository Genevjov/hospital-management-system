package ua.nure.dlubovskyi.Clinic.web.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.dlubovskyi.Clinic.constants.Urls;

/**
 * Servlet Filter implementation class AccessFilter
 */
public class AccessFilter implements Filter {
	private Map<String, List<String>> accessCommandsMap = new HashMap<>();
	private List<String> freeZone;
	private final Logger LOGGER = Logger.getLogger(AccessFilter.class);

	/**
	 * Default constructor.
	 */
	public AccessFilter() {

	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {

	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String command = request.getParameter("command");
		LOGGER.debug("Obtain command:" + command);
		HttpSession session = req.getSession(false);

		if (freeZone.contains(command)) {
			chain.doFilter(request, response);
		} else if (Objects.isNull(session)) {
			resp.sendRedirect(Urls.REDIRECT_LOGIN_PAGE);
		} else if (Objects.isNull(session.getAttribute("role"))
				|| !accessCommandsMap.get((String) session.getAttribute("role")).contains(command)) {
			resp.sendRedirect(Urls.REDIRECT_LOGIN_PAGE);

		} else {
			chain.doFilter(request, response);

		}

	}

	/**
	 * Init map with commands and roles
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		LOGGER.debug("Access filter initialization has been started");
		accessCommandsMap.put("Admin", asList(fConfig.getInitParameter("admin")));
		accessCommandsMap.put("Doctor", asList(fConfig.getInitParameter("doctor")));
		accessCommandsMap.put("Nurse", asList(fConfig.getInitParameter("nurse")));
		freeZone = asList(fConfig.getInitParameter("freeZone"));
		LOGGER.debug("Filter initialization finished");
	}

	/**
	 * Extracts parameter values from string.
	 * 
	 * @param str
	 *            parameter values string.
	 * @return list of parameter values.
	 */
	private List<String> asList(String str) {
		List<String> list = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(str);
		while (st.hasMoreTokens()) {
			list.add(st.nextToken());
		}
		return list;
	}

}
