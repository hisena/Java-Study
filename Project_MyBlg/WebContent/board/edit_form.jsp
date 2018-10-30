<%--
 -- 게시글 수정 화면
 -- @author 류세은
 --%>

<%@page import="kr.or.kosta.blog.article.domain.Article"%>
<%@page import="java.net.InetAddress"%>
<%@page import="kr.or.kosta.blog.article.dao.ArticleDao"%>
<%@page import="kr.or.kosta.blog.factory.DaoFactory"%>
<%@page contentType="text/html; charset=utf-8"%>

<%
request.setCharacterEncoding("utf-8");
%>
<%
	//쿠키 내 loginId 값 존재여부 확인
	String loginId = null;
	Cookie[] cookies = request.getCookies();
	if (cookies != null) {
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("loginId")) {
				loginId = cookie.getValue();
				break;
			}
		}
	}
    //쿼리스트링값 받음
    String articleId = request.getParameter("articleId");
  String subject = request.getParameter("subject");
  String content = request.getParameter("content");
  String passwd = request.getParameter("passwd");

	DaoFactory factory = (DaoFactory) application.getAttribute("factory");
	ArticleDao dao = factory.getArticleDao();
	//ip 동적으로 받아오기
	   InetAddress localhost = InetAddress.getLocalHost();
	  String ip = localhost.getHostAddress();

  session.setAttribute("originalPW", passwd);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css"
  href="<%=application.getContextPath()%>/css/article.css">
<link rel="stylesheet" type="text/css"
  href="<%=application.getContextPath()%>/css/index.css">
<title>게시글 수정</title>
</head>
<body>

  <%--탑메뉴 시작--%>
  <jsp:include page="/include/navigator.jsp" />
  <%--탑메뉴 종료--%>

  <div id="articlearea">
    <h3>게시글 수정</h3>
    <form id="writeForm" action="<%=application.getContextPath() %>/board/edit_action.jsp" method="post">
      <input type="hidden" name="articleId" value="<%=articleId%>"> 
      <input type="hidden" name="ip" value="<%=ip%>"> 
      <table class="articleTable">
        <tr>
          <td>제목</td>
          <td colspan="3"><input type="text" name="subject" value="<%=subject%>"></td>
        </tr>
        <tr>
          <td>작성자</td>
          <td><input type="text" name="writer" value="<%=loginId%>" readonly></td>
          <td>비밀번호</td>
          <td><input type="text" name="passwd"></td>
        </tr>
        <tr>
          <td colspan="4"><textarea name="content" ><%=content%></textarea></td>
        </tr>
      </table>
      <div class="submit">
        <span><input type="submit" value="전송" id="postbutton" /></span>
        <span><button type="button" id="cancelbutton" onclick="location.href='<%=application.getContextPath() %>/board/board_form.jsp'">취소</button></span>
      </div>
    </form>
  </div>

  <%--footer 시작--%>
  <jsp:include page="/include/footer.jsp" />
  <%--footer 종료--%>

</body>
</html>