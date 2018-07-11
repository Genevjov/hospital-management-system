package ua.nure.dlubovskyi.Clinic.entity.staff;

/**
 * 
 * @author Dlubovskyi Oleg
 *
 */
public class Nurse extends Staff {
	private int executedProc;

	public Nurse(String firstName, String secondName, String role) {
		super(firstName, secondName, "Nurse");
	}

	/**
	 * @return the executedProc
	 */
	public int getExecutedProc() {
		return executedProc;
	}

	/**
	 * @param executedProc
	 *            the executedProc to set
	 */
	public void setExecutedProc(int executedProc) {
		this.executedProc = executedProc;
	}

}
