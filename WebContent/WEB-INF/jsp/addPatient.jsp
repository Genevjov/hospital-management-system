<%@include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add patient</title>
</head>
<style>
html {
	background-color: gray;
}

.form {
	position: absolute;
	margin-top: 170px;
	margin-left: 400px;
	background-color: white;
	width: 500px;
	height: 400px;
}

.form ul li {
	list-style: none;
	padding-top: 20px;
}

.form ul li input {
	float: right;
	margin-right: 110px;
	width: 150px;
}

.form ul li select {
	float: right;
	margin-right: 110px;
	width: 150px;
}

.errorMessage {
	position: absolute;
	margin-top: 150px;
	margin-left: 400px;
	width: 500px;
	background-color: red;
	text-align: center;
}
</style>
<body>
	<%@include file="/WEB-INF/jspf/changeLanguage.jspf"%>

	<%@include file="/WEB-INF/jspf/adminHeader.jspf"%>
	<c:if test="${emptyFieldsP}">
		<div class="errorMessage">
			<fmt:message key="addStaff.error" />
		</div>
	</c:if>

	<div class="addPatientForm">
		<form class="form" action="controller?command=addPatient"
			method="POST">
			<ul>
				<li><fmt:message key="table.name" /> <input type="text"
					name="firstName"></li>
				<li><fmt:message key="table.secondName" /> <input type="text"
					name="secondName"></li>
				<li><fmt:message key="table.dateOfBirth" /><input type="date"
					name="dateOfBirth"></li>
				<li><fmt:message key="Doctor" /> <select name="doctor">
						<c:forEach items="${doctors }" var="doctor">
							<option value="${doctor.docId }">${doctor.firstName }
								${doctor.secondName } (
								<fmt:message key="${doctor.specialization.name }" />)
							</option>
						</c:forEach>
				</select></li>
				<li><input type="submit" value="Add"></li>
			</ul>
		</form>
	</div>
</html>