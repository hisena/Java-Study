<%--
  -- 로그아웃 액션
  -- @author 류세은
 --%>

<%@page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
<% 
Cookie[] cookies = request.getCookies();
if (cookies != null) {
	for (Cookie cookie : cookies) {
		if (cookie.getName().equals("loginId")) {
			cookie.setPath("/");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
			break;
		}
	}
}
response.sendRedirect(application.getContextPath()+"/index.jsp");

%>
</body>
</html>
