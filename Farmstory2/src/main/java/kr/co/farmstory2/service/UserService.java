package kr.co.farmstory2.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory2.dao.UserDAO;
import kr.co.farmstory2.db.SQL;
import kr.co.farmstory2.dto.UserDTO;

public enum UserService {
	
	INSTANCE;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	UserDAO dao = new UserDAO();
	
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
}
