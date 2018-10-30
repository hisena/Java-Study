<%--
  -- index 화면의 aside
  -- @author 류세은
 --%>

<%@ page contentType="text/html; charset=utf-8"%>

<div class="rightcolumn">
  <div class="card">
    <%
    	String loginId = null;
        String saveId = null;  

    	Cookie[] cookies = request.getCookies();
    	if (cookies != null) {
    		for (Cookie havecookie : cookies) {
    			if (havecookie.getName().equals("loginId")) {
    				loginId = havecookie.getValue();
    			}else if(havecookie.getName().equals("saveId")){
    				saveId = havecookie.getValue();
              }
    		}
    	}
    %>
    <div>
      <%
      	if (loginId == null) {
      %>
      <form action="<%=application.getContextPath() %>/user/login.jsp" method="post">
      <%
      if(saveId == null) {
      %>
    	<input type="text" id="userid" name="userid" placeholder="Identifier..."> 
      <%
      } else {
      %>
      <input type="text" id="userid" name="userid" placeholder="Identifier..." value="<%=saveId%>"> 
      <%
      }
      %>
        <input type="password" id="userpw" name="userpw" placeholder="Password..."> 
        <input type="checkbox" name="checkbox">아이디 저장
        <input type="submit" value="Login">
      </form>
      <%
      	} else {
      %>
      <form action="<%=application.getContextPath() %>/user/logout.jsp" method="post">
        <%=loginId%>님이 로그인 중 <input type="submit" value="Logout">
        <%
        	}
        %>
      </form>

    </div>

  </div>

</div>
