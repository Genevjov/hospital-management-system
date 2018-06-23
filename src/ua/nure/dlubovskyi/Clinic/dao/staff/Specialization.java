package ua.nure.dlubovskyi.Clinic.dao.staff;

/**
 *
 * Doctor specialization entity
 * 
 * @author Dlubovskyi Oleg
 */
public class Specialization {
	private int id;
	private String name;

	/**
	 * Default constructor.
	 * 
	 * @param id
	 * @param name
	 */
	public Specialization(int id, String name) {
		this.id = id;
		this.name = name;
	}

	/**
	 * Empty constructor.
	 */
	public Specialization() {
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
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

}
