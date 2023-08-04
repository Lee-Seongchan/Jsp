<%@page import="kr.co.jboard1.vo.UserDAO"%>
<%@page import="kr.co.jboard1.vo.UserVO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 데이터 수신
	request.setCharacterEncoding("UTF-8");
	
	String uid = request.getParameter("uid");
	String pass = request.getParameter("pass");
	
	UserVO vo = new UserVO();
	vo.setUid(uid);
	vo.setPass(pass);
	
	// 사용자 DB조회
	UserVO user = UserDAO.getInstance().selectUser(vo);
		
	// 회원 여부 확인
	if(user != null){
		// 세션 처리
		session.setAttribute("sessUser", user);
		
		//리다이렉트
		response.sendRedirect("/Jboard1/list.jsp");
		
	}else{
		//리다이렉트
		response.sendRedirect("/Jboard1/user/login.jsp?success=100"); //100은 실패코드라고 치는 파라미터
	}
	
	
	
%>