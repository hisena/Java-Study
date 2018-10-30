<%--
 -- 게시글 등록 액션
 -- @author 류세은
 --%>

<%@page import="kr.or.kosta.blog.article.domain.Article"%>
<%@page import="kr.or.kosta.blog.article.dao.ArticleDao"%>
<%@page import="kr.or.kosta.blog.factory.JdbcDaoFactory"%>
<%@page import="kr.or.kosta.blog.factory.DaoFactory"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="article"
  class="kr.or.kosta.blog.article.domain.Article" scope="request" />
<jsp:setProperty property="*" name="article" />

<%
	DaoFactory factory = (DaoFactory) application.getAttribute("factory");
	ArticleDao dao = factory.getArticleDao();
	dao.create(article);
%>

<jsp:forward page="/board/board_form.jsp" />
