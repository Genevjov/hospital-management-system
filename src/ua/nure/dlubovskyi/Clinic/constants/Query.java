package ua.nure.dlubovskyi.Clinic.constants;

public class Query {

	// staff

	public static final String SQL_CARRY_OUT_PROC = "UPDATE procedures SET is_done = 1 WHERE procedure_id = ?";

	public static final String SQL_GET_ALL_TREATMENTS = "SELECT * FROM summary_task.treatmant";

	public static final String SQL_GET_LOGIN_DATA_BY_ID = "SELECT * FROM staff_login_data WHERE staff_login_data.staff_id = ?";

	public static final String SQL_ADD_ADMIN = "INSERT INTO staff(first_name, second_name, role_id) VALUES (?,?,1)";

	public static final String SQL_ADD_NURSE = "INSERT INTO staff(first_name, second_name, role_id) VALUES (?,?,3)";

	public static final String SQL_GET_LAST_INDEX = "SELECT last_insert_id() AS id";

	public static final String SQL_ADD_STAFF_INFO = "INSERT INTO staff(first_name, second_name, role_id) VALUES(?,?,?)";

	public static final String SQL_GET_STAFF_BY_LOGIN = "SELECT * FROM staff INNER JOIN staff_login_data"
			+ " ON staff.staff_id = staff_login_data.staff_id "
			+ "INNER JOIN roles ON staff.role_id = roles.role_id WHERE staff_login_data.login = ?";

	public static final String SQL_GET_ALL_DOCTORS = "SELECT * FROM staff INNER JOIN doctors \r\n"
			+ "		ON staff.staff_id = doctors.staff_id INNER JOIN specialization \r\n"
			+ "			ON doctors.specialization_id = specialization.specialization_id LEFT JOIN staff_login_data\r\n"
			+ "			ON staff_login_data.staff_id = staff.staff_id";

	public static final String SQL_ADD_LOGIN_DATA = "INSERT INTO staff_login_data(staff_id, login, password) VALUES (?,?,?)";

	public static final String SQL_GET_ALL_SPECIALIZATIONS = "SELECT * FROM specialization";

	// doctors

	public static final String SQL_GET_PROCEDURES_FOR_DOCTOR = "select * from procedures \r\n"
			+ "where doctor_id = ? and is_done = 0";

	// all doctors sort
	public static final String SQL_GET_DOCTORS_SORTED_BY_NAME_UP = "SELECT * FROM staff INNER JOIN doctors \r\n"
			+ "	ON staff.staff_id = doctors.staff_id INNER JOIN specialization \r\n"
			+ "	ON doctors.specialization_id = specialization.specialization_id Left JOIN staff_login_data\r\n"
			+ "	ON staff_login_data.staff_id = staff.staff_id order by first_name";

	public static final String SQL_GET_DOCTORS_SORTED_BY_NAME_DOWN = "SELECT * FROM staff INNER JOIN doctors \r\n"
			+ " ON staff.staff_id = doctors.staff_id INNER JOIN specialization \r\n"
			+ " ON doctors.specialization_id = specialization.specialization_id Left JOIN staff_login_data \r\n"
			+ " ON staff_login_data.staff_id = staff.staff_id order by first_name DESC";

	public static final String SQL_GET_DOCTORS_SORTED_BY_SECOND_NAME_UP = "SELECT * FROM staff INNER JOIN doctors \r\n"
			+ " ON staff.staff_id = doctors.staff_id INNER JOIN specialization \r\n"
			+ " ON doctors.specialization_id = specialization.specialization_id Left JOIN staff_login_data \r\n"
			+ " ON staff_login_data.staff_id = staff.staff_id order by second_name ";

	public static final String SQL_GET_DOCTORS_SORTED_BY_SECOND_NAME_DOWN = "SELECT * FROM staff INNER JOIN doctors \r\n"
			+ " ON staff.staff_id = doctors.staff_id INNER JOIN specialization \r\n"
			+ " ON doctors.specialization_id = specialization.specialization_id Left JOIN staff_login_data \r\n"
			+ " ON staff_login_data.staff_id = staff.staff_id order by second_name DESC";

