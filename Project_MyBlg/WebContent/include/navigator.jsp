<%--
  -- index 화면의 네비게이터
  -- @author 류세은
 --%>

<%@ page contentType="text/html; charset=utf-8"%>

  <div class="header">
    <h1><a href="/index.jsp">세은 블로그</a></h1>
    <p>세은이의 음악블로그에 오신 것을 환영합니다</p>
  </div>
  
<div class="topnav">
  <a href="<%=application.getContextPath() %>/introduce.jsp">블로그 소개</a>
  <a href="<%=application.getContextPath() %>/guestbook/guestbook_form.jsp">방명록</a>
  <a href="<%=application.getContextPath() %>/board/board_form.jsp">자유게시판</a>
  <a href="<%=application.getContextPath() %>/user/regist_form.jsp" style="float:right">회원가입</a>
</div>