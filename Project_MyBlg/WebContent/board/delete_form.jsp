<%--
  -- 게시물 삭제를 위한 비밀번호 확인
  -- @author 류세은
 --%>

<%@page contentType="text/html; charset=utf-8"%>
<%
request.setCharacterEncoding("utf-8");
String certify = request.getParameter("certify");
%>
<!DOCTYPE html>
<html>
<script type="text/javascript">
</script>
<head>
<link rel="stylesheet" type="text/css" href="<%=application.getContextPath() %>/css/index.css">
<link rel="stylesheet" type="text/css" href="<%=application.getContextPath() %>/css/snackbar.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
 <%--탑메뉴 시작--%>
    <jsp:include page="/include/navigator.jsp" />
    <%--탑메뉴 종료--%>

  <div class="row">
    <div class="body">
      <div class="w3-container" id="contact">
      <h2>게시물 삭제</h2>
      <h6>게시물을 등록했을 때의 비밀번호를 입력하여 주십시오</h6>
       <form action="delete_action.jsp" method="post">
         <p>
          <input class="w3-input w3-padding-16 w3-border" type="text" placeholder="passwd" required name="passwd">
          <input type="hidden" value="<%=request.getParameter("articleId")%>" name="articleId">
          <input type="submit" value="게시물 삭제">
        </p>
      </form>
    </div>
</div>
    
    <%--footer 시작--%>
<jsp:include page="/include/footer.jsp"/>
<%--footer 종료--%>
 </div>
</body>
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
document.getElementById("snackbar").innerText = "비밀번호를 정확히 입력해주십시오";
myFunction();
</script>
<%} else%>
</html>
