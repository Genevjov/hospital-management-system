<%@include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@taglib prefix="custom" tagdir="/WEB-INF/tags/"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Patients</title>
</head>
<style>
html {
	background-color: gray;
}

.patients {
	position: absolute;
	margin-top: 100px;
	margin-left: 400px;
}
</style>
<body>
	<%@include file="/WEB-INF/jspf/changeLanguage.jspf"%>
	<%@include file="/WEB-INF/jspf/staffHeader.jspf"%>

	<div class="patients">
		<table border="1">
			<tr>
				<td><fmt:message key="table.name" /></td>
				<td><fmt:message key="table.secondName" /></td>
				<td><fmt:message key="table.dateOfBirth" /></td>
				<td><fmt:message key="table.type" /></td>
				<td><fmt:message key="table.desc" /></td>
				<td><fmt:message key="table.status" /></td>
			<tr>
				<c:forEach items="${patients}" var="patient">

					<custom:nursePatients patient="${patient}"></custom:nursePatients>
				</c:forEach>
		</table>

	</div>
</body>
</html>