<%@include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="/WEB-INF/res/css/login.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<style>
html {
	font-family: 'PT Sans';
	background-color: #068279;
}

.loginForm {
	position: absolute;
	margin-top: 180px;
	margin-left: 480px;
	background-color: white;
	width: 350px;
	height: 300px;
}

.loginForm ul li {
	list-style: none;
	padding-top: 20px;
}

.loginForm ul li:first-child {
	width: 270px;
	text-align: center;
}

.loginForm ul li:last-child {
	margin-right: 110px;
	margin-top: 20px;
}

.loginForm ul li input {
	float: right;
	margin-right: 30px;
}

.errorMessage {
	position: absolute;
	background: red;
	margin-top: 160px;
	margin-left: 480px;
	width: 350px;
	text-align: center;
	color: white;
}


</style>
<body>
	<%@include file="/WEB-INF/jspf/changeLanguage.jspf"%>
	<c:if test="${error }">
		<div class="errorMessage">
			<span><fmt:message key="login.error" /></span>
		</div>
	</c:if>
	<div class="loginForm">
		<form action="controller" method="post">
			<ul>
				<li><fmt:message key="login.page.title" />
				<li><input type="hidden" name="command" value="login"></li>
				<li><label><fmt:message key="login.login" />:</label> <input
					type="text" name="login"></li>
				<li><label><fmt:message key="login.password" />:</label> <input
					type="password" name="password"></li>
				<li><input type="submit"
					value='<fmt:message key = "login.button"/>'></li>
			</ul>
		</form>
	</div>
</body>
</html>