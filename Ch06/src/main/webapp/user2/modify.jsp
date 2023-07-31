<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.mysql.cj.xdevapi.PreparableStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="vo.User2VO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
	// 인코딩 설정
	request.setCharacterEncoding("UTF-8");
	
	// 전송 데이터 수신
	String uid = request.getParameter("uid");
	
	String host ="jdbc:mysql://127.0.0.1:3306/userdb";
	String user = "root";
	String pass = "1234";
	
	User2VO vo = null;
	
	try{
		//JDNI 서비스 객체 생성
		Context initCtx = new InitialContext();
		Context ctx = (Context)initCtx.lookup("java:comp/env"); //JNDI 기본 환경 이름
				
		// 커네션 풀에서 커넥션 가져오기 
		DataSource ds = (DataSource)ctx.lookup("jdbc/userdb");
		Connection conn = ds.getConnection();
		
		PreparedStatement psmt = conn.prepareStatement("SELECT * FROM `user2` WHERE `uid` = ?");
		psmt.setString(1, uid);
		ResultSet rs = psmt.executeQuery();
		
		if(rs.next()){
			vo = new User2VO();
			vo.setUid(rs.getString(1));
			vo.setName(rs.getString(2));
			vo.setHp(rs.getString(3));
			vo.setAge(rs.getInt(4));
		}
			
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	
	

%>
<html>
	<head>
		<meta charset="UTF-8">
		<title>user2 :: modify</title>
	</head>
	<body>
		<h3>User2 수정</h3>
		<a href="/Ch06/1_JDBC.jsp">처음으로</a>
		<a href="/Ch06/user2/list.jsp">User2 목록</a>
		
		<form action="/Ch06/user2/modifyProc.jsp" method="post">
			<table border = "1">
				<tr>
					<td>아이디</td>
					<td><input type="text" name="uid" readonly value = "<%=vo.getUid()%>"></td>
				</tr>
				
				<tr>
					<td>이름</td>
					<td><input type="text" name="name" value = "<%=vo.getName()%>"></td>
				</tr>
				
				<tr>
					<td>휴대폰</td>
					<td><input type="text" name="hp" value = "<%=vo.getHp()%>"></td>
				</tr>
				
				<tr>
					<td>나이</td>
					<td><input type="number" name="age" value = "<%=vo.getAge()%>"></td>
				</tr>
				
				<tr>
					<td colspan="2" align="right"><input type="text" type = "submit" value= "수정"></td>
				</tr>
			</table>
		</form>
	</body>
</html>