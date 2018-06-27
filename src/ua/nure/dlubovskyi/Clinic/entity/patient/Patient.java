package ua.nure.dlubovskyi.Clinic.entity.patient;

import ua.nure.dlubovskyi.Clinic.entity.AbstractEntity;
import ua.nure.dlubovskyi.Clinic.entity.staff.Doctor;

/**
 * Patient entity
 * 
 * @author Dlubovskyi Oleg
 *
 */
public class Patient extends AbstractEntity {
	private int patientId;
	private Doctor doctor;
	private String diagnosis;
	// this atrib ONLY FOR ADDING NEW USER
	private int doctorId;
	private Procedure procedure;

	/**
	 * Default constuctor
	 * 
	 * @param firstName
	 * @param secondName
	 * @param diagnosis
	 * @param patientId
	 */
	public Patient(String firstName, String secondName, String diagnosis, int patientId, String dateOfBirth) {
		super(firstName, secondName);
		this.setDiagnosis(diagnosis);
		this.setPatientId(patientId);
		this.setDateOfBirth(dateOfBirth);

	}

	/**
	 * Constructor for adding user
	 * 
	 * @param firstName
	 * @param secondName
	 * @param doctorId
	 */
	public Patient(String firstName, String secondName, int doctorId) {
		super(firstName, secondName);
		this.setDoctorId(doctorId);
	}

	/**
	 * @return the doctor
	 */
	public Doctor getDoctor() {
		return doctor;
	}

	/**
	 * @param doctor
	 *            the doctor to set
	 */
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	/**
	 * @return the diagnosis
	 */
	public String getDiagnosis() {
		return diagnosis;
	}

	/**
	 * @param diagnosis
	 *            the diagnosis to set
	 */
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	/**
	 * @return the patientId
	 */
	public int getPatientId() {
		return patientId;
	}

	/**
	 * @param patientId
	 *            the patientId to set
	 */
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	/**
	 * @return the doctorId
	 */
	public int getDoctorId() {
		return doctorId;
	}

	/**
	 * @param doctorId
	 *            the doctorId to set
	 */
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	/**
	 * @return the procedure
	 */
	public Procedure getProcedure() {
		return procedure;
	}

	/**
	 * @param procedure
	 *            the procedure to set
	 */
	public void setProcedure(Procedure procedure) {
		this.procedure = procedure;
	}

}
