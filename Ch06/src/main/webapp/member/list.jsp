<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="vo.Member"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>

<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<%
	//데이터베이스 처리
	String host = "jdbc:mysql://127.0.0.1:3306/userdb";
	String user = "root";
	String pass = "1234";
	
	List<Member> users = new ArrayList<>();
	
	try{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection(host, user, pass);
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM `member`");
		
		while(rs.next()){
			Member vo = new Member();
			vo.setUid(rs.getString(1));
			vo.setName(rs.getString(2));
			vo.setHp(rs.getString(3));
			vo.setPos(rs.getString(4));
			vo.setDep(rs.getInt(5));
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
		<a href="/Ch06/user4/register.jsp">User4 등록</a>
		
		<table border="1">
			<tr>
				<td>이름</td>
				<td>성별</td>			
				<td>나이</td>
				<td>주소</td>
				<td>관리</td>
			</tr>
			<%for(Member vo : users){ %>
			<tr>
				<td><%=vo.getUid() %></td>
				<td><%=vo.getName() %></td>
				<td><%=vo.getHp() %></td>
				<td><%=vo.getPos() %></td>
				<td><%=vo.getDep() %></td>
				<td>
					<a href="/Ch06/user4/modify.jsp?seq=<%=vo.getUid()%>">수정</a>
					<a href="/Ch06/user4/delete.jsp?seq=<%=vo.getUid()%>">삭제</a>
				</td>
			</tr>
			<%} %>
		</table>	
	</body>
</html>