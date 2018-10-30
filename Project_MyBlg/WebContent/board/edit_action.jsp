<%--
 -- 게시글 수정 액션
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
<jsp:useBean id="article" class="kr.or.kosta.blog.article.domain.Article" scope="request"/>
<jsp:setProperty property="*" name="article"/>

<%

String passwd = request.getParameter("passwd");
String articleId = request.getParameter("articleId");

DaoFactory factory= (DaoFactory)application.getAttribute("factory");
ArticleDao dao = factory.getArticleDao();

Article articleCheck = dao.read(Integer.parseInt(articleId));
//비밀번호 체크
if(passwd.equals(articleCheck.getPasswd())) {
dao.update(article);
%>
<jsp:forward page="/board/board_form.jsp?certify=success"/>
<%}  else {%>
<jsp:forward page="/board/board_form.jsp?certify=fail"/>
 <%
}
%>
