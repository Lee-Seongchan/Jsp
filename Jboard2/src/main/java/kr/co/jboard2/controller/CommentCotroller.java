package kr.co.jboard2.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

import kr.co.jboard2.dto.ArticleDTO;
import kr.co.jboard2.service.ArticleService;

@WebServlet("/comment.do")
public class CommentCotroller extends HttpServlet{

	ArticleService service = ArticleService.Instance;
	Logger logger = LoggerFactory.getLogger(this.getClass());
		
	private static final long serialVersionUID = 3223854630441341143L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	

		String kind = req.getParameter("kind");
		String no = req.getParameter("no");
		
		int result = 0;
		
		switch (kind) {
			case "REMOVE": 
				result = service.deleteComment(no);
				
				break;
	
		}
		
		//json 출력
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		resp.getWriter().print(json);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String parent = req.getParameter("parent");
		String writer = req.getParameter("writer");
		String content = req.getParameter("content");
		String regip = req.getRemoteAddr();
		
		
		logger.debug("CommentController parent = " + parent);
		logger.debug("CommentController writer = " + writer);
		logger.debug("CommentController content =" + content);
		logger.debug("CommentController regip = " + regip);
		
		
		ArticleDTO dto = new ArticleDTO();
		dto.setWriter(writer);
		dto.setParent(parent);
		dto.setContent(content);
		dto.setRegip(regip);
		
		//댓글 입력
		int result = service.insertComment(dto);
		logger.debug("result = " + result);
		//댓글 카운터 수정
		service.commentCountPlus(parent);			

		//리다이렉트
		//resp.sendRedirect("/Jboard2/view.do?no="+no);
		
		//Json 출력(Ajax요청)
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		resp.getWriter().print(json);
	}
	
}
