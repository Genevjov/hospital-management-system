<%@include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@taglib prefix="custom" tagdir="/WEB-INF/tags/"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Patients</title>
</head>
<style>
html {
	background-color: gray;
}

.patientsTable {
	position: absolute;
	margin-top: 150px;
}

.patientsTable a {
	text-decoration: none;
	color: white;
	padding-left: 5px;
}
</style>
<body>
	<%@include file="/WEB-INF/jspf/changeLanguage.jspf"%>

	<c:if test="${role eq 'Admin' }">
		<%@include file="/WEB-INF/jspf/adminHeader.jspf"%>
	</c:if>
	<c:if test="${role eq 'Doctor' or 'Nurse' }">
		<%@include file="/WEB-INF/jspf/staffHeader.jspf"%>
	</c:if>
	<div class=patientsTable>
		<table border="1">
			<tr>
				<td><fmt:message key="table.name" /><a
					href="controller?command=patients&sort=firstNameUp">+</a><a
					href="controller?command=patients&sort=firstNameDown">-</a></td>
				<td><fmt:message key="table.secondName" /><a
					href="controller?command=patients&sort=secondNameUp">+</a><a
					href="controller?command=patients&sort=secondNameDown">-</a></td>
				<td><fmt:message key="table.dateOfBirth" /><a
					href="controller?command=patients&sort=ageNameUp">+</a><a
					href="controller?command=patients&sort=ageDown">-</a></td>
				<td><fmt:message key="table.diagnosis" /><a
					href="controller?command=patients&sort=diagnosisUp">+</a><a
					href="controller?command=patients&sort=diagnosisDown">-</a></td>
				<td><fmt:message key="Doctor" /></td>
				<td><fmt:message key="table.discharge" /></td>
				<td><fmt:message key="table.card" /></td>
			</tr>
			<c:forEach items="${patients }" var="patient">
				<tr>
					<custom:parient patient="${patient }" />
				</tr>
			</c:forEach>
		</table>


	</div>
</body>
</html>