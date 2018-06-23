<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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

	<c:if test="${not empty errorMessage }">
		<div class="errorMessage">
			<span><c:out value="${errorMessage }"></c:out></span>
		</div>
	</c:if>
	<div class="loginForm">
		<form action="controller" method="post">
			<ul>
				<li>Plese login</li>
				<li><input type="hidden" name="command" value="login"></li>
				<li>Login: <input type="text" name="login"></li>
				<li>Password <input type="password" name="password"></li>
				<li><input type="submit" value="Login"></li>
			</ul>
		</form>
	</div>
</body>
</html>