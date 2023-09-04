package kr.co.farmstory2.controller.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.farmstory2.dto.TermsDTO;
import kr.co.farmstory2.service.TermsService;

@WebServlet("/user/terms.do")
public class TermsController extends HttpServlet{

	private static final long serialVersionUID = 1368971640139096854L;
	
	TermsService service = TermsService.INSTANCE;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		TermsDTO terms = service.selectTerms();
		req.setAttribute("terms", terms);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/user/terms.jsp");
		dispatcher.forward(req, resp);
	}
	
}
