package sub1;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//서블릿을 사용하기 위해서는 httpServlet을 상속받아야 한다
public class HelloServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	// WAS에 로드될 때 
	@Override
	public void init() throws ServletException {
		// 서블릿이 최초 실행될 때 
		System.out.println("HelloServlet init()...");
	}
	
	
	@Override						
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 클라이언트 GET 요청할 때 
		System.out.println("HelloServlet doGet()...");
		
		//HTML출력
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = resp.getWriter();
		writer.println("<html>");
		writer.println("<head>");
		writer.println("<meta charset='UTF-8'>");
		writer.println("</head>");
		writer.println("<body>");
		writer.println("<h3>HelloServlet</h3>");
		writer.println("<a href='./1_Servlet.jsp'>서블릿 메인</a>");
		writer.println("<a href='./hello.do'>HelloServlet</a>");
		writer.println("<a href='./welcome.do'>WelcomeServlet</a>");
		writer.println("<a href='./greeting.do'>GreetingServlet</a>");
		writer.println("</body>");
		writer.println("</html>");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 클라이언트의 POST 요청할 때
		System.out.println("HelloServlet doPost()...");
	
		String uid = req.getParameter("uid");
		String pass = req.getParameter("pass");
		
		// 현재 세션 가져오기
		HttpSession session = req.getSession();
		
		if(uid.equals("a101") && pass.equals("1234")) {
			// 사용자가 a101라고 가정하고 로그인 처리
			session.setAttribute("sessUid", uid);
			

			resp.sendRedirect("/Ch09/3_Listener.jsp?success=200");
		}else {
			// 로그인 실패 가정
			resp.sendRedirect("/Ch09/3_Listener.jsp?success=100");
		}
	}
	
	@Override
	public void destroy() {
		//서블릿 종료될 때
		System.out.println("HelloServlet destory()...");
	}
	
}
