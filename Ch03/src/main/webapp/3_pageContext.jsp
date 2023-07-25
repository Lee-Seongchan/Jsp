<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>3_pageContext</title>
		
		<!-- 
			날짜 : 2023/07/25
			이름 : 이성찬
			내용 : JSP pageContext 내장객체 실습하기
		
			pageContext 
			- 요청 파일 하나당 JSP파일 객체
			- JSP 파일 하나당 하나의 pageContext 객체 생성
			- 주요 기능은 페이지 흐름제어 기능 제공
			
			forward
			- 주어진 자원내에서 페이지를 요청하는 기능
			- 최초요청에 대한 주소 반영 cf)redirect는 최종 요청에 대한 반영
			
		 -->
	</head>
	<body>
		<h3>3.pageContext 내장객체</h3>
		
		<h4>include</h4>
		<%
			pageContext.include("./inc/_header.html");
			pageContext.include("./inc/_footer.html");
		
		%>
		<h4>forward</h4>
		<a href="./proc/forward1.jsp">포워드 페이지 요청1</a>
		
		<!-- 다른 자원이기 때문에 포워드 페이지 요청2는 실행되지 않는다. -->
		<a href="./proc/forward2.jsp">포워드 페이지 요청2</a>
		
		
		
		
	</body>
</html>