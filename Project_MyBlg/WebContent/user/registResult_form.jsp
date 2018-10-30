<%--
  -- 회원등록 결과 화면
  -- @author 류세은
 --%>
<%@page contentType="text/html; charset=utf-8"%>
<jsp:useBean id="user" class="kr.or.kosta.blog.user.domain.User" scope="request"/>

<!DOCTYPE html>
<html>
<head>
<title>회원등록 결과 화면</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="<%=application.getContextPath() %>/css/index.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body class="w3-light-grey">


 <%--탑메뉴 시작--%>
    <jsp:include page="/include/navigator.jsp" />
    <%--탑메뉴 종료--%>

<%
 
%>


  <!-- Page content -->
  <div class="w3-content" style="max-width: 600px;">
    <div
      class="w3-container w3-padding-32 w3-white w3-opacity w3-card-2 w3-hover-opacity-off"
      style="margin: 32px 0;">
      
      <h1>회원가입 완료</h1>
      <h4>회원가입이 정상적으로 완료되었습니다.</h4>

      <p>아이디  : <%=user.getId() %></p>
      <p>이름 : <%=user.getName() %></p>
      <p>이메일 : <%=user.getEmail() %></p>
            <h4>'홈으로' 버튼을 클릭하시면 메인화면으로 이동합니다.</h4>
      <button type="button" class="w3-button w3-black w3-margin-top" onclick="location.href='<%=application.getContextPath()%>/index.jsp'">홈으로</button>
    </div>
   </div>

       <%--footer 시작--%>
<jsp:include page="/include/footer.jsp"/>
<%--footer 종료--%>
   
</body>
</html>