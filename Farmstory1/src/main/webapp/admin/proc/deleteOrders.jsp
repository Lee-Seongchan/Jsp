<%@page import="kr.farmstory1.dao.OrderDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	//http://localhost:8080/Farmstory1/admin/proc/deleteOrders.jsp?chk=6&chk=7&chk=12 같은 chk를 반복
	
	String[] chks = request.getParameterValues("chk");
	
	OrderDAO dao = new OrderDAO();
	
	for(String orderNo : chks){
		dao.deleteOrder(orderNo);
	}

	response.sendRedirect("../orderList.jsp");
%>