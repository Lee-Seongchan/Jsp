<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>index4</title>
	</head>
	<body>
	<%@ include file ="../layout/header.jsp" %>
	
	<center>
		<h1><font color="green"><%= request.getParameter("id").toString() %></font>님 환영합니다.</h1>
	</center>
	
	<%@ include file ="../layout/footer.jsp" %>
		
	</body>
</html>