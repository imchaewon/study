<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2022/08/02
  Time: 1:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>Title</title>
</head>
<body>
	${data}
	<br><br>
	<c:forEach var="a" items="${data}">
		${a.dispName}
	</c:forEach>
</body>
</html>
