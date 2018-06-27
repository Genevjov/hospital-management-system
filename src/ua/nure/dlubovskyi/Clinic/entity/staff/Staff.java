package ua.nure.dlubovskyi.Clinic.entity.staff;

import ua.nure.dlubovskyi.Clinic.entity.AbstractEntity;

/**
 * Class container which impl staff entity
 * 
 * @see AbstractEntity
 * @author Dlubovskyi Oleg
 *
 */
public class Staff extends AbstractEntity {
	private int id;
	private String role;
	private String login;
	private String password;

	/**
	 * Default constr
	 * 
	 * @param firstName
	 * @param secondName
	 * @param role
	 * @param login
	 * @param password
	 */
	public Staff(String firstName, String secondName, String role, String login, String password) {
		super(firstName, secondName);
		this.role = role;
		this.login = login;
		this.password = password;
	}

	/**
	 * 
	 * @param firstName
	 * @param secondName
	 * @param id
	 * @param role
	 * @param login
	 * @param password
	 */
	public Staff(String firstName, String secondName, int id, String role, String login, String password) {
		super(firstName, secondName);
		this.id = id;
		this.role = role;
		this.login = login;
		this.password = password;
	}

	/**
	 * 
	 * @param firstName
	 * @param secondName
	 * @param role
	 */
	public Staff(String firstName, String secondName, String role) {
		super(firstName, secondName);
		this.role = role;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login
	 *            the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

}
