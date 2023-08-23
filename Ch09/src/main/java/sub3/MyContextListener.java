package sub3;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

//WAS 실행과 종료를 감시하는 옵저버 
public class MyContextListener implements ServletContextListener{
	
	//WAS 실행시 출력
	public MyContextListener() {
		System.out.println("MyContextListener()...");
	}
	
	//WAS 실행시 출력
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("contextInitialized()...");
	}
	
	
	//WAS 종료시 출력
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("contextDestroyed()...");
	}
	
	
	
}
