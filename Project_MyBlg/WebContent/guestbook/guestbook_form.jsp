<%--
  -- 방명록 화면
  -- @author 류세은
  --%>
<%@page import="java.util.List"%>
<%@page import="kr.or.kosta.blog.guestbook.domain.Guestbook"%>
<%@page import="kr.or.kosta.blog.factory.JdbcDaoFactory"%>
<%@page import="kr.or.kosta.blog.guestbook.dao.GuestbookDao"%>
<%@page import="kr.or.kosta.blog.factory.DaoFactory"%>

<%@page contentType="text/html; charset=utf-8"%>
<%
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
	GuestbookDao dao = factory.getGuestbookDao();
	List<Guestbook> list = dao.listAll();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록</title>
<link rel="stylesheet" type="text/css"
  href="<%=application.getContextPath()%>/css/guestbook.css">
<link rel="stylesheet" type="text/css"
  href="<%=application.getContextPath()%>/css/index.css">
</head>
<body>

  <%--탑메뉴 시작--%>
  <jsp:include page="/include/navigator.jsp" />
  <%--탑메뉴 종료--%>

  <div class="wrap">
    <ul>
      <!-- 게시판 제목 -->
      <li id="title">방명록</li>
    </ul>
  </div>


  <div id="guestbook">
    <div>
      <form
        action="<%=application.getContextPath()%>/guestbook/guestbook_action.jsp"
        method="post">
        <input type="hidden" name="userId" value="<%=loginId%>">
        <%
        	if (loginId == null) {
        %>
        <span><textarea id="guestcomments"
            placeholder="로그인하시면 방명록 등록이 가능합니다." disabled></textarea></span>
        <%
        	} else {
        %>
        <span><textarea id="guestcomments" name="contents"></textarea>
          <%
          	}
          %> <input type="submit" id="guestbookadd" value="등록"></span>
      </form>
    </div>


    <table class="guest_list">
      <colgroup>
        <col style="width: 15%" />
        <col style="width: 70%" />
        <col style="width: 15%" />
      </colgroup>
      <thead>
        <tr>
          <th scope="col">작성자</th>
          <th scope="col">내용</th>
          <th scope="col">등록날짜</th>
        </tr>
      </thead>
      <tbody>
        <%
        	for (Guestbook guestbook : list) {
        %>
        <tr>
          <td><%=guestbook.getUserId()%></td>
          <td><pre><%=guestbook.getContents()%></pre></td>
          <td><%=guestbook.getRegdate()%></td>
        </tr>
        <%
        	}
        %>
      </tbody>
    </table>
  </div>
  <%--footer 시작--%>
  <jsp:include page="/include/footer.jsp" />
  <%--footer 종료--%>

</body>
</html>