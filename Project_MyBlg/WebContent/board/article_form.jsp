<%--
 -- 신규게시글 등록 화면
 -- @author 류세은
 --%>

<%@page import="java.net.InetAddress"%>
<%@page import="kr.or.kosta.blog.article.dao.ArticleDao"%>
<%@page import="kr.or.kosta.blog.factory.DaoFactory"%>
<%@page contentType="text/html; charset=utf-8"%>

<%
	//쿠키 loginId 존재여부 확인
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
	DaoFactory factory = (DaoFactory) application.getAttribute("factory");
	ArticleDao dao = factory.getArticleDao();
	//ip 동적으로 받아오기
   InetAddress localhost = InetAddress.getLocalHost();
  String ip = localhost.getHostAddress();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css"
  href="<%=application.getContextPath()%>/css/article.css">
<link rel="stylesheet" type="text/css"
  href="<%=application.getContextPath()%>/css/index.css">
<title>새 글쓰기</title>
</head>
<body>

  <%--탑메뉴 시작--%>
  <jsp:include page="/include/navigator.jsp" />
  <%--탑메뉴 종료--%>

  <div id="articlearea">
    <h3>신규 게시글 쓰기</h3>
    <form id="writeForm" action="<%=application.getContextPath() %>/board/article_action.jsp" method="post">
      <input type="hidden" name="ip" value="<%=ip%>"> 
      <input type="hidden" name="levelNo" value="<%=0%>">
      <input type="hidden" name="orderNo" value="<%=0%>">
      <table class="articleTable">
        <tr>
          <td>제목</td>
          <td colspan="3"><input type="text" name="subject"></td>
        </tr>
        <tr>
          <td>작성자</td>
          <td><input type="text" name="writer" value="<%=loginId%>" readonly></td>
          <td>비밀번호</td>
          <td><input type="text" name="passwd"></td>
        </tr>
        <tr>
          <td colspan="4"><textarea name="content"></textarea></td>
        </tr>
      </table>
      <div class="submit">
        <span><input type="submit" value="전송" id="postbutton"/></span>
        <span><button type="button" id="cancelbutton" onclick="location.href='<%=application.getContextPath() %>/board/board_form.jsp'">취소</button></span> 
      </div>
    </form>
  </div>

  <%--footer 시작--%>
  <jsp:include page="/include/footer.jsp" />
  <%--footer 종료--%>

</body>
</html>