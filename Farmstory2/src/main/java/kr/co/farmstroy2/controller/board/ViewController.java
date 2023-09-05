package kr.co.farmstroy2.controller.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory2.dto.ArticleDTO;
import kr.co.farmstory2.service.ArticleService;

@WebServlet("/board/view.do")
public class ViewController extends HttpServlet{

	private static final long serialVersionUID = -1764844321193357911L;

	Logger logger = LoggerFactory.getLogger(this.getClass());
	ArticleService service = ArticleService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String group = req.getParameter("group");
		String cate  = req.getParameter("cate");
		
		String no = req.getParameter("no");
		logger.debug("group = " + group);
		logger.debug("cate = " + cate);
		logger.debug("no = " + no);
		
		//글 보기
		ArticleDTO article = service.selectArticle(no);
		
		req.setAttribute("article", article);
		req.setAttribute("group", group);
		req.setAttribute("cate", cate);
		
		
	
		RequestDispatcher dispatcher = req.getRequestDispatcher("/board/view.jsp");
		dispatcher.forward(req, resp);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	
	
	}
	
}
