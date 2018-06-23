<%@include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@taglib prefix="custom" tagdir="/WEB-INF/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Nurses</title>
<style>
html {
	background-color: gray;
}

.nursesTable {
	position: absolute;
	margin-top: 150px;
}
</style>
</head>
<body>
	<%@include file="/WEB-INF/jspf/adminHeader.jspf"%>
	<div class="nursesTable">
		<table border="1">
			<tr>
				<td>First name</td>
				<td>Second name</td>
				<td>Login</td>
			</tr>
			<c:forEach items="${nurses }" var="nurse">
				<tr>

					<custom:nurse nurse="${nurse }" />
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>