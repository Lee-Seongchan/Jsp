
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.nio.file.Files"%>
<%@page import="dto.FileDTO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.Context"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
	Context ctx = (Context) new InitialContext().lookup("java:comp/env");	
	DataSource ds = (DataSource) ctx.lookup("jdbc/Jboard");
	
	List<FileDTO> files = new ArrayList<>();
	
	try{
	Connection conn = ds.getConnection();
	Statement stmt = conn.createStatement();
	ResultSet rs = stmt.executeQuery("SELECT * FROM `FileTest`");
	
	while(rs.next()){
		FileDTO dto = new FileDTO();
		dto.setFno(rs.getInt(1));
		dto.setoName(rs.getString(2));
		dto.setsName(rs.getString(3));
		dto.setRdate(rs.getString(4));
		files.add(dto);
		
		
	}
	
	stmt.close();
	conn.close();
	rs.close();
	
	}catch(Exception e){
		e.printStackTrace();
	}


%>


<html>
	<head>
		<meta charset="UTF-8">
		<title>2_FileDownload</title>
	</head>
	<body>
		<h3>파일 다운로드 실습</h3>
		
		<table>
			<tr>
				<th>파일번호</th>
				<th>원본파일명</th>
				<th>저장파일명</th>
				<th>등록일</th>
			</tr>
			
			<%for(FileDTO file : files){ %>
			<tr>
				<td><%=file.getFno() %></td>
				<td><%=file.getoName() %></td>
				<td><%=file.getsName() %></td>
				<td><%=file.getRdate() %></td>
				<td><a href="./proc/fileDownload.jsp?fno=<%=file.getFno()%>">다운로드</a></td>				
			</tr>
			<%} %>
		</table>
	</body>
</html>