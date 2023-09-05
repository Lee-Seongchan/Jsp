package kr.co.farmstroy2.controller.board;

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

@WebServlet("/board/list.do")
public class ListController extends HttpServlet{

	private static final long serialVersionUID = 8754072670749877797L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	ArticleService service = ArticleService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pg = req.getParameter("pg");
		
		String group = req.getParameter("group");
		String cate  = req.getParameter("cate");

		
		logger.debug("pg = " + pg);
		logger.debug("group = " + group);
		logger.debug("cate = " + cate);
	
		
		req.setAttribute("group", group);
		req.setAttribute("cate", cate);
	
	
		int start = 0;
	   	int currentPage = 1;
	   	int total = 0;
	   	int lastPageNum = 0;
	   	int pageGroupCurrent = 1;
	   	int pageGroupStart = 1;
	   	int pageGroupEnd = 0;
	   	int pageStartNum = 0;
	   	
	   	
	   	// 현재 페이지 계산
	   	if(pg != null){
	   		currentPage = Integer.parseInt(pg);
	   	}
	   	
	   	// Limit 시작값 계산
	   	start = (currentPage - 1) * 10; 
	   	
	   	
	   	//전체 게시물 갯수조회
		total = service.selectCountTotal(cate);
	   	
	   	// 페이지 번호 계산
	   	if(total % 10 == 0){
	   		lastPageNum = (total / 10); 
	   	}else {	
	   		lastPageNum = (total / 10) + 1;
	   	}
	   	
	   	
	   	
	   	//페이지 그룹 계산						
	   	pageGroupCurrent = (int)Math.ceil(currentPage / 10.0); // int(11.5) //ceil 올림메서드 => 12
	   	pageGroupStart = (pageGroupCurrent - 1) * 10 + 1;      // 111
	   	pageGroupEnd = pageGroupCurrent * 10;				   // 120
	   														   
	   	if(pageGroupEnd > lastPageNum){
	   		pageGroupEnd = lastPageNum;
	   	}
	   	
	   	
	   	logger.info("start : " + start);
		logger.info("currentPage : " + currentPage);
		logger.info("total : " + total);
		logger.info("lastPageNum : " + lastPageNum);
		logger.info("pageGroupCurrent : " + pageGroupCurrent);
		logger.info("pageGroupStart : " + pageGroupStart);
		logger.info("pageGroupEnd : " + pageGroupEnd);
		logger.info("pageStartNum : " + pageStartNum);
		logger.info("cate : " + cate);
		logger.info("group : " + group);
	   	
	   	
	   	
	   	// 페이지 시작번호 계산
	   	pageStartNum = total - start + 1;
		
	   	req.setAttribute("start", start);
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("total", total);
        req.setAttribute("lastPageNum", lastPageNum);
        req.setAttribute("pageGroupCurrent", pageGroupCurrent);
        req.setAttribute("pageGroupStart", pageGroupStart);
        req.setAttribute("pageGroupEnd", pageGroupEnd);
        req.setAttribute("pageStartNum", pageStartNum);
        req.setAttribute("group", group);
        req.setAttribute("cate", cate);
		
		
		//현재 게시글 조회
		List<ArticleDTO> articles = service.selectArticles(cate, start);

		req.setAttribute("articles", articles);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/board/list.jsp");
		dispatcher.forward(req, resp);
	}
}
