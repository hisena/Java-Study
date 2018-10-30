<%--
  -- 회원가입 화면
  -- @author 류세은
 --%>

<%@page contentType="text/html; charset=utf-8"%>
<%
request.setCharacterEncoding("utf-8");
String certify = request.getParameter("certify");
String insertId = request.getParameter("id");
%>
<!DOCTYPE html>
<html>
<script type="text/javascript" src="<%=application.getContextPath() %>/js/Validation.js"></script>
<script type="text/javascript">
/**
 * 윈도우 브라우저 실행
 */
window.onload = function() {
	eventRegist();
}

/** 
 * 이벤트소스에 이벤트리스너 등록
 */
function eventRegist(){
  //아이디 입력 정확성여부 확인
  document.getElementsByName('id')[0].onkeyup = function(){
	  correctId();
  }
  
  //이름 입력 정확성여부 확인
  document.getElementsByName('name')[0].onkeyup = function(){
	  correctName();
  }
  
  //비밀번호 입력 정확성여부 확인
  document.getElementsByName('passwd')[0].onkeyup = function(){
	  correctPw();
  }
  
  //이메일 입력 정확성여부 확인
  document.getElementsByName('email')[0].onkeyup = function(){
	  correctEmail();
  }
}
</script>
<head>
<link rel="stylesheet" type="text/css" href="<%=application.getContextPath() %>/css/index.css">
<link rel="stylesheet" type="text/css" href="<%=application.getContextPath() %>/css/regist_form.css">
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
      <h2>회원가입</h2>
      <p>해당 항목을 꼼꼼하게 작성해주십시오</p>
      <form action="regist_action.jsp" method="post">
         <p>
          <input class="w3-input w3-padding-16 w3-border" type="text" placeholder="아이디(영문대소문자 및 숫자 2-8자리)" required name="id">
          <label id="id"></label>
        </p>
        <p>
          <input class="w3-input w3-padding-16 w3-border" type="text" placeholder="이름(한글 5자리 이하)" required name="name">
          <label id="name"></label>
        </p>
        <p>
          <input class="w3-input w3-padding-16 w3-border" type="password" placeholder="비밀번호(영문대소문자 및 숫자 2-8자리)" required name="passwd">
          <label id="passwd"></label>
        </p>
        <p>
          <input class="w3-input w3-padding-16 w3-border" type="text" placeholder="이메일(ex)kosta123@kosta.or.kr)" required name="email">
          <label id="email"></label>
         </p>
        <p>
       <% 
        if(certify == "fail") {
       %>
          <button class="w3-button w3-black w3-padding-large" type="submit" disabled>가입하기</button>
          <button class="w3-button w3-gray w3-padding-large" type="reset">취소하기</button>
        <% 
        } else {
        %>   
          <button class="w3-button w3-black w3-padding-large" type="submit" name="regist">가입하기</button>
          <button class="w3-button w3-gray w3-padding-large" type="reset">취소하기</button>
        <% 
        }
        %>  
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
document.getElementById("snackbar").innerText = "중복된 아이디입니다";
myFunction();
document.getElementsByName("regist")[0].disabled = 'true';
</script>
<%} else%>
</html>
