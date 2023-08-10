<%@page import="kr.co.jboard1.dao.ArticleDAO"%>
<%@page import="kr.co.jboard1.vo.ArticleDTO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./_header.jsp" %> <!-- include는 상대경로로 작성한다. --> 

<%
	request.setCharacterEncoding("UTF-8");

	String no = request.getParameter("no");

	
	
	ArticleDTO dto = new ArticleDTO();
	dto.setNo(no);
	
	ArticleDAO dao = new ArticleDAO();
	dao.deleteArticle(no);
	
	

	response.sendRedirect("/Jboard1/list.jsp");

%>