	public static final String SQL_GET_DOCTORS_SORTED_BY_LOGIN_UP = "SELECT * FROM staff INNER JOIN doctors \r\n"
			+ " ON staff.staff_id = doctors.staff_id INNER JOIN specialization \r\n"
			+ " ON doctors.specialization_id = specialization.specialization_id Left JOIN staff_login_data \r\n"
			+ " ON staff_login_data.staff_id = staff.staff_id order by login";

	public static final String SQL_GET_DOCTORS_SORTED_BY_LOGIN_DOWN = "SELECT * FROM staff INNER JOIN doctors \r\n"
			+ " ON staff.staff_id = doctors.staff_id INNER JOIN specialization \r\n"
			+ " ON doctors.specialization_id = specialization.specialization_id Left JOIN staff_login_data \r\n"
			+ " ON staff_login_data.staff_id = staff.staff_id order by login DESC";

	// by specs doctors sort
	public static final String SQL_SPC_GET_DOCTORS_SORTED_BY_NAME_UP = "SELECT * FROM staff INNER JOIN doctors \r\n"
			+ "	ON staff.staff_id = doctors.staff_id INNER JOIN specialization \r\n"
			+ "	ON doctors.specialization_id = specialization.specialization_id Left JOIN staff_login_data\r\n"
			+ "	ON staff_login_data.staff_id = staff.staff_id where specialization.specialization_id = ? order by first_name";

	public static final String SQL_SPC_GET_DOCTORS_SORTED_BY_NAME_DOWN = "SELECT * FROM staff INNER JOIN doctors \r\n"
			+ " ON staff.staff_id = doctors.staff_id INNER JOIN specialization \r\n"
			+ " ON doctors.specialization_id = specialization.specialization_id Left JOIN staff_login_data \r\n"
			+ " ON staff_login_data.staff_id = staff.staff_id where specialization.specialization_id = ? order by first_name DESC";

	public static final String SQL_GET_SPC_DOCTORS_SORTED_BY_SECOND_NAME_UP = "SELECT * FROM staff INNER JOIN doctors \r\n"
			+ " ON staff.staff_id = doctors.staff_id INNER JOIN specialization \r\n"
			+ " ON doctors.specialization_id = specialization.specialization_id Left JOIN staff_login_data \r\n"
			+ " ON staff_login_data.staff_id = staff.staff_id where specialization.specialization_id = ? order by second_name ";

	public static final String SQL_GET_SPC_DOCTORS_SORTED_BY_SECOND_NAME_DOWN = "SELECT * FROM staff INNER JOIN doctors \r\n"
			+ " ON staff.staff_id = doctors.staff_id INNER JOIN specialization \r\n"
			+ " ON doctors.specialization_id = specialization.specialization_id Left JOIN staff_login_data \r\n"
			+ " ON staff_login_data.staff_id = staff.staff_id where specialization.specialization_id = ? order by second_name DESC";

	public static final String SQL_GET_SPC_DOCTORS_SORTED_BY_LOGIN_UP = "SELECT * FROM staff INNER JOIN doctors \r\n"
			+ " ON staff.staff_id = doctors.staff_id INNER JOIN specialization \r\n"
			+ " ON doctors.specialization_id = specialization.specialization_id Left JOIN staff_login_data \r\n"
			+ " ON staff_login_data.staff_id = staff.staff_id where specialization.specialization_id = ? order by login";

	public static final String SQL_GET_SPC_DOCTORS_SORTED_BY_LOGIN_DOWN = "SELECT * FROM staff INNER JOIN doctors \r\n"
			+ " ON staff.staff_id = doctors.staff_id INNER JOIN specialization \r\n"
			+ " ON doctors.specialization_id = specialization.specialization_id Left JOIN staff_login_data \r\n"
			+ " ON staff_login_data.staff_id = staff.staff_id where specialization.specialization_id = ? order by login DESC";

	public static final String SQL_GET_DOCTORS_BY_SPEC_ID = "SELECT * FROM staff INNER JOIN doctors \r\n"
			+ "ON staff.staff_id = doctors.staff_id\r\n" + "INNER JOIN specialization \r\n"
			+ "ON doctors.specialization_id = specialization.specialization_id INNER JOIN staff_login_data\r\n"
			+ "ON staff_login_data.staff_id = staff.staff_id WHERE doctors.specialization_id = ?";

