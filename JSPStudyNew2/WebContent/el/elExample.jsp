<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>EL 연습</title>
</head>
<body>
${"김기정"}
${'김기정'}
${10}
${null}
${true}
${false} <br>
<%-- 
asdasdsadadadadsad<%= %>asdasdsadas
이것을
asdasdsadadadadsad${ }asdasdsadas
이렇게 대체 가능
--%> 

<%= 10  + 20 %>
----이걸 대체하는 것이
${10 + 20 }

<%= 10  + "20" %> --이건 1020
${10 + "20" } --이건 30 EL은 숫자로 인식 (형번환이 문자보다 숫자에 더 우선권이 있다)
${true && false },  ${true and false }

${empty null } --true로 나옴
${empty "" } --true로 나옴

</body>
</html>