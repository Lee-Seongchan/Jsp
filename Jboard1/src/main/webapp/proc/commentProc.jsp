<%@page import="kr.co.jboard1.vo.ArticleDTO"%>
<%@page import="kr.co.jboard1.dao.ArticleDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	
	String content = request.getParameter("content");
	
	String parent = request.getParameter("parent");
	String writer = request.getParameter("writer");
	
	String regip = request.getRemoteAddr();
	
	
	ArticleDTO dto = new ArticleDTO();
	dto.setParent(parent); //숫자를 문자열로 받음 -> DTO에 새로운 setter를 재정의한다.
	dto.setContent(content);
	dto.setWriter(writer);
	dto.setRegip(regip);
	
	
	ArticleDAO dao = new ArticleDAO();
	//댓글 입력
	dao.insetComment(dto);
	
	// 댓글 카운트 수정
	dao.updateArticleForComment(parent);
	response.sendRedirect("/Jboard1/view.jsp?no="+parent);
%>