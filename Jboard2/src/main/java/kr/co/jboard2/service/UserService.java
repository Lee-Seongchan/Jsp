package kr.co.jboard2.service;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.jboard2.dao.UserDAO;
import kr.co.jboard2.dto.UserDTO;

public class UserService {
	
	private static UserService instance = new UserService();
	
	public static UserService getInstance() {
		return instance;
	}
	private UserService() {}
	
	private UserDAO dao = UserDAO.getInstance();
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static String generateCode;
	
	
	
	public void insertUser(UserDTO dto) {
		dao.insertUser(dto);
	}
	
	public UserDTO selectUser(String uid, String pass) {
		return dao.selectUser(uid, pass);
	}
	
	public List<UserDTO> selectUsers() {
		return dao.selectUsers();
	}
	public void updateUser(UserDTO dto) {
		dao.updateUser(dto);
	}
	public void deleteUser(String uid) {
		dao.deleteUser(uid);
	}
	
	
	//추가
	public int selectCountUid(String uid) {
		return dao.selectCountUid(uid);
	}
	
	public int selectCountNick(String nick) {
		return dao.selectCountNick(nick);
	}
	
	public int selectCountHp(String hp) {
		return dao.selectCountHp(hp);
	}
	
	public int selectCountEmail(String email) {
		return dao.selectCountEmail(email);
	}
	
	public int selectCountNameAndEmail(String name, String email) {
		return dao.selectCountNameAndEmail(name, email);
	}
	
	public UserDTO selectUserByNameAndEmail(String name, String email) {
		return dao.selectUserByNameAndEmail(name, email);
	}
	
	public int selectCountUidAndEmail(String uid, String email) {
		return dao.selectCountUidAndEmail(uid, email);
	}
	
	public int updateUserPass(String uid, String pass) {
		return dao.updateUserPass(uid, pass);
	}
	
	public int updateUserForWithdraw(String uid) {
		return dao.updateUserForWithdraw(uid);
	}
	
	
	
	public int sendCodeByEmail(String receiver) {
		//인증코드 생성
		int code = ThreadLocalRandom.current().nextInt(100000, 1000000);
		generateCode = ""+code;
		
		//기본 정보
		String sender = "lseong96@gmail.com";
		String password = "gkjvpjytpojsdhkc";
		String title = "Jboard2 인증 코드 입니다.";
		String content = "<h1>인증코드는 " + code + "</h1>";
		
		
		
		// Gmail SMTP 서버 설정
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		
		// Gmail SMTP 세션 설정
		Session gmailSession = Session.getInstance(props, new Authenticator(){
			
			@Override
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(sender, password);
			}
			
		});
		
		// 메일 발송
		int status = 0;
		Message message = new MimeMessage(gmailSession);
		
		
		try{
			logger.info("here1...");
			message.setFrom(new InternetAddress(sender, "보내는 사람", "UTF-8"));
			message.setRecipient(Message.RecipientType.TO , new InternetAddress(receiver));
			message.setSubject(title);
			message.setContent(content, "text/html;charset=UTF-8");
			Transport.send(message);		
			
			status = 1;//1이면 전송 성공
			
		}catch(Exception e){
			status = 0; //0이면 오류
			logger.error("sendCodeByEmail() error : " + e.getMessage());
		}
		
		return status;
		
	}// sendCodeByEmail end
	
	
	public int confirmCodeByEmail(String code) {
		
		if(code.equals(generateCode)) {
			logger.info("return 1...");
			return 1;
		}else {
			logger.info("return 0...");
			return 0;
		}
		
	}
	
}
