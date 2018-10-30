<%--
  -- 방명록 등록 액션
  -- @author 류세은
  --%>
<%@page import="kr.or.kosta.blog.guestbook.domain.Guestbook"%>
<%@page import="kr.or.kosta.blog.guestbook.dao.GuestbookDao"%>
<%@page import="kr.or.kosta.blog.factory.JdbcDaoFactory"%>
<%@page import="kr.or.kosta.blog.factory.DaoFactory"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="guestbook"
  class="kr.or.kosta.blog.guestbook.domain.Guestbook" scope="request" />
<jsp:setProperty property="*" name="guestbook" />

<%
	DaoFactory factory = (DaoFactory) application.getAttribute("factory");
	GuestbookDao dao = factory.getGuestbookDao();
	dao.create(guestbook);
%>

<jsp:forward page="/guestbook/guestbook_form.jsp" />