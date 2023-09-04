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

@WebServlet("/user/checkUid.do")
public class CheckUidController extends HttpServlet{
	private static final long serialVersionUID = -4413450232588058294L;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	UserService service = UserService.INSTANCE;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String uid = req.getParameter("uid");
		logger.debug("uid = " + uid);
		
		int result = service.selectCheckUid(uid);
		logger.debug("result = " + result);
		
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		
		PrintWriter writer = resp.getWriter();
		writer.print(json.toString());
		//writer.print(json.toString());:
		//다음으로 JSON 객체(json)를 문자열로 변환하고, 
		//그 문자열을 writer를 사용하여 응답 출력 스트림에 출력합니다.
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
}
