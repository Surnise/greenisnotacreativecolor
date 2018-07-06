<%--
  Created by IntelliJ IDEA.
  User: --
  Date: 05.07.2018
  Time: 19:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form name="ToHomePage" method="GET" action="controller">
    <input type="hidden" name="command" value="homepage" />
    <input type="submit" name="submit" value="Submit" />
</form>
${pageContext.request.getSession(true).getAttribute("user")}
${sessionScope.user.firstName}
${sessionScope.user.lastName}
${sessionScope.user.role}
</body>
</html>
