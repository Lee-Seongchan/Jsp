package kr.co.farmstroy2.controller.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import kr.co.farmstory2.dto.ArticleDTO;
import kr.co.farmstory2.service.ArticleService;

@WebServlet("/board/comment.do")
public class CommentController extends HttpServlet{

	private static final long serialVersionUID = 3067437297483467517L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	ArticleService service = ArticleService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String no = req.getParameter("no");
		String kind = req.getParameter("kind");
		
		logger.debug("no = " + no);
		logger.debug("kind = " + kind);
		
		int result = 0;
		
		switch (kind) {
			case "REMOVE" :
				result = service.deleteComment(no);
			break;

		default:
			break;
		}
		//json 생성
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		
	
		resp.getWriter().print(json);
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String parent = req.getParameter("parent");
		String content = req.getParameter("content");
		String writer = req.getParameter("writer");
		String regip = req.getRemoteAddr();
		
		logger.debug("parent = " + parent);
		logger.debug("content = " + content);
		logger.debug("writer = " + writer);
		logger.debug("regip = " + regip);
		
		ArticleDTO dto = new ArticleDTO();
		dto.setWriter(writer);
		dto.setParent(parent);
		dto.setContent(content);
		dto.setRegip(regip);
		
		int result = service.insertComment(dto);
		logger.debug("result = " + result);

		//json 생성
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		
	
		resp.getWriter().print(json);
		
		//resp.setContentType("application/json;charset=UTF-8"); // <-- 이거해야 클라이언트로 전송되는 JSON 한글 안깨짐
		
		//json 생성
		//Gson gson = new Gson();
		//json 객체 만들기
		//String strJson = gson.toJson(result);
		//출력
		//resp.getWriter().print(strJson);
		
		
	
	}
	
}
