package controller.user1;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.User1DTO;
import service.User1Service;


@WebServlet("/user1/list.do")
public class ListController extends HttpServlet {
	private static final long serialVersionUID = -9126243818521018693L;
	
	private User1Service service = new User1Service();


	public void init(ServletConfig config) throws ServletException {
	
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<User1DTO> users = service.selectUser1s();
		
		// View에서 users 참조하기 위해 request Scope 저장
		request.setAttribute("users", users);
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/user1/list.jsp");
		dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
