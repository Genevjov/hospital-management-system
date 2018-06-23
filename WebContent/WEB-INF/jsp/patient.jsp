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
	<%-- 	<c:if test="${role eq 'Admin' }"> --%>
	<%@include file="/WEB-INF/jspf/adminHeader.jspf"%>
	<%-- 	</c:if> --%>

	<div class="patientInfo">
		<ul>
			<li>First name: ${patient.firstName }</li>
			<li>Second name: ${patient.secondName }</li>
			<li>Date of birth: ${patient.dateOfBirth }</li>
			<li>Doctor: <a
				href="controller?command=doctor&id=${patient.doctor.docId }">${patient.doctor.secondName }
					${patient.doctor.firstName } </a></li>
		</ul>
	</div>

	<div class="patientProcdures">
		<table border="1">
			<tr>
				<td>Doctor</td>
				<td>Type</td>
				<td>Info</td>
				<td>Is done</td>
			</tr>
			<patient:patientProcTable id="${patient.patientId }"></patient:patientProcTable>

		</table>
	</div>
</body>
</html>