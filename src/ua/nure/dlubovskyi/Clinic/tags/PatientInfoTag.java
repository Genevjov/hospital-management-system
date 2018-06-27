package ua.nure.dlubovskyi.Clinic.tags;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import ua.nure.dlubovskyi.Clinic.entity.managers.PatientManager;
import ua.nure.dlubovskyi.Clinic.entity.patient.Procedure;

public class PatientInfoTag extends SimpleTagSupport {

	private int id;

	public int getPatient() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public void doTag() throws JspException, IOException {
		JspWriter writer = getJspContext().getOut();
		writer.println(getPatientProcs(id));
	}

	private String getPatientProcs(int id) {
		StringBuilder builder = new StringBuilder();
		List<Procedure> procedures = PatientManager.getProceduresByPatientId(id);
		for (Procedure procedure : procedures) {
			builder.append("<tr>");
			builder.append("<td>");
			builder.append(procedure.getDoctor().getSecondName() + " " + procedure.getDoctor().getFirstName());
			builder.append("</td>");
			builder.append("<td>");
			builder.append(procedure.getTreatmant().getName());
			builder.append("</td>");
			builder.append("<td>");
			builder.append(procedure.getInfo());
			builder.append("</td>");
			builder.append("<td>");
			int status = procedure.getStatus();
			if (status == 1) {
				builder.append("+");
			} else {
				builder.append("-");
			}
			builder.append("</td>");

			builder.append("</tr>");

		}
		return builder.toString();
	}

}