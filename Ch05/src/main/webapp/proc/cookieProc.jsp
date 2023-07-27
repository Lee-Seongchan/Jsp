<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>cookieProc</title>
	</head>
	<body>
		<h3>쿠키생성</h3>
		<%
			// 인코딩 설정
			request.setCharacterEncoding("UTF-8");
			
			// 전송 데이터 수신
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			
			// 쿠키 생성
			Cookie c1 = new Cookie("cid", id); //cid라는 쿠키이름과 id값을 가진 쿠키 생성 
			Cookie c2 = new Cookie("cpw", pw); //cpw라는 쿠키이름과 pw값을 가진 쿠키 생성 
			
			// 쿠키 전송
			response.addCookie(c1);
			response.addCookie(c2);
		%>
		
		<a href="./cookieConfirm.jsp">쿠키확인</a>
	</body>
</html>