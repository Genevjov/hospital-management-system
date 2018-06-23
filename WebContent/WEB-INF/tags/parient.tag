<%@tag pageEncoding="UTF-8"%>
<%@tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="patient" required="true"
	type="ua.nure.dlubovskyi.Clinic.entity.patient.Patient"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<td>${patient.firstName}</td>
<td>${patient.secondName}</td>
<td>${patient.dateOfBirth }</td>
<td>${patient.diagnosis }</td>
<td><a href="controller?command=doctor&id=${patient.doctor.docId }">${patient.doctor.secondName }&nbsp;${patient.doctor.firstName
		}</a></td>
<td><a
	href="controller?command=discharge&patient=${patient.patientId }">Discharge</a></td>
<td><a
	href="controller?command=patientCard&patient=${patient.patientId }">Patient
		card</a></td>