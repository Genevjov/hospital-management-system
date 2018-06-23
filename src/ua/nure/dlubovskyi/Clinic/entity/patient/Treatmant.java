package ua.nure.dlubovskyi.Clinic.entity.patient;

/**
 * Treatmant entity
 * 
 * @see Procedure
 * @author DLubovskyi Oleg
 *
 */
public class Treatmant {
	private int id;
	private String name;

	/**
	 * Default constructor
	 * 
	 * @param id
	 * @param name
	 */
	public Treatmant(int id, String name) {
		super();
		this.id = id;
		this.name = name;
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
