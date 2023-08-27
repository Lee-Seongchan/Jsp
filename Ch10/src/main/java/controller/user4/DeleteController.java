package controller.user4;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.User4Service;

@WebServlet("/user4/delete.do")
public class DeleteController extends HttpServlet{

	private static final long serialVersionUID = 3752275910252668559L;
	User4Service service = new User4Service();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String seq = req.getParameter("seq");
		service.deleteUser4(seq);

		resp.sendRedirect("/Ch10/user4/list.do");
	}
}
