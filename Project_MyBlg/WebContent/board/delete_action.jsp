<%--
 -- 게시글 삭제 액션
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

<%
String articleId = request.getParameter("articleId");
String insertPw = request.getParameter("passwd");

DaoFactory factory= (DaoFactory)application.getAttribute("factory");
ArticleDao dao = factory.getArticleDao();
Article articleCheck = dao.read(Integer.parseInt(articleId));
if(insertPw.equals(articleCheck.getPasswd())) {
dao.delete(Integer.parseInt(articleId));
%>
<jsp:forward page="/board/board_form.jsp?certify=success"/>
<%
} else {
%>
<jsp:forward page="/board/delete_form.jsp?certify=fail"/>    
<%
}
%>
