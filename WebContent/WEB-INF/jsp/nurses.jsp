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

.nursesTable table a {
	padding-left: 5px;
	text-decoration: none;
	color: white;
}
</style>
</head>
<body>
	<%@include file="/WEB-INF/jspf/changeLanguage.jspf"%>

	<%@include file="/WEB-INF/jspf/adminHeader.jspf"%>
	<div class="nursesTable">
		<table border="1">
			<tr>
				<td><fmt:message key="table.name" /><a
					href="controller?command=nurses&sort=firstNameUp">+</a> <a
					href="controller?command=nurses&sort=firstNameDown">-</a></td>
				<td><fmt:message key="table.secondName" /><a
					href="controller?command=nurses&sort=secondNameUp">+</a> <a
					href="controller?command=nurses&sort=secondNameDown">-</a></td>
				<td><fmt:message key="table.login" /><a
					href="controller?command=nurses&sort=loginUp">+</a> <a
					href="controller?command=nurses&sort=loginDown">-</a></td>
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