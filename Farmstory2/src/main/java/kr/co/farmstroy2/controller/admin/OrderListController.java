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

import kr.co.farmstory2.dto.OrderDTO;
import kr.co.farmstory2.service.OrderService;

@WebServlet("/admin/orderList.do")
public class OrderListController extends HttpServlet{
	private static final long serialVersionUID = -114258389208792294L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	OrderService service = OrderService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<OrderDTO> orders = service.selectOrders();
		
		req.setAttribute("orders", orders);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/orderList.jsp");
		dispatcher.forward(req, resp);
	}
}
