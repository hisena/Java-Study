<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>EL 디폴트객체 11개</title>
</head>
<body>

${pageScope}
--------------------------<br>
${requestScope}
--------------------------<br>
${sessionScope}
--------------------------<br>
<%--
${applicationScope}
--------------------------<br>
 --%>
${param.name}
--------------------------<br>
${param["name"]}
--------------------------<br>
 ${paramValues.hobby[0]}, ${paramValues.hobby[1] }
 --------------------------<br>
 ${header['user-agent']}
 --------------------------<br>
 쿠키이름 : ${cookie.loginId.name}
쿠키값 : ${cookie.loginId.value}
--------------------------<br>
<%=request.getMethod()%><br>
${pageContext.request.method}
 
</body>
</html>