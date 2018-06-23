package ua.nure.dlubovskyi.Clinic.constants;

public class Query {

	// staff

	public static final String SQL_ADD_ADMIN = "INSERT INTO staff(first_name, second_name, role_id) VALUES (?,?,1)";

	public static final String SQL_ADD_NURSE = "INSERT INTO staff(first_name, second_name, role_id) VALUES (?,?,3)";

	public static final String SQL_GET_LAST_INDEX = "SELECT last_insert_id() AS id";

	public static final String SQL_ADD_STAFF_INFO = "INSERT INTO staff(first_name, second_name, role_id) VALUES(?,?,?)";

	public static final String SQL_GET_STAFF_BY_LOGIN = "SELECT * FROM staff INNER JOIN staff_login_data"
			+ " ON staff.staff_id = staff_login_data.staff_id "
			+ "INNER JOIN roles ON staff.role_id = roles.role_id WHERE staff_login_data.login = ?";

	public static final String SQL_GET_ALL_DOCTORS = "SELECT * from staff INNER JOIN doctors"
			+ " ON staff.staff_id = doctors.staff_id "
			+ "INNER JOIN specialization ON doctors.specialization_id = specialization.specialization_id";

	public static final String SQL_ADD_LOGIN_DATA = "INSERT INTO staff_login_data(staff_id, login, password) VALUES (?,?,?)";

	public static final String SQL_GET_ALL_SPECIALIZATIONS = "SELECT * FROM specialization";

	// doctors

	public static final String SQL_GET_DOCTORS_BY_SPEC_ID = "SELECT * FROM staff INNER JOIN doctors \r\n"
			+ "ON staff.staff_id = doctors.staff_id\r\n" + "INNER JOIN specialization \r\n"
			+ "ON doctors.specialization_id = specialization.specialization_id INNER JOIN staff_login_data\r\n"
			+ "ON staff_login_data.staff_id = staff.staff_id WHERE doctors.specialization_id = ?";

	public static final String SQL_GET_DOCTOR_BY_ID = "SELECT * FROM staff INNER JOIN doctors ON staff.staff_id = doctors.staff_id\r\n"
			+ "INNER JOIN specialization ON doctors.specialization_id = specialization.specialization_id\r\n"
			+ "WHERE doctors.doctor_id = ?";

	public static final String SQL_ADD_DOC_INFO = "INSERT INTO doctors(staff_id, specialization_id) VALUES (?,?)";

	public static final String SQL_GET_SPEC_ID_BY_NAME = "SELECT specialization_id from specialization WHERE specialization_name = ?";

	public static final String SQL_GET_TREATMENT_BY_ID = "SELECT * FROM treatmant WHERE treatmant.treatment_id = ?";
	// nurses

	public static final String SQL_GET_ALL_NURSES = "SELECT * FROM staff inner join staff_login_data on\r\n"
			+ "staff.staff_id = staff_login_data.staff_id\r\n" + " where staff.role_id = 3";
	// patient
	public static final String SQL_GET_ALL_PATIENTS = "SELECT * FROM summary_task.patients INNER JOIN patients_info ON\r\n"
			+ "patients.patient_id = patients_info.patient_id\r\n"
			+ "LEFT JOIN diagnosis ON patients_info.patient_id = diagnosis.diagnosis_id";

	public static final String SQL_GET_PATIENT_BY_ID = "SELECT * FROM summary_task.patients INNER JOIN patients_info ON\r\n"
			+ "patients.patient_id = patients_info.patient_id\r\n"
			+ "LEFT JOIN diagnosis ON patients_info.patient_id = diagnosis.diagnosis_id"
			+ " WHERE patients.patient_id = ?";

	public static final String SQL_ADD_NEW_PATIENT = "INSERT INTO patients(first_name, second_name, date_of_birth) VALUES (?,?,?)";

	public static final String SQL_SET_DOCTOR_TO_PATIENT = "INSERT INTO patients_info(patient_id, doctor_id) VALUES(?,?)";

	public static final String SQL_GET_PROCEDURES_BY_PATIENT_ID = "SELECT * FROM procedures WHERE procedures.patient_id = ?";
}
