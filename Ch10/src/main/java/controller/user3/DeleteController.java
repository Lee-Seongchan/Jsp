package controller.user3;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.User3Service;

@WebServlet("/user3/delete.do")
public class DeleteController extends HttpServlet{
	private static final long serialVersionUID = 5646637100293364669L;
	
	private User3Service service = new User3Service();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uid = req.getParameter("uid");
		service.deleteUser3(uid);
		
		resp.sendRedirect("/Ch10/user3/list.do");
	}
	
}
