<%--
  Created by IntelliJ IDEA.
  User: --
  Date: 01.07.2018
  Time: 11:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--<form action="params.jsp">--%>
    <%--First name: <input type="text" name="firstname"><br/>--%>
    <%--email 1: <input type="text" name="mail"><br/>--%>
    <%--email 2: <input type="text" name="mail"><br/>--%>
    <%--<input type="submit" name="submit" value="Подтвердить">--%>
<%--</form>--%>

<hr/>
<hr/>
<label>COMMON</label>
<form name="LoginForm" method="POST" action="controller">
    <input type="hidden" name="command" value="login" />
    <label>Login:    <input type="text" name="login" value=""></label><br/><br/>
    <label>Password: <input type="text" name="password" value=""></label><br/>
    <input type="submit" name="submit" value="Submit">
    ${accessDeniedMessage}
</form>

</body>
</html>
