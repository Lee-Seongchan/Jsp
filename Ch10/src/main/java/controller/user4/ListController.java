package controller.user4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dto.User4DTO;
import service.User4Service;

@WebServlet("/user4/list.do")
public class ListController extends HttpServlet{
	private static final long serialVersionUID = 5110263817807050311L;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private User4Service service = new User4Service();
	
	@Override
	public void init() throws ServletException {
	
		
	}
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("User4 doGet()...");
		
		List<User4DTO>users = service.selectUser4s();
		request.setAttribute("users", users);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/user4/list.jsp");
		dispatcher.forward(request, response);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	}
	
}
