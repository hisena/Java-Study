<%--
 -- 답변글 작성화면
 -- @author 류세은
 --%>

<%@page import="java.net.InetAddress"%>
<%@page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="<%=application.getContextPath() %>/css/article.css">
<link rel="stylesheet" type="text/css" href="<%=application.getContextPath() %>/css/index.css">
<title>답변 글쓰기</title>
</head>
<body>

<%
System.out.println("========답변글 화면 시작=========");
//회원여부 체크
String loginId = null;
Cookie[] cookies = request.getCookies();
if(cookies != null) {
  for(Cookie cookie : cookies) {
    if(cookie.getName().equals("loginId")){
      loginId = cookie.getValue();
      break;
    }
  }
}
//ip 동적으로 받아오기
InetAddress localhost = InetAddress.getLocalHost();
String ip = localhost.getHostAddress();
System.out.println("ip : " + ip);

String articleId = request.getParameter("articleId");
System.out.println(articleId);
String groupNo = request.getParameter("groupNo");
System.out.println(groupNo);
String levelNo = request.getParameter("levelNo");
System.out.println(levelNo);
%>
    <%--탑메뉴 시작--%>
    <jsp:include page="/include/navigator.jsp" />
    <%--탑메뉴 종료--%>

<div id="articlearea">
<h3>답변글 쓰기</h3>
<form id="writeForm" action="<%=application.getContextPath() %>/board/reply_action.jsp" method="post">
 <input type="hidden" name="articleId" value="<%=articleId%>">
 <input type="hidden" name="groupNo" value="<%=groupNo%>">
 <input type="hidden" name="levelNo" value="<%=levelNo%>">
 <input type="hidden" name="ip" value="<%=ip%>">
<table class="articleTable">
<tr>
    
    <td>제목</td>
    <td colspan="3"><input type="text" name="subject"></td>
</tr>
<tr>
    <td>작성자</td>
    <td><input type="text" name="writer" value="<%=loginId%>"></td>
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
<jsp:include page="/include/footer.jsp"/>
<%--footer 종료--%>
  
</body>
</html>