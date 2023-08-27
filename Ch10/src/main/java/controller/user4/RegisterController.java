package controller.user4;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.User4DTO;
import service.User4Service;

@WebServlet("/user4/register.do")
public class RegisterController extends HttpServlet{
	private static final long serialVersionUID = -4518322775833297731L;
	User4Service service = new User4Service();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	 	RequestDispatcher dispatcher = req.getRequestDispatcher("/user4/register.jsp");
	 	dispatcher.forward(req, resp);
	 	
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		
		String name = req.getParameter("name");
		int gender = Integer.parseInt(req.getParameter("gender"));
		String age = req.getParameter("age");
		String addr = req.getParameter("addr");
		
		User4DTO dto = new User4DTO();
		
		dto.setName(name);
		dto.setGender(gender);
		dto.setAge(age);
		dto.setAddr(addr);
		
		service.insertUser4(dto);
		resp.sendRedirect("/Ch10/user4/list.do");
		
	}
}
