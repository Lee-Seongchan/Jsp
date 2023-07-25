<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>forward2</title>
	</head>
	<body>
		<h3>forward2 페이지</h3>
		
		<%
			// forwar는 서버 자원내에서 제어권 이동하기 때문에 타 써버 자원으로 이동 안됨
			pageContext.forward("http://naver.com");
			
		
		%>
		
	</body>
</html>