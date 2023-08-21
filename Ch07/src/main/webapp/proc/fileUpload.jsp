<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="java.io.File"%>
<%@page import="java.util.UUID"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	//파일폼 데이터 수신
	String path = application.getRealPath("/upload");
	int maxSize = 1024 * 1024 * 10; //10메가
	
	
	//MultipartRequest mr = new MultipartRequest(request,"파일이 저장되는 경로", 파일 크기, "UTF-8" , new DefaultfileRenamePolicy())
	MultipartRequest mr = new MultipartRequest(request, path, maxSize, "UTF-8" , new DefaultFileRenamePolicy());
	String uid = mr.getParameter("uid");
	String name = mr.getParameter("name");
	String fname = mr.getFilesystemName("fname");
	
									//		   123456789
 	//파일명 수정						// fname = tomcat 설치.txt
	int i = fname.lastIndexOf("."); // 확장자 앞에 있는 .  i =9
	String ext = fname.substring(i);	// ext = txt
	
	String uuid = UUID.randomUUID().toString();
	String sName = uuid + ext;
	
	File f1 = new File(path +"/" + fname);
	File f2 = new File(path +"/" + sName);
	
	f1.renameTo(f2);
	
	// 원본 파일명 INSERT (DB에 저장)
	Context ctx = (Context) new InitialContext().lookup("java:comp/env");	
	DataSource ds = (DataSource) ctx.lookup("jdbc/Jboard");
	
	
	try{
		Connection conn = ds.getConnection();
		PreparedStatement psmt	= conn.prepareStatement("INSERT INTO `FileTest` SET `oName` = ?, `sName` = ?, `rdate` = NOW() ");
		psmt.setString(1, fname);
		psmt.setString(2, sName);
		psmt.executeUpdate();
		
		psmt.close();
		conn.close();
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	// 파일 다운로드 이동
	response.sendRedirect("../2_FileDownload.jsp");
	
	
%>