package kr.co.farmstory2.controller.market;

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
import kr.co.farmstory2.service.UserService;

@WebServlet("/market/list.do")
public class ListController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private UserService uService = UserService.INSTANCE;
	private ProductService pService = ProductService.INSTANCE;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		

		
		//받아야할 값
		//pg , type
	
		HttpSession session=req.getSession();
		UserDTO sessUser=(UserDTO)session.getAttribute("sessUser");
		
		if(sessUser==null) {			
			resp.sendRedirect("/Farmstory2/user/login?success=101");
			
		}else {
			
			//데이터수신
			String page =req.getParameter("page");
			String type = req.getParameter("type");

			logger.debug("page = " +page);
			logger.debug("type = " +type);
			
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
			
			
			//타입별 게시물 갯수조회 
			total = pService.selectCountProductsTotal(type);
			
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
		
		
			List<ProductDTO> products = pService.selectProduct2s(type, pageStartNum);
			
			req.setAttribute("products", products);
			req.setAttribute("start", start);
			req.setAttribute("type", type);
			req.setAttribute("currentPage", currentPage);
			req.setAttribute("total", total);
			req.setAttribute("lastPageNum", lastPageNum);
			req.setAttribute("pageGroupCurrent", pageGroupCurrent);
			req.setAttribute("pageGroupStart", pageGroupStart);
			req.setAttribute("pageGroupEnd", pageGroupEnd);
			req.setAttribute("pageStartNum", pageStartNum);
			
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/market/list.jsp");
			dispatcher.forward(req, resp);
		}//if(sessUser==null) end
	}
}
