<%@tag pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="patient" required="true"
	type="ua.nure.dlubovskyi.Clinic.entity.patient.Patient"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<td>${patient.firstName}</td>
<td>${patient.secondName}</td>
<td>${patient.dateOfBirth }</td>
<td>${patient.diagnosis }</td>
<c:if test="${ role ne 'Admin' }">
	<td>${patient.doctor.secondName }&nbsp;${patient.doctor.firstName}</td>
</c:if>
<c:if test="${role eq 'Admin' }">
	<td><a href="controller?command=doctor&id=${doctor.docId }">${patient.doctor.secondName}&nbsp;${patient.doctor.firstName}</a></td>
</c:if>

<td>
	<form action="controller?command=discharge" method="post">
		<input type="hidden" name="patient" value="${patient.patientId }">
		<input type="submit" value='<fmt:message key = "table.discharge" />'>
	</form>
</td>
<td><a
	href="controller?command=patientCard&patient=${patient.patientId }"><fmt:message key="table.card"/></a></td>