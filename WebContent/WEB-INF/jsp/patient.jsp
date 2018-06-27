<%@include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@ taglib uri="/WEB-INF/patientInfo.tld" prefix="patient"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
</head>
<style>
html {
	background-color: gray;
}

.patientInfo {
	margin-top: 100px;
	background-color: white;
	position: absolute;
	width: 350px;
	height: 300px;
}

.patientInfo ul li {
	list-style: none;
	padding-top: 20px;
}

.patientProcdures {
	position: absolute;
	margin-top: 200px;
	margin-left: 800px;
}
</style>
<body>
	<%@include file="/WEB-INF/jspf/changeLanguage.jspf"%>

	<c:if test="${role eq 'Admin' }">
		<%@include file="/WEB-INF/jspf/adminHeader.jspf"%>
	</c:if>
	<c:if test="${role eq 'Doctor' or 'Nurse' }">
		<%@include file="/WEB-INF/jspf/doctorFunctions.jspf"%>
		<%@include file="/WEB-INF/jspf/staffHeader.jspf"%>
	</c:if>

	<div class="patientInfo">
		<ul>
			<li><fmt:message key="table.name" />: ${patient.firstName }</li>
			<li><fmt:message key="table.secondName" />:
				${patient.secondName }</li>
			<li><fmt:message key="table.dateOfBirth" />:
				${patient.dateOfBirth }</li>
			<li><fmt:message key="Doctor" />: <a
				href="controller?command=doctor&id=${patient.doctor.docId }">${patient.doctor.secondName }
					${patient.doctor.firstName } </a></li>
		</ul>
	</div>

	<div class="patientProcdures">
		<table border="1">
			<tr>
				<td><fmt:message key="Doctor" /></td>
				<td><fmt:message key="table.type" /></td>
				<td><fmt:message key="table.desc" /></td>
				<td><fmt:message key="table.status" /></td>
			</tr>
			<patient:patientProcTable id="${patient.patientId }"></patient:patientProcTable>

		</table>
	</div>
</body>
</html>