<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<a href="/Ch10">처음으로</a>
		<a href="/Ch10/user4/register.do">User4 등록</a>
		
		<table border="1">
			<tr>
				<td>이름</td>
				<td>성별</td>			
				<td>나이</td>
				<td>주소</td>
				<td>관리</td>
			</tr>
			
			<c:forEach var="user" items="${users}">
				<tr>					
					<td>${user.name}</td>
					<td>${user.gender}</td>
					<td>${user.age}</td>
					<td>${user.addr}</td>
					<td>
						<a href="/Ch10/user4/modify.do">수정</a>
						<a href="/Ch10/user4/delete.do">삭제</a>
					</td>
				</tr>
			</c:forEach>	
		</table>	
	</body>
</html>