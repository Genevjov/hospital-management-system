<%@tag pageEncoding="UTF-8"%>
<%@tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="patient" required="true"
	type="ua.nure.dlubovskyi.Clinic.entity.patient.Patient"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<tr>
	<td>${patient.firstName }</td>
	<td>${patient.secondName }</td>
	<td>${patient.dateOfBirth }</td>
	<td><fmt:message key="${patient.procedure.treatmant.name }" /></td>
	<td>${patient.procedure.info }</td>
	<td><form action="controller?command=carryOutProcedure"
			method="post">
			<input type="hidden" name="patient" value="${patient.patientId }">
			<input type="hidden" name="proc" value="${patient.procedure.id }">
			<c:if test="${role eq 'Doctor' }">
				<input type="hidden" name="doctor" value="${staff.id}">
			</c:if>
			<c:if test="${role eq 'Nurse' }">
				<input type="hidden" name="nurse" value="${staff.id}">
			</c:if>
			<input type="submit" value="<fmt:message key="carryOut" />">
		</form></td>


</tr>