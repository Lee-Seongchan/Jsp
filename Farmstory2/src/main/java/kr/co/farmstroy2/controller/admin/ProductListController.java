package kr.co.farmstroy2.controller.admin;

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

import kr.co.farmstory2.dto.ProductDTO;
import kr.co.farmstory2.dto.UserDTO;
import kr.co.farmstory2.service.ProductService;

@WebServlet("/admin/productList.do")
public class ProductListController extends HttpServlet{

	private static final long serialVersionUID = 7418288182450539344L;

	Logger logger = LoggerFactory.getLogger(this.getClass());
	ProductService service = ProductService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session=req.getSession();
		UserDTO sessUser=(UserDTO)session.getAttribute("sessUser");
		
		if(sessUser == null) {			
			resp.sendRedirect("/Farmstory2/user/login?success=101");
			
		}else {
			
			//데이터수신
			String page =req.getParameter("page");
			String type = req.getParameter("type");
			
			
			if(type == null){
				type = "0";
			}

			//페이지 관련 변수
			int start=0;
			int currentPage =1;
			int total=0;
			int allproduct=0;
			int lastPageNum=0;
			int pageGroupCurrent=1;
			int pageGroupStart=1;
			int pageGroupEnd=0;
			int pageStartNum=0;
			
			
			//현재페이지계산
			if(page!=null){
				currentPage =Integer.parseInt(page);
				
			}
			
			//LIMIT 시작값계산
			start =(currentPage -1)*10;

			//전체 게시물 조회
			allproduct=service.selectCountProductsTotal(type);
			
			
			//타입별 게시물 갯수조회 
			total=service.selectCountProductsTotal(type);
			
			if(total%10 == 0){
				lastPageNum =(total/10);
			}else{
				lastPageNum =(total/10)+1;
			}
			
			//페이지 그룹계산
			pageGroupCurrent=(int) Math.ceil(currentPage/10.0);
			pageGroupStart=(pageGroupCurrent-1)*10+1;
			pageGroupEnd=pageGroupCurrent*10;
			
			if(pageGroupEnd > lastPageNum){
				pageGroupEnd=lastPageNum;
			}
			
			//페이지 시작번호 계산
			pageStartNum =total-start;
			
			
		logger.info("start : " + start);
		logger.info("currentPage : " + currentPage);
		logger.info("total : " + total);
		logger.info("lastPageNum : " + lastPageNum);
		logger.info("pageGroupCurrent : " + pageGroupCurrent);
		logger.info("pageGroupStart : " + pageGroupStart);
		logger.info("pageGroupEnd : " + pageGroupEnd);
		logger.info("pageStartNum : " + pageStartNum);
		
		List<ProductDTO>products = service.selectProducts(start);
		
		req.setAttribute("products", products);
		req.setAttribute("start", start);
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("total", total);
		req.setAttribute("lastPageNum", lastPageNum);
		req.setAttribute("pageGroupCurrent", pageGroupCurrent);
		req.setAttribute("pageGroupStart", pageGroupStart);
		req.setAttribute("pageGroupEnd", pageGroupEnd);
		req.setAttribute("pageStartNum", pageStartNum);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/productList.jsp");
		dispatcher.forward(req, resp);
		}
	}	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
	
	}
}
