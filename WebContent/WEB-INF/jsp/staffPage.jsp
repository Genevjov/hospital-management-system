<%@include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@taglib prefix="custom" tagdir="/WEB-INF/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>${doctor.secondName }${doctor.firstName}</title>
</head>
<style>
html {
	background-color: gray;
}

.staffInfo {
	position: absolute;
	margin-top: 200px;
	margin-left: 100px;
	width: 350px;
	height: 300px;
	background-color: white;
}

.staffInfo ul li {
	list-style: none;
	padding-top: 20px;
}

.patients {
	position: absolute;
	margin-top: 200px;
	margin-left: 600px;
}

.patients table a {
	text-decoration: none;
	color: white;
}
</style>
<body>
	<%@include file="/WEB-INF/jspf/changeLanguage.jspf"%>

	<%@include file="/WEB-INF/jspf/adminHeader.jspf"%>
	<!-- info -->
	<div class="staffInfo">
		<ul>
			<li><fmt:message key="table.name" />: ${doctor.firstName }</li>
			<li><fmt:message key="table.secondName" />: ${doctor.secondName }</li>
			<li><fmt:message key="table.spec" />: <fmt:message
					key="${doctor.specialization.name}" /></li>
			<li><fmt:message key="login.login" />: ${doctor.login }</li>
			<li><fmt:message key="login.password" />: ${doctor.password }</li>
		</ul>
	</div>
	<!-- patients -->
	<div class="patients">
		<table border="1">
			<tr>
				<td><fmt:message key="table.name" /></td>
				<td><fmt:message key="table.secondName" /></td>
				<td><fmt:message key="table.dateOfBirth" /></td>
				<td><fmt:message key="table.diagnosis" /></td>
				<td><fmt:message key="table.discharge" /></td>
				<td><fmt:message key="table.card" /></td>

			</tr>
			<c:forEach items="${patients }" var="patient">
				<tr>
					<custom:doctorPagePratients patient="${patient }" />
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>