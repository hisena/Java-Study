<%--
  -- 중복검사 액션
  -- @author 류세은
 --%>

<%@page import="kr.or.kosta.blog.user.dao.UserDao"%>
<%@page import="kr.or.kosta.blog.factory.JdbcDaoFactory"%>
<%@page import="kr.or.kosta.blog.factory.DaoFactory"%>
<%@page import="kr.or.kosta.blog.user.domain.User"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%
request.setCharacterEncoding("utf-8");
%>
<%
DaoFactory factory = (DaoFactory)application.getAttribute("factory");
UserDao dao = factory.getUserDao();

String insertId = request.getParameter("id");


if(dao.isExistId(insertId) == true) {
%>
  <jsp:forward page="/user/regist_form.jsp?certify=fail"/>
  <%}  else {%>
  <jsp:forward page="/user/regist_form.jsp?certify=success?id=<%=insertId%>"/>
   <%
  }
  %>
