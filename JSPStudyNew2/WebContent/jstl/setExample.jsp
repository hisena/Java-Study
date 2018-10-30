<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String message = "jstl 연습입니다";
request.setAttribute("message", message);
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title></title>
</head>
<body>
${message}
<c:out value="김기정"/><br>
<c:out value="${message} }" default="기본 메세지입니다."/>
d=
</body>
</html>