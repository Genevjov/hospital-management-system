package ua.nure.dlubovskyi.Clinic.entity.patient;

import ua.nure.dlubovskyi.Clinic.entity.staff.Doctor;

/**
 * Procedure entit
 * 
 * @author Dlubovskyi Oleg
 *
 */
public class Procedure {
	private int id;
	private Doctor doctor;
	private String info;
	private Treatmant treatmant;
	private int status;

	/**
	 * Default constructor
	 * 
	 * @param doctor
	 * @param info
	 * @param treatmant
	 */
	public Procedure(int id, Doctor doctor, String info, Treatmant treatmant, int status) {
		this.id = id;
		this.doctor = doctor;
		this.info = info;
		this.treatmant = treatmant;
		this.setStatus(status);
	}

	/**
	 * @see Treatmant
	 * @return the treatmant
	 */
	public Treatmant getTreatmant() {
		return treatmant;
	}

	/**
	 * @see Treatmant
	 * 
	 * @param treatmant
	 *            the treatmant to set
	 */
	public void setTreatmant(Treatmant treatmant) {
		this.treatmant = treatmant;
	}

	/**
	 * @return the info
	 */
	public String getInfo() {
		return info;
	}

	/**
	 * @param info
	 *            the info to set
	 */
	public void setInfo(String info) {
		this.info = info;
	}

	/**
	 * @see Doctor
	 * @return the doctor
	 */
	public Doctor getDoctor() {
		return doctor;
	}

	/**
	 * @see Doctor
	 * @param doctor
	 *            the doctor to set
	 */
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
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
