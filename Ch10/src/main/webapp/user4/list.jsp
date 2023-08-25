<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
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
			
			<tr>
				<td>이야기</td>
				<td>남</td>
				<td>55</td>
				<td>서울시</td>
				<td>
					<a href="/Ch10/user4/modify.jsp">수정</a>
					<a href="/Ch10/user4/delete.jsp">삭제</a>
				</td>
			</tr>
			
		</table>	
	</body>
</html>