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
	<c:if test="${emptyInput}">
		<div class="errorMessage">
			<fmt:message key="addStaff.error" />
		</div>
	</c:if>
	<div class="addStaffForm">
		<form class="form" name="staffForm"
			action="controller?command=addStaff" method="POST">
			<ul>
				<li><fmt:message key="table.name" /><input type="text"
					name="firstName"></li>
				<li><fmt:message key="table.secondName" /> <input type="text"
					name="secondName"></li>
				<li><fmt:message key="login.login" /> <input type="text"
					name="login"></li>
				<li><fmt:message key="login.password" /> <input
					type="password" name="password"></li>
				<li><fmt:message key="login.repeatPass" /> <input
					type="password" name="rePassword"></li>
				<li><fmt:message key="table.role" /><select name="role"
					onchange="checkRole()" id="roleSelect">
						<option selected="selected" value="Admin"><fmt:message
								key="Admin" /></option>
						<option value="Doctor"><fmt:message key="Doctor" /></option>
						<option value="Nurse"><fmt:message key="Nurse" /></option>
				</select></li>
				<li id="docSpecialization"><fmt:message key="table.spec" />: <select
					name="spec">
						<c:forEach items="${specializations}" var="spec">
							<option value="${spec.name }"><fmt:message
									key="${spec.name }" /></option>
						</c:forEach>
				</select></li>
				<li><input type="submit" value="<fmt:message key="add" />"></li>
			</ul>
		</form>

	</div>
</body>
<script type="text/javascript">
	window.onload = checkRole;

	var docSpec = document.getElementById('docSpecialization')
	var roleSelect = document.getElementById('roleSelect')
	function checkRole() {
		if (roleSelect.selectedIndex == 1) {
			docSpec.style.visibility = 'visible'
		} else {
			docSpec.style.visibility = 'hidden'
		}
	}
</script>
</html>