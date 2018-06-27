package ua.nure.dlubovskyi.Clinic.entity;

/**
 * Abstract class container for all entites
 * 
 * @author Dlubovskyi Oleg
 *
 */
public abstract class AbstractEntity {
	private String firstName;
	private String secondName;
	private String dateOfBirth;

	public AbstractEntity(String firstName, String secondName) {
		this.firstName = firstName;
		this.secondName = secondName;
	}

	/**
	 * Get first name
	 * 
	 * @return firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Set firstName
	 * 
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Get secondName
	 * 
	 * @return secondName
	 */
	public String getSecondName() {
		return secondName;
	}

	/**
	 * Set secondName
	 * 
	 * @param secondName
	 */
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	/**
	 * Get dateOfBirth
	 * 
	 * @return dateOfBirth
	 */
	public String getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * Set dateOfBirth
	 * 
	 * @param dateOfBirth
	 */
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

}
