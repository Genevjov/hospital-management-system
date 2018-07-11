<!--Custom nurse table tag-->
<%@tag pageEncoding="UTF-8"%>
<%@tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="nurse" required="true"
	type="ua.nure.dlubovskyi.Clinic.entity.staff.Nurse"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>


<td>${nurse.firstName }</td>
<td>${nurse.secondName }</td>
<td>${nurse.login }</td>
<td>${nurse.executedProc }</td>
