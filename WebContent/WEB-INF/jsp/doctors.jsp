<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>

<%@taglib prefix="custom" tagdir="/WEB-INF/tags"%>
<html>
<head>
<link href="${Cotex }">
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

.doctorsTable td a{
	text-decoration: none;
	color: white;
}
</style>
<body>
	<%@include file="/WEB-INF/jspf/adminHeader.jspf"%>
	<div class="specs">
		<ul>
			<c:forEach items="${specializations }" var="specialization">

				<li><a
					href="controller?command=doctors&spec=${specialization.id }">
						${specialization.name} </a></li>
			</c:forEach>

			<li><a href="controller?command=doctors">All doctors</a></li>

		</ul>

	</div>

	<div class="doctorsTable">
		<table border="1">
			<tr>
				<td><a href="controller?command=doctors&sort=firstName">First
						name</a></td>
				<td><a href="controller?command=doctors&sort=secondName">Second
						name</a></td>
				<td><a href="controller?command=doctors&sort=login">Login</a></td>
				<td><a href="controller?command=doctors&sort=spec">Specialization</a></td>
				<td><a href="controller?command=doctors&sort=patients">Patients
						count</a></td>
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