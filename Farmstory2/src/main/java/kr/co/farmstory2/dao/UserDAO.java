package kr.co.farmstory2.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory2.db.DBHelper;
import kr.co.farmstory2.db.SQL;
import kr.co.farmstory2.dto.UserDTO;

public class UserDAO extends DBHelper{
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void insertUser(UserDTO dto) {
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.INSERT_USER);
			psmt.setString(1, dto.getUid());
			psmt.setString(2, dto.getPass());
			psmt.setString(3, dto.getName());
			psmt.setString(4, dto.getNick());
			psmt.setString(5, dto.getEmail());
			psmt.setString(6, dto.getHp());
			psmt.setString(7, dto.getZip());
			psmt.setString(8, dto.getAddr1());
			psmt.setString(9, dto.getAddr2());
			psmt.setString(10, dto.getRegip());
			psmt.executeUpdate();
			
		} catch (Exception e) {
			logger.error("insertUser()..." + e.getMessage());
		}
	}
	
	public UserDTO selectUser(String uid, String pass) {
		
		UserDTO user = null;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_USER);
			psmt.setString(1, uid);
			psmt.setString(2, pass);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				user = new UserDTO();
				user.setUid(rs.getString(1));
				user.setPass(rs.getString(2));
				user.setName(rs.getString(3));
				user.setNick(rs.getString(4));
				user.setEmail(rs.getString(5));
				user.setHp(rs.getString(6));
				user.setRole(rs.getString(7));
				user.setZip(rs.getString(8));
				user.setAddr1(rs.getString(9));
				user.setAddr2(rs.getString(10));
				user.setRegip(rs.getString(11));
				user.setRegDate(rs.getString(12));
				user.setLeaveDate(rs.getString(13));
				
				close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	
	}


	public void selectUsers() {
		
	}
	
	public void updateUser() {
		
	}
	
	public void deleteUser() {
		
	}
	
	
	
	//추가
	
	//아이디 중복 체크
	public int selectCheckUid(String uid) {
		int result = 0;
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_CHECK_UID);
			psmt.setString(1, uid);
			rs= psmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			close();
			
		} catch (Exception e) {
			logger.error("selectCheckUid()..." + e.getMessage());
		}
		
		
		return result;
	}
	
	//닉 중복체크
	public int selectCheckNick(String nick) {
		int result = 0;
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_CHECK_NICK);
			psmt.setString(1, nick);
			rs= psmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
			close();
			
		} catch (Exception e) {
			logger.error("selectCheckUid()..." + e.getMessage());
		}
		
		
		return result;
	}
	
	//휴대폰 중복 체크
		public int selectCheckHp(String hp) {
			int result = 0;
			
			try {
				conn = getConnection();
				psmt = conn.prepareStatement(SQL.SELECT_COUNT_HP);
				psmt.setString(1, hp);
				rs= psmt.executeQuery();
				
				if(rs.next()) {
					result = rs.getInt(1);
				}
				close();
				
			} catch (Exception e) {
				logger.error("selectCheckHp() " + e.getMessage());
			}
			
			return result;
		}
	
	
	
	//email 중복 체크
	public int selectCheckEmail(String email) {
		int result = 0;
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_CHECK_EMAIL);
			psmt.setString(1, email);
			rs= psmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			close();
			
		} catch (Exception e) {
			logger.error("selectCheckEmail() " + e.getMessage());
		}
		return result;
	}
}
