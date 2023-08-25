package controller;

import java.io.IOException;
import java.security.Provider.Service;
import java.util.List;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jdt.internal.compiler.ast.ThisReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.MemberDAO;
import dto.MemberDTO;
import service.MemberService;

@WebServlet("/list.do")
public class ListController extends HttpServlet{

	private static final long serialVersionUID = -3262191788834862821L;
	
	//enum을 활용한 싱글톤 객체
	private MemberService service = MemberService.INSTANCE;
	
	//로거 생성
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void init() throws ServletException {
	
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	logger.info("ListController doGet...1");	
		
	List<MemberDTO> members = service.selectMembers();
	
	req.setAttribute("members", members);
	RequestDispatcher dispatcher = req.getRequestDispatcher("/list.jsp");
	dispatcher.forward(req, resp);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
