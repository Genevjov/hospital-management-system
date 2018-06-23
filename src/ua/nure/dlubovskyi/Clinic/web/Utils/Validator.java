package ua.nure.dlubovskyi.Clinic.web.Utils;

/**
 * Util class for input validation
 * 
 * @author Dlubovskyi Oleg
 *
 */
public class Validator {
	// regext for validation first name, second name, etc.
	private static final String REGEXP_TEXT_INPUT = "[à-ÿÀ-ß¸¨a-zA-Z0-9-_\\\\.]{1,20}";
	// regext for validation email
	private static final String REGEXP_EMAIL_INPUT = "/.+@.+\\..+/i";

	/**
	 * Static utill mathod for input not empty validation
	 * 
	 * @param input
	 * @return boolean result of regexp matches
	 */
	public static boolean validateInput(String input) {
		return input.matches(REGEXP_TEXT_INPUT);
	}

	/**
	 * Static utill mathod for email input validation
	 * 
	 * @param email
	 * @return boolean result of regexp matches
	 */
	public static boolean validateEmail(String email) {
		return email.matches(REGEXP_EMAIL_INPUT);
	}

	/**
	 * 
	 * @param login
	 * @return
	 */
	public static boolean isValidLogin(String login) {
		// TODO
		return false;
	}
}