	public static final String SQL_GET_DOCTOR_BY_ID = "SELECT * FROM staff INNER JOIN doctors ON staff.staff_id = doctors.staff_id\r\n"
			+ "INNER JOIN specialization ON doctors.specialization_id = specialization.specialization_id\r\n"
			+ "WHERE doctors.doctor_id = ?";

	public static final String SQL_SET_PROC_TO_PATIENT = "INSERT INTO procedures (doctor_id,patient_id,treatment_id,info) VALUES (?,?,?,?)";

	public static final String SQL_CREATE_DIAGNOSIS = "INSERT INTO diagnosis (diagnosis_name) VALUES (?)";

	public static final String SQL_SET_DIAGNOSIS = "UPDATE patients_info SET diagnosis_id = ? WHERE doctor_id = ? AND patient_id = ?";

	public static final String SQL_GET_DOC_ID_BY_STAFF_ID = "SELECT * FROM doctors WHERE staff_id = ?";

	public static final String SQL_ADD_DOC_INFO = "INSERT INTO doctors(staff_id, specialization_id) VALUES (?,?)";

	public static final String SQL_GET_SPEC_ID_BY_NAME = "SELECT specialization_id from specialization WHERE specialization_name = ?";

	public static final String SQL_GET_TREATMENT_BY_ID = "SELECT * FROM treatmant WHERE treatmant.treatment_id = ?";
	// nurses

	public static final String SQL_GET_ALL_NURSES = "SELECT * FROM staff inner join staff_login_data on\r\n"
			+ "staff.staff_id = staff_login_data.staff_id\r\n" + " where staff.role_id = 3";

	// nurses sort
	public static final String SQL_GET_NURSES_SORTED_BY_NAME_UP = "select * from staff inner join staff_login_data on staff.staff_id = staff_login_data.staff_id where\r\n"
			+ "role_id = 3 order by first_name";

	public static final String SQL_GET_NURSES_SORTED_BY_NAME_DOWN = "select * from staff inner join staff_login_data on staff.staff_id = staff_login_data.staff_id where\r\n"
			+ "role_id = 3 order by first_name desc";

	public static final String SQL_GET_NURSES_SORTED_BY_SECOND_NAME_UP = "select * from staff inner join staff_login_data on staff.staff_id = staff_login_data.staff_id where\r\n"
			+ "role_id = 3 order by second_name";
	public static final String SQL_GET_NURSES_SORTED_BY_SECOND_NAME_DOWN = "select * from staff inner join staff_login_data on staff.staff_id = staff_login_data.staff_id where\r\n"
			+ "role_id = 3 order by second_name desc";

	public static final String SQL_GET_NUSRES_SORTED_BY_LOGIN_UP = "select * from staff inner join staff_login_data on staff.staff_id = staff_login_data.staff_id where\r\n"
			+ "role_id = 3 order by login";
	public static final String SQL_GET_NURSES_SORTED_BY_LOGIN_DOWN = "select * from staff inner join staff_login_data on staff.staff_id = staff_login_data.staff_id where\r\n"
			+ "role_id = 3 order by login desc";
	// patient

	public static final String SQL_GET_ALL_PATIENTS = "SELECT * FROM summary_task.patients INNER JOIN patients_info ON\r\n"
			+ "patients.patient_id = patients_info.patient_id\r\n"
			+ "LEFT JOIN diagnosis ON patients_info.diagnosis_id = diagnosis.diagnosis_id";

	public static final String SQL_GET_PATIENT_BY_ID = "SELECT * FROM summary_task.patients INNER JOIN patients_info ON\r\n"
			+ "patients.patient_id = patients_info.patient_id\r\n"
			+ "LEFT JOIN diagnosis ON patients_info.diagnosis_id = diagnosis.diagnosis_id"
			+ " WHERE patients.patient_id = ?";

	public static final String SQL_GET_PATIENTS_SORT_BY_NAME_UP = "Select * from patients inner join patients_info on\r\n"
			+ "patients.patient_id = patients_info.patient_id \r\n"
			+ "left join diagnosis on diagnosis.diagnosis_id = patients_info.diagnosis_id\r\n" + "order by first_name ";

