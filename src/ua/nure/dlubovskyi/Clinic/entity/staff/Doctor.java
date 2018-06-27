package ua.nure.dlubovskyi.Clinic.entity.staff;

import java.util.List;

import ua.nure.dlubovskyi.Clinic.entity.patient.Patient;

/**
 * Doctor entity class
 * 
 * @author Dlubovskyi Oleg
 *
 */
public class Doctor extends Staff {
	// doctor specification
	private Specialization specialization;
	// doctor's own patients
	private List<Patient> patients;
	private int docId;
	private int patientCount;

	/**
	 * Default constructor
	 * 
	 * @param firstName
	 * @param secondName
	 * @param id
	 * @param role
	 * @param login
	 * @param password
	 * @param specialization
	 */
	public Doctor(String firstName, String secondName, int id, String role, String login, String password,
			Specialization specialization) {
		super(firstName, secondName, role, login, password);
		this.specialization = specialization;
	}

	/**
	 * 
	 * @param firstName
	 * @param secondName
	 * @param specialization
	 */
	public Doctor(String firstName, String secondName, Specialization specialization) {
		super(firstName, secondName, "Doctor");
		this.specialization = specialization;

	}
	/**
	 * 
	 * @param firstName
	 * @param secondName
	 * @param login
	 * @param password
	 * @param specialization
	 */
	public Doctor(String firstName, String secondName, String login, String password, Specialization specialization) {
		super(firstName, secondName, "Doctor", login, password);
		this.specialization = specialization;
	}

	/**
	 * @return the specialization
	 */
	public Specialization getSpecialization() {
		return specialization;
	}

	/**
	 * @param specialization
	 *            the specialization to set
	 */
	public void setSpecialization(Specialization specialization) {
		this.specialization = specialization;
	}

	/**
	 * @return the patients
	 */
	public List<Patient> getPatients() {
		return patients;
	}

	/**
	 * @param patients
	 *            the patients to set
	 */
	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}

	/**
	 * @return the docId
	 */
	public int getDocId() {
		return docId;
	}

	/**
	 * @param docId
	 *            the docId to set
	 */
	public void setDocId(int docId) {
		this.docId = docId;
	}

	/**
	 * @return the patientCount
	 */
	public int getPatientCount() {
		return patientCount;
	}

	/**
	 * @param patientCount
	 *            the patientCount to set
	 */
	public void setPatientCount(int patientCount) {
		this.patientCount = patientCount;
	}
}
