<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="vo.User2VO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
		 <script type="text/javascript">
		 	$(function(){
		 		$.ajax({
		 			url:"./listProc.jsp",
		 			type:"GET",
		 			dateType:"json",
		 			success: function(data){
		 			
		 			for(let user of data){
		 				
			 				let tr = `<tr>
			 							<td>\${user.uid}</td>
			 							<td>\${user.name}</td>
			 							<td>\${user.hp}</td>
			 							<td>\${user.age}</td>		 						
			 							<td>
			 								<a href="./modify.jsp?uid=\${user.uid}">수정</a>
			 								<a href="./delete.jsp?uid=\${user.uid}">삭제</a>
			 							</td>		 						
			 						  </tr>`;
			 						  
			 				$("table").append(tr);			  
		 				}
		 			}
		 		});
		 		
		 		
		 	});	
		 
		 </script>
	</head>
	<body>
		<h3>User6 목록</h3>
		<a href="/Ch06/1_JDBC.jsp">처음으로</a>
		<a href="/Ch06/user6/register.jsp">User6 등록</a>
		
			<table border="1">
				<tr>
					<td>아이디</td>
					<td>이름</td>
					<td>휴대폰</td>
					<td>나이</td>
					<td>관리</td>
				</tr>
				
				
			</table>
		
	</body>
</html>