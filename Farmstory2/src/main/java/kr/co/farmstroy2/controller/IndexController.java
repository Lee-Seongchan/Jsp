package kr.co.farmstroy2.controller;

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

import kr.co.farmstory2.dto.ArticleDTO;
import kr.co.farmstory2.service.ArticleService;



@WebServlet(value = {"", "/index.do"})
public class IndexController extends HttpServlet{

	private static final long serialVersionUID = -2203926732656800956L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	ArticleService service = ArticleService.INSTANCE;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		List<ArticleDTO> storyLates = service.selectArticleLateste("story", 5);
		List<ArticleDTO> growLates = service.selectArticleLateste("grow", 5);
		List<ArticleDTO> schoolLates = service.selectArticleLateste("shool", 5);
		
		
		List<ArticleDTO> noticeLates = service.selectArticleLateste("notice", 3);
		List<ArticleDTO> quaLates = service.selectArticleLateste("qua", 3);
		List<ArticleDTO> faqLates = service.selectArticleLateste("faq", 3);
		
		
		logger.debug(storyLates.toString());
		logger.debug(growLates.toString());
		logger.debug(schoolLates.toString());
		
		req.setAttribute("storyLates", storyLates);
		req.setAttribute("growLates", growLates);
		req.setAttribute("schoolLates", schoolLates);
		
		req.setAttribute("noticeLates", noticeLates);
		req.setAttribute("quaLates", quaLates);
		req.setAttribute("faqLates", faqLates);
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
		dispatcher.forward(req, resp);	
	}
	
}
