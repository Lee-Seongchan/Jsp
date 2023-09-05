package kr.co.farmstroy2.controller.board;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.co.farmstory2.dto.ArticleDTO;
import kr.co.farmstory2.dto.FileDTO;
import kr.co.farmstory2.service.ArticleService;
import kr.co.farmstory2.service.FileService;



@WebServlet("/board/write.do")
public class WriteController extends HttpServlet{

	private static final long serialVersionUID = -6205936701729917116L;

	ArticleService aService = ArticleService.INSTANCE;
	FileService fService = FileService.INSTANCE;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String group = req.getParameter("group");
		String cate  = req.getParameter("cate");
		
		req.setAttribute("group", group);
		req.setAttribute("cate", cate);
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/board/write.jsp");
		dispatcher.forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 파일 업로드 경로 구하기 
		ServletContext ctx = req.getServletContext();
		String path = ctx.getRealPath("/upload");
		
		// 최대 업로드 파일 크기
		int maxSize = 1024 * 1024 * 10;
		
		// 파일 업로드 및 Multipart 객체 생성
		MultipartRequest mr = new MultipartRequest(req, path, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		
		//데이터 폼 수신
		
		String group   = mr.getParameter("group");
		String cate    = mr.getParameter("cate");
		String title   = mr.getParameter("title");
		String oriName = mr.getOriginalFileName("file");
		String content = mr.getParameter("content");
		String writer  = mr.getParameter("writer");	
		String regip   = req.getRemoteAddr();
		
		logger.debug("title = " + title);
		logger.debug("content = " + content);
		logger.debug("writer = " + writer);
		logger.debug("oriName = " + oriName);
		logger.debug("regip= " + regip);
		logger.debug("cate= " + cate);
		logger.debug("group= " + group);
		
		// DTO 생성
		ArticleDTO dto = new ArticleDTO();
		dto.setCate(cate);
		dto.setTitle(title);
		dto.setContent(content);
		dto.setFile(oriName);
		dto.setWriter(writer);
		dto.setRegip(regip);

		// 글 Insert
		int no = aService.insertArticle(dto);
		
		// 파일명 수정 및 파일 Insert
		if(oriName != null) {
			
			int i = oriName.lastIndexOf(".");
			String ext = oriName.substring(i);
			
			String uuid  = UUID.randomUUID().toString();
			String newName = uuid + ext;
			
			File f1 = new File(path+"/"+oriName);
			File f2 = new File(path+"/"+newName);
			
			// 파일명 수정
			f1.renameTo(f2);
			
			// 파일 테이블 Insert
			FileDTO fileDto = new FileDTO();
			fileDto.setAno(no);
			fileDto.setOriName(oriName);
			fileDto.setNewName(newName);
			
			fService.insertFile(fileDto);
		}

		
		// 리다이렉트
		resp.sendRedirect("/Farmstory2/board/list.do?group="+group+"&cate="+cate);

	}
	
}
