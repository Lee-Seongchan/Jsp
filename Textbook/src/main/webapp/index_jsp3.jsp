<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" 
			errorPage="errors/boardError3.jsp"
			session = "true" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>session</title>
		
	</head>
	<body>
		<center>
			
			<h1><font color = "green"><%=request.getParameter("id").toString() %></font>님 환영합니다.</h1>--%>
			세션아이디 : <%= session.getId() %>
			<%--세션ID는 단순히 사용자를 식별할 수 있는 의미없는 문자 --%>

		</center>
	</body>
</html>