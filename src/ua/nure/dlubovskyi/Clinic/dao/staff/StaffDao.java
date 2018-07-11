package ua.nure.dlubovskyi.Clinic.dao.staff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import ua.nure.dlubovskyi.Clinic.constants.Query;
import ua.nure.dlubovskyi.Clinic.dao.ConnectingPool;
import ua.nure.dlubovskyi.Clinic.entity.staff.Staff;

/**
 * 
 * @author Dlubovskyi Oleg
 *
 */
public class StaffDao {
	public final static Logger LOGGER = Logger.getLogger(StaffDao.class);
	private static Connection connection;

	/**
	 * Util meth for close connection.
	 * 
	 * @param connection
	 */
	private static void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			LOGGER.error("Close connection error.");
		}
		LOGGER.debug("Connection has been closed.");

	}

	/**
	 * Return Staff obj by login.
	 * 
	 * @param login
	 * @return staff obj
	 * @see Staff
	 */
	public static Staff getStaffByLogin(String login) {
		Staff staff = null;
		connection = ConnectingPool.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(Query.SQL_GET_STAFF_BY_LOGIN);
			preparedStatement.setString(1, login);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				staff = new Staff(resultSet.getString("first_name"), resultSet.getString("second_name"),
						resultSet.getInt("staff_id"), resultSet.getString("role_name"), login,
						resultSet.getString("password"));
			}
			preparedStatement.close();
			resultSet.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		} finally {
			closeConnection(connection);
		}
		return staff;
	}

	/**
	 * Adding new admin user to database
	 * 
	 * @param admin
	 */
	public static void addNewAdmin(Staff admin) {
		connection = ConnectingPool.getConnection();
		try {
			LOGGER.debug("Adding staff data for new admin");
			PreparedStatement ps = connection.prepareStatement(Query.SQL_ADD_ADMIN);
			ps.setString(1, admin.getFirstName());
			ps.setString(2, admin.getSecondName());
			ps.executeUpdate();
			// obtain this staff id
			ps = connection.prepareStatement(Query.SQL_GET_LAST_INDEX);
			ResultSet resultSet = ps.executeQuery();
			int id = 0;
			while (resultSet.next()) {
				id = resultSet.getInt("id");
			}
			LOGGER.debug("New Admin has been added with id: " + id);
			// setting login data
			LOGGER.debug("Adding login data for new admin");
			ps = connection.prepareStatement(Query.SQL_ADD_LOGIN_DATA);
			ps.setInt(1, id);
			ps.setString(2, admin.getLogin());
			ps.setString(3, admin.getPassword());
			ps.executeUpdate();
			resultSet.close();
			ps.close();
		} catch (SQLException e) {
			LOGGER.error("Failed to add new admin.");
		} finally {
			closeConnection(connection);

		}
	}

	/**
	 * Method for setting login data to staff
	 * 
	 * @see Staff
	 * @param id
	 */
	public static void setLoginDataByStaffId(Staff staff, int id) {
		LOGGER.debug("Getting staf login data for id: " + id);
		connection = ConnectingPool.getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(Query.SQL_GET_LOGIN_DATA_BY_ID)) {
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				staff.setLogin(resultSet.getString("login"));
				staff.setPassword(resultSet.getString("password"));
			}
			resultSet.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		} finally {
			closeConnection(connection);
		}
	}

	/**
	 * Method sets 1 (true) to procedure is done status
	 * 
	 * @param procId
	 */
	public static void carryOutProc(int procId, int staffId) {
		LOGGER.debug("Procedure: " + procId + " has been executed");
		connection = ConnectingPool.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(Query.SQL_CARRY_OUT_PROC);
			preparedStatement.setInt(1, staffId);
			preparedStatement.setInt(2, procId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		} finally {
			closeConnection(connection);
		}
	}
}
