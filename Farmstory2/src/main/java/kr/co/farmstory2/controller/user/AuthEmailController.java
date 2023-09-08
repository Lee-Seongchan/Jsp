package kr.co.farmstory2.controller.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

import kr.co.farmstory2.service.UserService;




@WebServlet("/user/authEmail.do")
public class AuthEmailController extends HttpServlet{

	private static final long serialVersionUID = 3422854268531008300L;
	UserService service = UserService.INSTANCE;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String type = req.getParameter("type");
		String email = req.getParameter("email");
		
		logger.info("email : " + email);
		logger.info("type : " + type);
		
		int status = 0;
		int result = 0;
		
		
		if(type.equals("REGISTER")) {
			// 회원가입할 때 이메일 인증
			result = service.selectCheckEmail(email);
			
			logger.debug("result - " + result);
			
			if(result == 0) {
				status = service.sendCodeByEmail(email);
				logger.debug("status - " + status);				
			}
		
		}
		
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		json.addProperty("status", status);
		
		PrintWriter writer = resp.getWriter();
		writer.print(json.toString());
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String code = req.getParameter("code");
		
		int result = service.confirmCodeByEmail(code);
		
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		
		PrintWriter writer = resp.getWriter();
		writer.print(json.toString());
	}
	
	
}
