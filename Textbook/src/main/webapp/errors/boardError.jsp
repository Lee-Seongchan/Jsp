<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<!-- isErrorPage 속성은 특정 jsp를 에러 전용 페이지로 설정할 때 사용하는 속성 -->
	</head>
	<body>
		<center>
			<h3><%=exception.getClass().getName() %>발생!</h3>
			<hr>
			<% StackTraceElement[] elements = exception.getStackTrace(); %>
			예외 위치 : <%= elements[0].toString() %>
		</center>
	</body>
</html>