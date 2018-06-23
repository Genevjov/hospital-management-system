package ua.nure.dlubovskyi.Clinic.entity;

/**
 * Abstract class container for all entites
 * 
 * @author Zver
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

}
