package kr.co.farmstory2.service;

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

import kr.co.farmstory2.dao.UserDAO;
import kr.co.farmstory2.db.SQL;
import kr.co.farmstory2.dto.UserDTO;

public enum UserService {
	
	INSTANCE;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	UserDAO dao = new UserDAO();
	
	private static String generateCode;
	
	
	public void insertUser(UserDTO dto) {
		dao.insertUser(dto);
	}
	
	public UserDTO selectUser(String uid, String pass) {
		return dao.selectUser(uid, pass);
	}


	public void selectUsers() {
		
	}
	
	public void updateUser() {
		
	}
	
	public void deleteUser() {
		
	}
	
	
	
	
	//추가
	
	public int selectCheckUid(String uid) {
		return dao.selectCheckUid(uid);
	}
	
	//닉 중복체크
	public int selectCheckNick(String nick) {
		return dao.selectCheckNick(nick);
	}
	//휴대폰 중복 체크
	public int selectCheckHp(String hp) {
		return dao.selectCheckHp(hp);
	}

	//email 중복 체크
	public int selectCheckEmail(String email) {
		return dao.selectCheckEmail(email);
	}
	
	
	//이메일 코드 발송
	public int sendCodeByEmail(String receiver) {
		//인증코드 생성
		int code = ThreadLocalRandom.current().nextInt(100000, 1000000);
		generateCode = ""+code;
		
		//기본 정보
		String sender = "lseong96@gmail.com";
		String password = "gkjvpjytpojsdhkc";
		String title = "Farmstory2 인증 코드 입니다.";
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
	
	//이메일 인증 코드 확인 
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
