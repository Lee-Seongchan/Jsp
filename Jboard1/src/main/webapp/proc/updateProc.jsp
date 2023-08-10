<%@page import="kr.co.jboard1.vo.ArticleDTO"%>
<%@page import="kr.co.jboard1.dao.ArticleDAO"%>
<%@page import="com.mysql.cj.x.protobuf.MysqlxConnection.Close"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");

	String title = request.getParameter("title");
	String content = request.getParameter("content");
	String file = request.getParameter("file");
	String no = request.getParameter("no");
	
	ArticleDTO dto = new ArticleDTO();
	dto.setTitle(title);
	dto.setContent(content);
	dto.setNo(no);
	
	ArticleDAO dao = new ArticleDAO();
	dao.updateArticle(dto);
	
	

	response.sendRedirect("/Jboard1/view.jsp?no="+no);
	
	
%>