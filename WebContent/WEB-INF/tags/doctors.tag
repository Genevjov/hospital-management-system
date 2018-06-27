<%@tag pageEncoding="UTF-8"%>
<%@tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="doctor" required="true"
	type="ua.nure.dlubovskyi.Clinic.entity.staff.Doctor"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%-- <td>${doctor.login}</td> --%>
<td>${doctor.firstName}</td>
<td>${doctor.secondName}</td>
<td>${doctor.login}</td>
<td><fmt:message key="${doctor.specialization.name}" /></td>
<td>${doctor.patientCount }</td>
<td><a href="controller?command=doctor&id=${doctor.docId }">Info</a></td>