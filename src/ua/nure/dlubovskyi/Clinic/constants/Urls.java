package ua.nure.dlubovskyi.Clinic.constants;

public class Urls {
	public static final String PAGE_LIST_DOCTORS = "/WEB-INF/jsp/doctors.jsp";
	public static final String PAGE_LIST_PATIENTS = "/WEB-INF/jsp/patients.jsp";
	public static final String PAGE_LIST_NURSES = "/WEB-INF/jsp/nurses.jsp";
	public static final String PAGE_BAD_COMMAND = "/WEB-INF/jsp/badCommand.jsp";
	public static final String PAGE_ADD_STAFF = "/WEB-INF/jsp/addStaff.jsp";
	public static final String PAGE_ADD_PATIENT = "/WEB-INF/jsp/addPatient.jsp";
	public static final String PAGE_LOGIN = "login.jsp";
	public static final String PAGE_PATIENT_CARD = "/WEB-INF/jsp/patient.jsp";

	// redirect

	public static final String REDIRECT_LOGIN_PAGE = "/Clinic/";
	public static final String REDIRECT_DOCTORS_LIST = "controller?command=doctors";
	public static final String REDIRECT_PATIENTS_LIST = "controller?command=patients";
	public static final String REDIRECT_NURSES_LIST = "controller?command=nurses";
	public static final String REDIRECT_ADD_STAFF_PAGE = "controller?command=addStaff";
	public static final String REDIRECT_ADD_PATIENT_PAGE = "controller?command=addPatient";
	public static final String REDIRECT_CLINIC_INFO_PAGE = "controller?command=clinicInfo";
	public static final String REDIRECT_ADD_PATINET = "controller?command=addPatient";
}
