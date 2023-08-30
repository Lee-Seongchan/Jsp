package kr.co.jboard2.controller.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

import kr.co.jboard2.dto.UserDTO;
import kr.co.jboard2.service.UserService;

@WebServlet("/user/myInfo.do")
public class MyInfoController extends HttpServlet{
	private static final long serialVersionUID = 4104451605699619555L;

	Logger logger = LoggerFactory.getLogger(this.getClass());
	UserService service = UserService.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/user/myInfo.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// doPost가 받는 타입
		// 1. 비밀번호 변경
		// 2. 회원 탈퇴
		// 3. 회원 수정
		String kind = request.getParameter("kind");
		String uid = request.getParameter("uid");
		String pass = request.getParameter("pass");
		String name = request.getParameter("name");
		String nick = request.getParameter("nick");
		String email = request.getParameter("email");
		String hp = request.getParameter("hp");	
		String zip = request.getParameter("zip");
		String addr1 = request.getParameter("addr1");
		String addr2 = request.getParameter("addr2");
		
		
		logger.debug("kind : " + kind);
		logger.debug("uid : " + uid);
		logger.debug("pass : " + pass);
		
		
		switch(kind) {
		case "WITHDRAW" :
				int result1 = service.updateUserForWithdraw(uid); //result = 1
				//json생성
				JsonObject json1 = new JsonObject();
				json1.addProperty("result", result1);
				//json출력
				response.getWriter().print(json1);
				
				//ajax의 응답결과는 json이 되어야 하기 때문에 redirect 응답 처리는 안됨
				//resp.sendRedirect("/Jboard2/user/login.do?success=400");
				break;
		case "PASSWORD" :
			
				logger.debug("here...1");
				int result2 = service.updateUserPass(uid, pass);
				
				logger.debug("here...2");
				//json생성
				JsonObject json2 = new JsonObject();
				json2.addProperty("result", result2);
				
				logger.debug("here...3 : " + result2);
				
				//json출력
				response.getWriter().print(json2);
				break;
		case "MODIFY" :
				UserDTO dto = new UserDTO();

				dto.setUid(uid);
				dto.setName(name);
				dto.setNick(nick);
				dto.setEmail(email);
				dto.setHp(hp);
				dto.setZip(zip);
				dto.setAddr1(addr1);
				dto.setAddr2(addr2);
				
				service.updateUser(dto);
				response.sendRedirect("/Jboard2/user/logout.do");
				
				
				break;
		}
		
		
	}
	
	
}
