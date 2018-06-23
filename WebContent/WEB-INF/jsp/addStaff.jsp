<%@include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html">
<title>Add staff</title>
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
}
</style>
<body>
	<%@include file="/WEB-INF/jspf/adminHeader.jspf"%>
	<c:if test="${emptyInput}">
		<div class="errorMessage">хуйня</div>
	</c:if>
	<c:if test="${emptyRole}">
		<div class="errorMessage">хуйня</div>
	</c:if>
	<div class="addStaffForm">
		<form class="form" name="staffForm"
			action="controller?command=addStaff" method="POST">
			<ul>
				<li>First name: <input type="text" name="firstName"></li>
				<li>Second name: <input type="text" name="secondName"></li>
				<li>Login: <input type="text" name="login"></li>
				<li>Password: <input type="password" name="password"></li>
				<li>Repeat password: <input type="password" name="rePassword"></li>
				<li>Role: <select name="role" onchange="checkRole()"
					id="roleSelect">
						<option selected="selected">Admin</option>
						<option>Doctor</option>
						<option>Nurse</option>
				</select></li>
				<li id="docSpecialization">Sepcialization: <select name="spec">
						<c:forEach items="${specializations}" var="spec">
							<option>${spec.name}</option>
						</c:forEach>
				</select></li>
				<li><input type="submit" value="Add"></li>
			</ul>
		</form>

	</div>
</body>
<script type="text/javascript">
	window.onload = checkRole;

	var docSpec = document.getElementById('docSpecialization')
	var roleSelect = document.getElementById('roleSelect')
	function checkRole() {
		if (roleSelect.options[roleSelect.selectedIndex].text == 'Doctor') {
			docSpec.style.visibility = 'visible'
		} else {
			docSpec.style.visibility = 'hidden'
		}
	}
</script>
</html>