package ua.nure.dlubovskyi.Clinic.dao.staff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.dlubovskyi.Clinic.constants.Query;
import ua.nure.dlubovskyi.Clinic.dao.ConnectingPool;
import ua.nure.dlubovskyi.Clinic.entity.staff.Nurse;
import ua.nure.dlubovskyi.Clinic.entity.staff.Staff;

/**
 * 
 * @author DLubovskyi Oleg
 *
 */
public class NurseDao {
	private static final Logger LOGGER = Logger.getLogger(NurseDao.class);

	// db connection
	private static Connection connection;

	/**
	 * Getting list of Ners entity
	 * 
	 * @see Nurse
	 * @return Nurse entity list
	 */
	public static List<Nurse> getAllNurses() {
		connection = ConnectingPool.getConnection();
		List<Nurse> nurses = new ArrayList<>();
		try (PreparedStatement preparedStatement = connection.prepareStatement(Query.SQL_GET_ALL_NURSES)) {
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Nurse nurse = new Nurse(resultSet.getString("first_Name"), resultSet.getString("second_name"),
						resultSet.getString("login"));

				nurses.add(nurse);
			}
			closeConnection(connection);
			resultSet.close();
		} catch (SQLException e) {
			LOGGER.error(e.getLocalizedMessage());
		}
		return nurses;
	}

	public static void addNewNurse(Staff nurse) {
		connection = ConnectingPool.getConnection();
		try {
			LOGGER.debug("Adding staff data for new nurse");
			PreparedStatement ps = connection.prepareStatement(Query.SQL_ADD_NURSE);
			ps.setString(1, nurse.getFirstName());
			ps.setString(2, nurse.getSecondName());
			ps.executeUpdate();
			// obtain this staff id
			ps = connection.prepareStatement(Query.SQL_GET_LAST_INDEX);
			ResultSet resultSet = ps.executeQuery();
			int id = 0;
			while (resultSet.next()) {
				id = resultSet.getInt("id");
			}
			LOGGER.debug("New Nurse has been added with id: " + id);
			// setting login data
			LOGGER.debug("Adding login data for new Nurse");
			ps = connection.prepareStatement(Query.SQL_ADD_LOGIN_DATA);
			ps.setInt(1, id);
			ps.setString(2, nurse.getLogin());
			ps.setString(3, nurse.getPassword());
			ps.executeUpdate();
			resultSet.close();
			ps.close();
			closeConnection(connection);
		} catch (SQLException e) {
			LOGGER.error("Failed to add new nurse.");
		}
	}

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

}
