<%--
 -- 게시글 상세보기 화면
 -- @author 류세은
 --%>

<%@page import="java.net.URLEncoder"%>
<%@page import="kr.or.kosta.blog.page.Params"%>
<%@page import="kr.or.kosta.blog.article.domain.Article"%>
<%@page import="kr.or.kosta.blog.article.dao.ArticleDao"%>
<%@page import="kr.or.kosta.blog.user.dao.UserDao"%>
<%@page import="kr.or.kosta.blog.factory.JdbcDaoFactory"%>
<%@page import="kr.or.kosta.blog.factory.DaoFactory"%>
<%@page contentType="text/html; charset=utf-8"%>

<jsp:useBean id="article" class="kr.or.kosta.blog.article.domain.Article" scope="request"/>
<jsp:setProperty property="*" name="article"/>

<%
request.setCharacterEncoding("utf-8");
String certify = request.getParameter("certify");
//회원여부 체크
String loginId = null;
Cookie[] cookies = request.getCookies();
if(cookies != null){
  for(Cookie cookie : cookies){
    if(cookie.getName().equals("loginId")){
      loginId = cookie.getValue();
      break;
    }
  }
}
//쿼리스트링값을 받음
String pageNum = request.getParameter("page");

int articleId = Integer.parseInt(request.getParameter("article_id"));
if(pageNum == null) pageNum = "1";

DaoFactory factory= (DaoFactory)application.getAttribute("factory");
ArticleDao dao = factory.getArticleDao();
Article articleRead = dao.read(articleId);
//조회수 증가
dao.uphitcount(articleId);
%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="<%=application.getContextPath() %>/css/article.css">
<link rel="stylesheet" type="text/css" href="<%=application.getContextPath() %>/css/index.css">
<title>게시글 상세</title>
</head>
<body>

 <%--탑메뉴 시작--%>
    <jsp:include page="/include/navigator.jsp" />
    <%--탑메뉴 종료--%>

<div>
<h3>게시글 상세</h3>
 <input type="hidden" name="articleId" value="<%=articleRead.getArticleId()%>"> 
 <input type="hidden" name="levelNo" value="<%=articleRead.getLevelNo()%>"> 
 <input type="hidden" name="orderNo" value="<%=articleRead.getOrderNo()%>"> 
<table class="articleTable">
<tr>
    <td>제목</td>
    <td colspan="3"><input type="text" name="subject" value="<%=articleRead.getSubject()%>" readonly></td>
</tr>
<tr>
    <td>작성자</td>
    <td><input type="text" name="writer" value="<%=articleRead.getWriter()%>" readonly></td>
    <td>작성일</td>
    <td><input type="text" name="regdate" value="<%=articleRead.getRegdate()%>" readonly></td>
</tr>
<tr>
    <td>아이피</td>
    <td><input type="text" name="ip" value="<%=articleRead.getIp()%>" readonly></td>
    <td>조회수</td>
    <td><input type="text" name="hitcount" value="<%=articleRead.getHitcount()%>" readonly></td>
</tr>
<tr>
    <td colspan="4"><textarea name="content" readonly><%=articleRead.getContent()%></textarea></td>
</tr>
</table>
<div>
<%
if(loginId != null && loginId != "" && (loginId.equals(articleRead.getWriter()))) {
%>
    <span><button type="button" id="listButton" onclick="location.href='<%=application.getContextPath() %>/board/board_form.jsp'">글목록</button></span>
    <span><input type="submit" value="답글쓰기" id="commentButton" onclick="location.href='<%=application.getContextPath() %>/board/reply_form.jsp?articleId=<%=articleRead.getArticleId()%>&levelNo=<%=articleRead.getLevelNo()%>&groupNo=<%=articleRead.getGroupNo()%>'"/></span>
    <span><input type="submit" value="글삭제" id="deleteButton" onclick="location.href='<%=application.getContextPath() %>/board/delete_form.jsp?articleId=<%=articleRead.getArticleId()%>'"/></span>
    <span><input type="submit" value="글수정" id="editButton" onclick="location.href='<%=application.getContextPath() %>/board/edit_form.jsp?articleId=<%=articleRead.getArticleId()%>&subject=<%=articleRead.getSubject()%>&content=<%=articleRead.getContent()%>'"/></span>
<%
} else if(loginId != null && loginId != "" && !(loginId.equals(articleRead.getWriter()))) {
%>
    <span><button type="button" id="listButton" onclick="location.href='<%=application.getContextPath() %>/board/board_form.jsp'">글목록</button></span>
    <span><input type="submit" value="답글쓰기" id="commentButton" onclick="location.href='<%=application.getContextPath() %>/board/reply_form.jsp?articleId=<%=articleRead.getArticleId()%>&levelNo=<%=articleRead.getLevelNo()%>&groupNo=<%=articleRead.getGroupNo()%>'"/></span>
<%
} else { 
%>
    <span><button type="button" id="listButton" onclick="location.href='<%=application.getContextPath() %>/board/board_form.jsp'">글목록</button></span>
<%
} 
%>
</div>
</div>
    
<%--footer 시작--%>
<jsp:include page="/include/footer.jsp"/>
<%--footer 종료--%>
</body>
</html>
