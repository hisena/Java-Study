<%@page import="java.util.Calendar"%>
<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>EL 디폴트객체 11개</title>
</head>
<body>
<%
// 테스트를 위한 Scope객체에 데이터 저장
String today = String.format("%1$tF %1$tT", Calendar.getInstance());
request.setAttribute("today", today);
session.setAttribute("id", "bangry");
String[] names = {"김기정", "박기정", "최기정"};
%>
<%=request.getAttribute("today") %>
${requestScope.today}<br>
${today}<br>

<%=session.getAttribute("id") %>
<%=pageContext.findAttribute("id") %>
${id}<br>

<%--이거는 접근이 안됨, String names는 지역변수이기 때문에 접근 안 됨
맵에 저장된 것만 접근할 수 있는데 이건 맵에 저장된 값이 아닌 지역변수니까 --%>
${names[0]}, ${names[1]}, ${names[2]}<br>

<%--
<jsp:getProperty property="id" name="student"/>
<jsp:getProperty property="name" name="student"/>
<jsp:getProperty property="dog" name="student"/>
--%>

${student.id},
${student.name}, 
${student.dog.name}<br>

<jsp:useBean id="dog" class="kr.or.kosta.jsp.el.Dog" scope="page"></jsp:useBean>
<jsp:setProperty property="name" name="dog" value="dubu"/>


<jsp:useBean id="student" class="kr.or.kosta.jsp.el.Student" scope="page"></jsp:useBean>
<jsp:setProperty property="name" name="student" value="건후"/>
<jsp:setProperty property="dog" name="student" value="${dog}"/> <%--객체를 전달하는 것, 그냥 dog쓰면 "dog"이것 자체를 문자열로 받아서 안 됨 --%>
<%--
<jsp:getProperty property="id" name="student"/>
<jsp:getProperty property="dog" name="student"/> 
 --%>

<jsp:getProperty property="dog" name="student"/>

${student.name}, 
${student.dog.name}
${student.setName("기정") }
${student.getName() }
${student.name }
</body>
</html>