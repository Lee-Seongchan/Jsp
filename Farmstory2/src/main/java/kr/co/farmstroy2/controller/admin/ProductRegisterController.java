package kr.co.farmstroy2.controller.admin;

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

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.co.farmstory2.dto.ProductDTO;
import kr.co.farmstory2.service.ArticleService;
import kr.co.farmstory2.service.ProductService;

@WebServlet("/admin/productRegister.do")
public class ProductRegisterController extends HttpServlet{

	private static final long serialVersionUID = 3044576915857353349L;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	ArticleService aService = ArticleService.INSTANCE;
	ProductService pService = ProductService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/productRegister.jsp");
		dispatcher.forward(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String path = aService.getPath(req, "/thumb");
		MultipartRequest mr = aService.uploadFile(req, path);
		
		
		String productName = mr.getParameter("productName");
		String type = mr.getParameter("type");
		String price = mr.getParameter("price");
		String delivery = mr.getParameter("delivery");
		String stock = mr.getParameter("stock");
		String thumb1 = mr.getOriginalFileName("thumb1");
		String thumb2 = mr.getOriginalFileName("thumb2");
		String thumb3 = mr.getOriginalFileName("thumb3");
		String seller = mr.getParameter("seller");
		String etc = mr.getParameter("etc");
		
		
		logger.debug("productName = " + productName);
		logger.debug("type = " + type);
		logger.debug("price = " + price);
		logger.debug("delivery = " + delivery);
		logger.debug("stock = " + stock);
		logger.debug("thumb1 = " + thumb1);
		logger.debug("thumb2 = " + thumb2);
		logger.debug("thumb3 = " + thumb3);
		logger.debug("seller = " + seller);
		logger.debug("etc = " + etc);
		
		
		ProductDTO dto = new ProductDTO(path);
		dto.setpName(productName);
		dto.setType(type);
		dto.setPrice(price);
		dto.setDelivery(delivery);
		dto.setStock(stock);
		dto.setThumb1ForRename(thumb1);
		dto.setThumb2ForRename(thumb2);
		dto.setThumb3ForRename(thumb3);
		dto.setSeller(seller);
		dto.setEtc(etc);
		
		logger.debug(dto.toString());
		
		
		// 상품등록
		pService.insertProduct(dto);
		
		// 리다이렉트
		resp.sendRedirect("/Farmstory2/admin/productRegister.do?success=200");
		
		
	}
	
}
