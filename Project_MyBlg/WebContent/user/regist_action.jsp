<%--
  -- 회원가입 액션
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
<jsp:useBean id="user" class="kr.or.kosta.blog.user.domain.User" scope="request"/>
<jsp:setProperty property="*" name="user"/>
<%
DaoFactory factory = (DaoFactory)application.getAttribute("factory");
UserDao dao = factory.getUserDao();

String id = request.getParameter("id");
if(dao.read(id) == null) {
dao.create(user);
} else {
 System.out.println("유저 등록 실패"); 
}
%>

<jsp:forward page="registResult_form.jsp"/>