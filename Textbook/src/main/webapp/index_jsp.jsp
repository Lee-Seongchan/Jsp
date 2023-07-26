<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" errorPage="errors/boardError.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<!-- errorPage는 실행 중인 JSP파일에서 에러가 발생하면 브라우저에 전송할 에러 페이지를 지정할 때 사용한다. -->
	</head>
	<body>
		<center>
			<h1><font color = "green"><%=request.getParameter("id").toString() %></font>님 환영합니다.</h1>
		</center>
	</body>
</html>