<%--
  -- 블로그 소개
  -- @author 류세은
 --%>
<%@page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="<%=application.getContextPath() %>/css/index.css">
</head>
<body>

    <%--탑메뉴 시작--%>
    <jsp:include page="/include/navigator.jsp" />
    <%--탑메뉴 종료--%>

  <div class="row">
    <div class="leftcolumn">
      <div class="card">
       <h2>R&B, 재즈, 소울음악을 사랑하는 세은이의 블로그입니다</h2>
        <img id="albumCover" alt="albumCover" src="/image/albumCover.jpg">
        <img id="BrownEyedSoul" alt="BrownEyedSoul" src="/image/BrownEyedSoul.jpg">
       </div>
    </div>
    </div>
    <%--footer 시작--%>
<jsp:include page="/include/footer.jsp"/>
<%--footer 종료--%>


</body>
</html>
