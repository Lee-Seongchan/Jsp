<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<h3>User2 목록</h3>
		<a href="/Ch10">메인</a>
		<a href="/Ch10/user2/register.do">User2 등록</a>
		
			<table border="1">
				<tr>
					<td>아이디</td>
					<td>이름</td>
					<td>휴대폰</td>
					<td>나이</td>
					<td>관리</td>
				</tr>
				
				<c:forEach var="user" items="${users}">
				<tr>
					<td>${user.getUid()}</td>
					<td>${user.getName()}</td>
					<td>${user.getHp()}</td>
					<td>${user.getAge()}</td>
					<td>
						<a href="/Ch10/user2/modify.do?uid=${user.getUid()}">수정</a>
						<a href="/Ch10/user2/delete.do?uid=${user.getUid()}">삭제</a>

					</td>
				</tr>
				</c:forEach>
			</table>
	</body>
</html>