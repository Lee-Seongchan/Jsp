<%@page import="kr.co.jboard1.dao.UserDAO"%>
<%@page import="kr.co.jboard1.db.SQL"%>
<%@page import="com.google.gson.JsonObject"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@ page contentType="application/json;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	
	String nick = request.getParameter("nick");
	
	int result = UserDAO.getInstance().selectCountNick(nick);
	
	
	//json 생성
	JsonObject json = new JsonObject();
	json.addProperty("result", result);  //json의 key, value
	
	//json 출력
	String jsonData = json.toString(); //json데이터를 문자열로 변환
	out.print(jsonData);
	
%>