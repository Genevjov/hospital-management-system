<%@tag pageEncoding="UTF-8"%>
<%@tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="patient" required="true"
	type="ua.nure.dlubovskyi.Clinic.entity.patient.Patient"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<td>${patient.firstName}</td>
<td>${patient.secondName}</td>
<td>${patient.dateOfBirth }</td>
<td>${patient.diagnosis }</td>
<td>
	<form action="controller?command=discharge" method="post">
		<input type="hidden" name="patient" value="${patient.patientId }">
		<input type="hidden" name="doctor" value="${staff.id }"> <input
			type="submit" value='<fmt:message key = "table.discharge" />'>
	</form>
<td><a
	href="controller?command=patientCard&patient=${patient.patientId }"><fmt:message
			key="table.card" /></a></td>