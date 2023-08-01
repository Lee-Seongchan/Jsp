<%@page import="com.google.gson.Gson"%>
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
<%@ page contentType="application/json;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	//데이터베이스 처리
	String host = "jdbc:mysql://127.0.0.1:3306/userdb";
	String user = "root";
	String pass = "1234";
	
	List<User2VO> users = new ArrayList<>();
	
	try{
		Context initctx = new InitialContext();
		Context ctx = (Context)initctx.lookup("java:comp/env");
		
		DataSource ds = (DataSource)ctx.lookup("jdbc/userdb");
		Connection conn = ds.getConnection();
		
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM `user6`");		
		
		while(rs.next()){
			User2VO vo = new User2VO();
			vo.setUid(rs.getString(1));
			vo.setName(rs.getString(2));
			vo.setHp(rs.getString(3));
			vo.setAge(rs.getInt(4));
			
			users.add(vo);
		}
		
		rs.close();
		stmt.close();
		conn.close();
		
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	Gson gson = new Gson();
	String jsonData = gson.toJson(users);
	
	out.print(jsonData);

%>