	public static final String SQL_GET_PATIENTS_SORT_BY_NAME_DOWN = "Select * from patients inner join patients_info on\r\n"
			+ "patients.patient_id = patients_info.patient_id \r\n"
			+ "left join diagnosis on diagnosis.diagnosis_id = patients_info.diagnosis_id\r\n"
			+ "order by first_name DESC";

	public static final String SQL_GET_PATIENTS_SORT_BY_SECOND_NAME_UP = "Select * from patients inner join patients_info on\r\n"
			+ "patients.patient_id = patients_info.patient_id \r\n"
			+ "left join diagnosis on diagnosis.diagnosis_id = patients_info.diagnosis_id\r\n"
			+ "order by second_name ";

	public static final String SQL_GET_PATIENTS_SORT_BY_SECOND_NAME_DOWN = "Select * from patients inner join patients_info on\r\n"
			+ "patients.patient_id = patients_info.patient_id \r\n"
			+ "left join diagnosis on diagnosis.diagnosis_id = patients_info.diagnosis_id\r\n"
			+ "order by second_name DESC";

	public static final String SQL_GET_PATIENTS_SORT_BY_AGE_UP = "Select * from patients inner join patients_info on\r\n"
			+ "patients.patient_id = patients_info.patient_id \r\n"
			+ "left join diagnosis on diagnosis.diagnosis_id = patients_info.diagnosis_id\r\n"
			+ "order by date_of_birth ";

	public static final String SQL_GET_PATIENTS_SORT_BY_AGE_DOWN = "Select * from patients inner join patients_info on\r\n"
			+ "patients.patient_id = patients_info.patient_id \r\n"
			+ "left join diagnosis on diagnosis.diagnosis_id = patients_info.diagnosis_id\r\n"
			+ "order by date_of_birth DESC";

	public static final String SQL_GET_PATIENTS_SORT_BY_DIAGNOSIS_UP = "Select * from patients inner join patients_info on\r\n"
			+ "patients.patient_id = patients_info.patient_id \r\n"
			+ "left join diagnosis on diagnosis.diagnosis_id = patients_info.diagnosis_id\r\n"
			+ "order by diagnosis_name ";

	public static final String SQL_GET_PATIENTS_SORT_BY_DIAGNOSIS_DOWN = "Select * from patients inner join patients_info on\r\n"
			+ "patients.patient_id = patients_info.patient_id \r\n"
			+ "left join diagnosis on diagnosis.diagnosis_id = patients_info.diagnosis_id\r\n"
			+ "order by diagnosis_name DESC";

	public static final String SQL_GET_PATIENTS_BY_DOCTOR_ID = "SELECT * FROM patients_info WHERE patients_info.doctor_id = ?";

	public static final String SQL_COUNT_PATIENTS_BY_DOCTOR_ID = "SELECT COUNT(*) FROM patients_info WHERE patients_info.doctor_id = ?";

	public static final String SQL_ADD_NEW_PATIENT = "INSERT INTO patients(first_name, second_name, date_of_birth) VALUES (?,?,?)";

	public static final String SQL_SET_DOCTOR_TO_PATIENT = "INSERT INTO patients_info(patient_id, doctor_id) VALUES(?,?)";

	public static final String SQL_GET_PATIENTS_FOR_NURSE = "select * from patients inner join patients_info on patients.patient_id = patients_info.patient_id\r\n"
			+ "inner join procedures on procedures.patient_id = patients.patient_id \r\n"
			+ "inner join diagnosis on patients_info.diagnosis_id = diagnosis.diagnosis_id\r\n"
			+ "inner join treatmant on treatmant.treatment_id = procedures.treatment_id\r\n"
			+ "where procedures.is_done = 0 and treatment_name != 'Operation'";

	public static final String SQL_GET_PROCEDURES_BY_PATIENT_ID = "SELECT * FROM procedures WHERE procedures.patient_id = ?";

	public static final String SQL_DELETE_PATIENT_INFO = "DELETE  FROM patient_info WHERE patient_id= ?";
	public static final String SQL_DELETE_PATIENT = "DELETE  FROM patients WHERE patient_id= ?";
	public static final String SQL_DELETE_PROC = "DELETE  FROM procedures WHERE patient_id= ?";

}
