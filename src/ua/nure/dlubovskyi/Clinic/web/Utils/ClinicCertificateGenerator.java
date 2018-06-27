package ua.nure.dlubovskyi.Clinic.web.Utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import ua.nure.dlubovskyi.Clinic.entity.patient.Patient;
import ua.nure.dlubovskyi.Clinic.entity.patient.Procedure;

/**
 * Util class for saving file with patient information
 * 
 * @author Dlubovskyi Oleg
 *
 */
public class ClinicCertificateGenerator {
	/**
	 * Mathod for generating file with patient information
	 * 
	 * @param patient
	 *            -parient id
	 * @param procedures
	 *            -list of patient procedures
	 * @throws IOException
	 */
	public static void generate(Patient patient, List<Procedure> procedures) throws IOException {
		StringBuilder builder = new StringBuilder();
		builder.append("----------------------------------------\r\n");
		builder.append("To: " + patient.getSecondName() + " " + patient.getFirstName() + "\r\n");
		builder.append(
				"Doctor: " + patient.getDoctor().getSecondName() + " " + patient.getDoctor().getFirstName() + "\r\n");
		builder.append("-----------------------------------------\r\n");
		builder.append("Diagnosis: " + patient.getDiagnosis() + "\r\n");
		builder.append("-----------------------------------------\r\n");
		builder.append("Procedures: \r\n");
		builder.append("|\tTyp\t|\t-Description\t|\tIs done\t|\r\n");
		for (Procedure procedure : procedures) {
			builder.append("|" + procedure.getTreatmant().getName() + "\t|" + procedure.getInfo() + "\t|\t"
					+ (procedure.getStatus() == 1 ? true : false) + "\r\n");
		}
		builder.append("\n----------------------------------------");
		PrintWriter writer = new PrintWriter("D:/projects/Clinic/WebContent/WEB-INF/DischargedPatients/"
				+ patient.getSecondName() + "_" + patient.getFirstName() + ".txt");
		writer.write(builder.toString());
		writer.close();
	}

}
