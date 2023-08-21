<%@page import="java.io.BufferedOutputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="org.apache.tomcat.jni.FileInfo"%>
<%@page import="java.io.BufferedInputStream"%>
<%@page import="java.nio.Buffer"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dto.FileDTO"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.Context"%>
<%@page import="java.util.List"%>
<%@page import="java.io.File"%>
<%@page import="java.net.URLEncoder"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 파일 정보 수신
	request.setCharacterEncoding("UTF-8");
	String fno = request.getParameter("fno");
	
	// 파일 정보 조회
	Context ctx = (Context) new InitialContext().lookup("java:comp/env");	
	DataSource ds = (DataSource) ctx.lookup("jdbc/Jboard");
	
	FileDTO dto = new FileDTO();
	
	try{
	Connection conn = ds.getConnection();
	Statement stmt = conn.createStatement();
	ResultSet rs = stmt.executeQuery("SELECT * FROM `FileTest` WHERE `fno`= "+fno);
	
	if(rs.next()){
		dto.setFno(rs.getInt(1));
		dto.setoName(rs.getString(2));
		dto.setsName(rs.getString(3));
		dto.setRdate(rs.getString(4));
		
	}
	
	stmt.close();
	conn.close();
	rs.close();
	
	}catch(Exception e){
		e.printStackTrace();
	}

	
	// response 파일 다운로드 헤더 수정(복붙)
	response.setContentType("application/octet-stream");
	response.setHeader("Content-Disposition", "attachment; filename="+URLEncoder.encode(dto.getoName(), "utf-8"));	
	response.setHeader("Content-Transfer-Encoding", "binary");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "private");

	//response 파일 스트림 작업
	String path = application.getRealPath("/upload");
	File file = new File(path + "/"+dto.getsName());
	
	out.clear();
	//스트림 생성(연결)
	//입력 스트림
	BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
	
	//출력 스트림
	BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream()); 
	
	while(true){
		
		//파일 읽기(byte 단위의 데이터 이기 때문에 int)
		int data = bis.read();
		if(data == -1){
			break;
		}
		//파일쓰기
		bos.write(data);
	}
	
	bos.close();
	bis.close();
	

%>