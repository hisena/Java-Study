<%--
  -- 로그인 액션
  -- @author 류세은
 --%>

<%@page import="kr.or.kosta.blog.user.domain.User"%>
<%@page import="kr.or.kosta.blog.factory.DaoFactory"%>
<%@page import="kr.or.kosta.blog.user.dao.UserDao"%>
<%@page import="kr.or.kosta.blog.factory.JdbcDaoFactory"%>
<%@page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
  <%
  	String id = request.getParameter("userid");
  	String pw = request.getParameter("userpw");

  	//아이디저장 체크박스 받는 변수
  	String saveId = request.getParameter("checkbox");

  	if (id == null || pw == null) {
  		return;
  	}

  	//UserDao를 이용한 회원여부 체크
  	DaoFactory factory = new JdbcDaoFactory();
  	UserDao dao = factory.getUserDao();
  	User user = dao.certify(id, pw);
  	if (user != null) {

  		if (saveId == null) {
  			Cookie cookie = new Cookie("loginId", user.getId());
  			cookie.setPath("/");
  			cookie.setMaxAge(60 * 60 * 24 * 30);
  			response.addCookie(cookie);

  			Cookie checkCookie = new Cookie("saveId", user.getId());
  			checkCookie.setMaxAge(0);
  			checkCookie.setPath("/");
  			response.addCookie(checkCookie);
  		} else if (saveId.equals("on")) {
  			Cookie longcookie = new Cookie("loginId", user.getId());
  			longcookie.setPath("/");
  			longcookie.setMaxAge(60 * 60 * 24 * 30);
  			response.addCookie(longcookie);

  			Cookie checkCookie = new Cookie("saveId", user.getId());
  			checkCookie.setMaxAge(60 * 60 * 24 * 30);
  			checkCookie.setPath("/");
  			response.addCookie(checkCookie);
  		}
  %>
  <jsp:forward page="/index.jsp?certify=success" />
  <%
  	} else {
  %>
  <jsp:forward page="/index.jsp?certify=fail" />
  <%
  	}
  %>
</body>
</html>
