
<%@page import="java.sql.ResultSet"%>
<%@page import="com.mysql.cj.protocol.Resultset"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.ArrayList"%>
<%@page import="vo.User4VO"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	//데이터베이스 처리
	String host = "jdbc:mysql://127.0.0.1:3306/userdb";
	String user = "root";
	String pass = "1234";
	
	List<User4VO> users = new ArrayList<>();
	
	try{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection(host, user, pass);
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM `user4`");
		
		while(rs.next()){
			User4VO vo = new User4VO();
			vo.setSeq(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setGender(rs.getInt(3));
			vo.setAge(rs.getInt(4));
			vo.setAddr(rs.getString(5));
			
			users.add(vo);
		}
		rs.close();
		stmt.close();
		
	}catch(Exception e){
		e.printStackTrace();
	}
	


 %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<a href="/Ch06/1_JDBC.jsp">처음으로</a>
		<a href="/Ch06/user4/register.jsp">User3 등록</a>
		
		<table border="1">
			<tr>
				<td>이름</td>
				<td>성별</td>			
				<td>나이</td>
				<td>주소</td>
				<td>관리</td>
			</tr>
			<%for(User4VO vo : users){ %>
			<tr>
				<td><%=vo.getName() %></td>
				<td><%=vo.getGender() == 1 ? "남":"여" %></td>
				<td><%=vo.getAge() %></td>
				<td><%=vo.getAddr() %></td>
				<td>
					<a href="/Ch06/user4/modify.jsp?seq=<%=vo.getSeq()%>">수정</a>
					<a href="/Ch06/user4/delete.jsp?seq=<%=vo.getSeq()%>">삭제</a>
				</td>
			</tr>
			<%} %>
		</table>	
	</body>
</html>