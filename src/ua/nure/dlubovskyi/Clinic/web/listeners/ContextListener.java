package ua.nure.dlubovskyi.Clinic.web.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Application Lifecycle Listener implementation class ContextListener
 *
 */
@WebListener
public class ContextListener implements ServletContextListener {
	private static final Logger LOGGER = Logger.getLogger(ContextListener.class);

	/**
	 * Default constructor.
	 */
	public ContextListener() {
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		ServletContext servletContext = arg0.getServletContext();
		initLogging(servletContext);
	}

	// init log4j
	private void initLogging(ServletContext servletContext) {
		System.out.println("Logging initialization started");
		try {
			PropertyConfigurator.configure(servletContext.getRealPath("WEB-INF/log4j.properties"));
		} catch (Exception ex) {
			System.err.println(ex.getLocalizedMessage());
		}
		LOGGER.debug("Logging has been initialized");
	}

}
