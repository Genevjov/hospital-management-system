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
}
</style>
<body>
	<c:if test="${role eq 'Admin' }">
		<%@include file="/WEB-INF/jspf/adminHeader.jspf"%>
	</c:if>
	<div class=patientsTable>
		<table border="1">
			<tr>
				<td><a href="/controller?command=patients&sort=firstName">First
						name</a></td>
				<td><a href="/controller?command=patients&sort=secondName">Second
						name</a></td>
				<td><a href="/controller?command=patients&sort=dateOfBirth">Date
						of birth</a></td>
				<td><a href="/controller?command=patients&sort=diagnosis">Diagnosis</a></td>
				<td><a href="/controller?command=patients&sort=doctor">Doctor</a></td>
				<td>Discharge</td>
				<td>Medical card</td>
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