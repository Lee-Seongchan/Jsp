<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<form action="/Ch10/user4/register.do" method="post">
			<table border="1">
		
				
				<tr>
					<td>이름</td>
					<td><input type="text" name="name"></td>
				</tr>
				
				<tr>
					<td>성별</td>
					<td>
						<label><input type="radio" name="gender" value = 1 checked>1</label>
						<label><input type="radio" name="gender" value = 2>2</label>
					</td>
				</tr>
				
				<tr>
					<td>나이</td>
					<td><input type="number" name="age"></td>
				</tr>
				
				<tr>
					<td>주소</td>
					<td><input type="text" name="addr"></td>
				</tr>
				
				<tr>
					<td colspan="2" align="right"><input type="submit" value="등록"></td>
				</tr>
			</table>	
		</form>
	</body>
</html>