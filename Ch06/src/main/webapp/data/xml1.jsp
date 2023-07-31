<%@ page contentType="text/xml;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	//xml 생성 (Key가 태그)
	String xmlData = "<user>";
			xmlData += "<uid>a101</uid>";
			xmlData += "<name>김유신</name>";
			xmlData += "<hp>010-2121-1001</hp>";
			xmlData += "<age>23</age>";
			xmlData += "</user>";
	
			
	//xml 출력
	out.print(xmlData);
	


%>