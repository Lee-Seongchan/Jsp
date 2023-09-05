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


@WebServlet("/board/modify.do")
public class ModifyController extends HttpServlet{

	private static final long serialVersionUID = 3024164010413509009L;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	ArticleService service = ArticleService.INSTANCE;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String group = req.getParameter("group");
		String cate  = req.getParameter("cate");
		String no  = req.getParameter("no");
		
		logger.debug("group = " + group);
		logger.debug("cate = " + cate);
		logger.debug("no = " + no);
		
		ArticleDTO article = service.selectArticle(no);
		
		req.setAttribute("group", group);
		req.setAttribute("cate", cate);
		req.setAttribute("article", article);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/board/modify.jsp");
		dispatcher.forward(req, resp);
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 String no = req.getParameter("no"); 
		 String cate = req.getParameter("cate"); 
		 String group = req.getParameter("group"); 
		 String title = req.getParameter("title");
		 String content = req.getParameter("content");
		 
		 ArticleDTO dto = new ArticleDTO();
		 dto.setNo(no);
		 dto.setCate(cate);
		 dto.setTitle(title);
		 dto.setContent(content);
		 
		service.updateArticle(dto);
		 
		resp.sendRedirect("/Farmstory2/board/view.do?group="+group+"&cate="+cate+"&no="+no);
	}
}
