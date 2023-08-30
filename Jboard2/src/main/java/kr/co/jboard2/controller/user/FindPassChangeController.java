package kr.co.jboard2.controller.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.jboard2.service.UserService;

@WebServlet("/user/findPassChange.do")
public class FindPassChangeController extends HttpServlet{
	private static final long serialVersionUID = 3184963205046179155L;
	UserService service = UserService.getInstance();
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//이메일 인증 받고 오지 않았을 때 인가 여부
		
		HttpSession session = req.getSession();
		String uid = (String)session.getAttribute("uid");
		
		if(uid == null) {
			resp.sendRedirect("/Jboard2/user/findPass.do");
		}else {
			RequestDispatcher dispatcher = req.getRequestDispatcher("/user/findPassChange.jsp");
			dispatcher.forward(req, resp);
		}
		
		
	
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uid = req.getParameter("uid");
		String pass = req.getParameter("pass1");
		
		service.updateUserPass(uid, pass);
		
		resp.sendRedirect("/Jboard2/user/login.do?success=300"); // 리타이렉트 요청 방식은 get방식의 전송
		
	}	
}
