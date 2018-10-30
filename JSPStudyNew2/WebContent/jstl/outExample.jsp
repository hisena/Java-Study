<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String message = "jstl 연습입니다";
request.setAttribute("message", message);
%>
<c: set var="message" value="jstl <연습>입니다" scope="page"/>

<jsp.usebean id='dog', class="kr.or.kosta.jsp.el.dog" scope="page"/>
<c:set target="${dog }" property="name" value="루니"/> <!-- setProperty 역할  -->
<c:remove var="message"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title></title>
</head>
<body>
${message}
<c:out value="김기정"/><br>
<c:out value="${message}" default="기본 메세지입니다."/>
강아지이름: ${dog.name }
</body>
</html>