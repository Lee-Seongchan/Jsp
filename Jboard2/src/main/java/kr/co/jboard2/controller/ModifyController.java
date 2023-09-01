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

import kr.co.jboard2.dto.ArticleDTO;
import kr.co.jboard2.service.ArticleService;

@WebServlet("/modify.do")
public class ModifyController extends HttpServlet{
	private static final long serialVersionUID = -8989721235114878879L;

	ArticleService service = ArticleService.Instance;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String no = req.getParameter("no");
		ArticleDTO article = service.selectArticle(no);
		
		logger.debug("no = " + no);
		logger.debug("article = " + article);
		
		req.setAttribute("article", article);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("modify.jsp");
		dispatcher.forward(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		 String no = req.getParameter("no"); 
		 String title = req.getParameter("title");
		 String content = req.getParameter("content");
		  
		 logger.debug("no = " + no);
		 logger.debug("title = " + title);
		 logger.debug("content = " + content);
		 
		 ArticleDTO dto = new ArticleDTO(); 
		 dto.setNo(no); 
		 dto.setTitle(title);
		 dto.setContent(content);
		  
		 service.updateArticle(dto);
		  
		 resp.sendRedirect("/Jboard2/view.do?no="+no);
		 
		
	}
	
}
