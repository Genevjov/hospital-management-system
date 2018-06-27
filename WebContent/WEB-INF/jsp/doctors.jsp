<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>

<%@taglib prefix="custom" tagdir="/WEB-INF/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Doctors</title>
</head>
<style>
html {
	background-color: gray;
}

.specs {
	position: absolute;
	margin-top: 80px;
	margin-left: -60px;
}

.specs li {
	display: inline;
	list-style: none;
	padding-left: 50px;
	text-decoration: none;
}

.specs li a {
	text-decoration: none;
	color: white;
}

.doctorsTable {
	position: absolute;
	margin-top: 150px;
}

.doctorsTable td a {
	text-decoration: none;
	color: white;
	padding-left: 5px;
	]
}
</style>
<body>
	<%@include file="/WEB-INF/jspf/changeLanguage.jspf"%>

	<%@include file="/WEB-INF/jspf/adminHeader.jspf"%>
	<div class="specs">
		<ul>
			<c:forEach items="${specializations }" var="specialization">

				<li><a
					href="controller?command=doctors&spec=${specialization.id }"> <fmt:message
							key="${specialization.name}" />
				</a></li>
			</c:forEach>

			<li><a href="controller?command=doctors"><fmt:message key="admin.allDoctors"/></a></li>

		</ul>

	</div>

	<div class="doctorsTable">
		<table border="1">
			<tr>
				<td><fmt:message key="table.name" /><a
					href="controller?command=doctors&spec=${specId }&sort=firstNameUp">+</a><a
					href="controller?command=doctors&spec=${specId }&sort=firstNameDown">-</a></td>
				<td><fmt:message key="table.secondName" /><a
					href="controller?command=doctors&spec=${specId }&sort=secondNameUp">+</a><a
					href="controller?command=doctors&spec=${specId }&sort=secondNameDown">-</a></td>
				<td><fmt:message key="table.login" /><a
					href="controller?command=doctors&spec=${specId }&sort=LoginUp">+</a><a
					href="controller?command=doctors&spec=${specId }&sort=LoginDown">-</a></td>
				<td><fmt:message key="table.spec" /></td>
				<td><fmt:message key="table.patientsCount" /><a
					href="controller?command=doctors&spec=${specId }&sort=patientsUp">+</a><a
					href="controller?command=doctors&spec=${specId }&sort=patientsDown">-</a></td>
				<td><fmt:message key="table.info" /></td>
			</tr>
			<c:forEach items="${doctors }" var="doctor">
				<tr>
					<custom:doctors doctor="${doctor }" />
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>