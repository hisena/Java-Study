<%@page contentType="text/html; charset=utf-8" %>
<%@page import="kr.or.kosta.shoppingmall.common.dao.UserDao"%>
<%@page import="kr.or.kosta.shoppingmall.common.dao.JdbcDaoFactory"%>
<%@page import="kr.or.kosta.shoppingmall.common.dao.DaoFactory"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="user" class="kr.or.kosta.shoppingmall.dao.User" scope="request"/>
<jsp:setProperty property="*" name="user"/>
<%
//DaoFactory factory = new JdbcDaoFactory();
DaoFactory factory = (DaoFactory)application.getAttribute("daoFactory");
UserDao dao = factory.getUserDao();
dao.create(user);

// 가입결과 jsp로 디스패치
%>

<jsp:forward page="regist_result.jsp"/>