<%@page import="kr.or.kosta.jsp.dao.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%
	List<String> teams = new ArrayList<String>();
	teams.add("한화 이글즈");
	teams.add("두산 베어즈");
	teams.add("SK 타이거즈");
	request.setAttribute("teams", teams);

	List<User> users = new ArrayList<User>();
	users.add(new User("bangry", "김기정", "1111", "bangry@naver.com", "2002"));
	users.add(new User("bangry", "김기정", "1111", "bangry@naver.com", "2002"));
	users.add(new User("bangry", "김기정", "1111", "bangry@naver.com", "2002"));
	users.add(new User("bangry", "김기정", "1111", "bangry@naver.com", "2002"));
	request.setAttribute("users", users);
%>

<c:set var="score" value="85" scope="request" />

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title></title>
</head>
<body>

  <c:forEach var="i" begin="1" end="10">
${i }김기정입니다<br>
  </c:forEach>

  <table border="1">
    <c:forEach var="i" begin="2" end="9">
      <tr>
        <c:forEach var="j" begin="1" end="9">
          <td>${i } * ${j } = ${i*j }</td>
        </c:forEach>
      </tr>
    </c:forEach>
  </table>

  <table>
    <tr>
      <th>아이디</th>
      <th>이름</th>
      <th>비밀번호</th>
      <th>이메일</th>
      <th>가입일자</th>
    </tr>

    <c:choose>
      <c:when test="${not empty users }">
        <c:forEach var="user" items="${users }">
          <tr>
            <td>${user.id }</td>
            <td>${user.name }</td>
            <td>${user.passwd }</td>
            <td>${user.email }</td>
            <td>${user.regdate }</td>
          </tr>
        </c:forEach>
      </c:when>
      <c:otherwise>
        <tr>
          <td colspan="5">회원이 존재하지 않습니다</td>
        </tr>
      </c:otherwise>
    </c:choose>
  </table>

  <select>
    <c:forEach var="team" items="${teams }">
      <option>${team }</option>
    </c:forEach>
  </select>

</body>
</html>