<%--
  -- 첫화면 페이지
  -- @author 류세은
 --%>
<%@page contentType="text/html; charset=utf-8"%>
<%
request.setCharacterEncoding("utf-8");
String certify = request.getParameter("certify");
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="<%=application.getContextPath() %>/css/index.css">
<link rel="stylesheet" type="text/css" href="<%=application.getContextPath() %>/css/snackbar.css">
</head>
<body>

    <%--탑메뉴 시작--%>
    <jsp:include page="/include/navigator.jsp" />
    <%--탑메뉴 종료--%>

  <div class="row">
    <div class="leftcolumn">
      <div class="card">
        <img id="albumCover" alt="albumCover" src="/image/albumCover.jpg">
        <img id="BrownEyedSoul" alt="BrownEyedSoul" src="/image/BrownEyedSoul.jpg">
        
       </div>
    </div>
    

     <%--사이드메뉴 시작--%>
    <jsp:include page="/include/aside.jsp" />
    <%--사이드메뉴 종료--%>
    
    </div>
    <%--footer 시작--%>
<jsp:include page="/include/footer.jsp"/>
<%--footer 종료--%>

 <!-- The actual snackbar -->
<div id="snackbar">Some text some message..</div>
</body>
<script type="text/javascript">
function myFunction() {
    // Get the snackbar DIV
    var x = document.getElementById("snackbar");

    // Add the "show" class to DIV
    x.className = "show";

    // After 3 seconds, remove the show class from DIV
    setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
}
</script>
<%if(certify != null && certify.equals("fail")){ %>
<script>
document.getElementById("snackbar").innerText = "아이디 혹은 비밀번호를 정확히 입력해주십시오";
myFunction();
</script>
<%} %>
</html>
