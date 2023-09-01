package kr.co.jboard2.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.jboard2.dao.ArticleDAO;
import kr.co.jboard2.dto.ArticleDTO;
import kr.co.jboard2.dto.UserDTO;
import kr.co.jboard2.service.ArticleService;

@WebServlet("/list.do")
public class ListController extends HttpServlet{
	private static final long serialVersionUID = 7787169292569437228L;
	
	ArticleService service = ArticleService.Instance;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	ArticleDAO dao = new ArticleDAO();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//데이터 수신
		String pg = req.getParameter("pg");
		String search = req.getParameter("search");
		
		
		
		//현재 세션 가져오기
		HttpSession session = req.getSession();
		UserDTO sessUser = (UserDTO)session.getAttribute("sessUser");
		
		if(sessUser != null) {
			
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
		   	
		   	// 전체 게시물 갯수 조회
		   	total = service.selectCountTotal(search);
		   	
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
		   	
		   	// 페이지 시작번호 계산
		   	pageStartNum = total - start;
			
		   	req.setAttribute("pageGroupStart", pageGroupStart);
		   	req.setAttribute("pageGroupEnd", pageGroupEnd);
		   	req.setAttribute("currentPage", currentPage);
		   	req.setAttribute("lastPageNum", lastPageNum);
		   	
		   	//게시글 목록 조회
			List<ArticleDTO> articles = service.selectArticles(start, search);
			
			req.setAttribute("articles", articles);
			
			
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("list.jsp");
			dispatcher.forward(req, resp);
			
		}else {
			resp.sendRedirect("/Jboard2/user/login.do?success=101");
		}
		
	}
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
	
	
	
}
