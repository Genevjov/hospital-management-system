package ua.nure.dlubovskyi.Clinic.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class ConnectingPool {
	public final static Logger LOGGER = Logger.getLogger(ConnectingPool.class);
	private static DataSource dataSource;

	/**
	 * 
	 * @return connection
	 */
	public static synchronized Connection getConnection() {
		Connection connection = null;
		try {
			LOGGER.debug("Obtain dataSource");
			dataSource = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/summary_task");

		} catch (NamingException e) {
			LOGGER.error("DataSource obtaining failed");

		}

		try {
			// obtin connection
			LOGGER.debug("Getting connection by datasource");
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			LOGGER.error("Connection to db failed");
		}
		LOGGER.debug("Connected!");
		return connection;

	}
}
