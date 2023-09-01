package kr.co.jboard2.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.jboard2.dao.ArticleDAO;
import kr.co.jboard2.dto.ArticleDTO;
import kr.co.jboard2.service.ArticleService;

@WebServlet("/view.do")
public class ViewController extends HttpServlet{

	private static final long serialVersionUID = -1638433365507040980L;
	
	ArticleService service = ArticleService.Instance;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String no = req.getParameter("no");
		
		logger.debug("ViewController no = " + no);
		
		//게시글 보기
		ArticleDTO article = service.selectArticle(no);
		
		//댓글 출력
		List<ArticleDTO> comments = service.selectComments(no);
		
		//VIEW 공유 참조
		req.setAttribute("article", article);
		req.setAttribute("comments", comments);
				
		RequestDispatcher dispatcher = req.getRequestDispatcher("/view.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
