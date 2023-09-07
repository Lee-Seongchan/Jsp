package kr.co.farmstroy2.controller.board;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory2.service.ArticleService;
import kr.co.farmstory2.service.FileService;

@WebServlet("/board/delete.do")
public class DeleteController extends HttpServlet{
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	ArticleService aService = ArticleService.INSTANCE;
	FileService fService = FileService.INSTANCE;
	
	private static final long serialVersionUID = -5758351615097459949L;

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 글 번호 수신
		
		String no = req.getParameter("no");
		String group = req.getParameter("group");
		String cate = req.getParameter("cate");
		
		logger.debug("no = " + no );
		logger.debug("group = " + group );
		logger.debug("cate = " + cate );
		
		
		
		//파일 삭제(DB)
		List<String> snames = fService.deleteFile(no);
		
		
		//파일 삭제(Directory) 파일이 없으면 에러가 뜨기 때문에 조건문을 걸어줌
		if(!snames.isEmpty()) {
			// 파일 업로드 경로 구하기
			ServletContext ctx = req.getServletContext();
			String path = ctx.getRealPath("/upload");
			
			for(String sname : snames) {
				File file = new File(path+"/"+sname);
				
				if(file.exists()) {
					file.delete();	
				}
			}	
		}
		
		//글 + 댓글 삭제
		aService.deleteArticle(no);

		
		//리다이렉트
		resp.sendRedirect("/Farmstory2/board/list.do?group="+group+"&cate="+cate+"&pg=1");
	}
	
	
}
