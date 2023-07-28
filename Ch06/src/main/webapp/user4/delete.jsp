<%@page import="java.sql.*"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String seq = request.getParameter("seq");
	
	String host = "jdbc:mysql://127.0.0.1:3306/userdb";
	String user = "root";
	String pass = "1234";

	try{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection(host, user, pass);
		PreparedStatement psmt = conn.prepareStatement("DELETE FROM `user4` WHERE `seq` = ?");
		psmt.setString(1, seq);
		
		psmt.executeUpdate();
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	response.sendRedirect("/Ch06/user4/list.jsp");
%>
