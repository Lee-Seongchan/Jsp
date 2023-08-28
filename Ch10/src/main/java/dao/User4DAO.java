package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import db.DBHelper;
import dto.User1DTO;
import dto.User2DTO;
import dto.User3DTO;
import dto.User4DTO;

public class User4DAO extends DBHelper{
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	public void insertUser4(User4DTO dto) {	
		
		try {
			logger.error("insert insertUser4()... ");
			
			conn = getConnection();
			psmt = conn.prepareStatement("INSERT INTO `user4`(`name`,`gender`,`age`,`addr`) VALUES(?, ?, ?, ?)");

			psmt.setString(1, dto.getName());
			psmt.setInt(2, dto.getGender());
			psmt.setInt(3, dto.getAge());
			psmt.setString(4, dto.getAddr());
			psmt.executeUpdate();
			
			close();
		} catch (Exception e) {
			logger.error("insert insertUser4()... Error" + e.getMessage());
		}
	}
	
	public User4DTO selectUser4(String seq) {
		
		
		User4DTO dto = new User4DTO();
		
		try {
			logger.error("insert selectUser4()... ");
			
			conn = getConnection();
			psmt = conn.prepareStatement("SELECT * FROM `user4` WHERE `seq` = ?");
			psmt.setString(1, seq);
			rs = psmt.executeQuery();
			
			if(rs.next()) { 
				dto.setSeq(rs.getInt(1));
				dto.setName(rs.getString(2));
				dto.setGender(rs.getInt(3));
				dto.setAge(rs.getInt(4));
				dto.setAddr(rs.getString(5));
			}
			close();
			
		} catch (Exception e) {
			logger.error("insert selectUser4()... Error" + e.getMessage());
		}
		
		
		return dto;	
	}
	
	public List<User4DTO> selectUser4s() {
		
		List<User4DTO> users = new ArrayList<>();
		
		try {
			
			logger.info("selectUser4s()...");

			conn = getConnection();
			psmt = conn.prepareStatement("SELECT * FROM `user4` ");
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				User4DTO dto = new User4DTO();
				dto.setSeq(rs.getInt(1));
				dto.setName(rs.getString(2));
				dto.setGender(rs.getInt(3));
				dto.setAge(rs.getInt(4));
				dto.setAddr(rs.getString(5));			
	
				users.add(dto);
			}
			
			close();
			
		}catch (Exception e) {
			logger.error("selectUser4s()...Error" + e.getMessage());
		}
		
		return users;
	}
	
	public void updateUser4(User4DTO dto) {

		try {
			logger.info("updateUser4()...");
			
			conn = getConnection();
			psmt = conn.prepareStatement("UPDATE `user4` SET `name` = ?, `gender` = ? `age` = ?, `addr` = ? WHERE `seq` = ?");
			psmt.setString(1, dto.getName());
			psmt.setInt(2, dto.getGender());
			psmt.setInt(3, dto.getAge());
			psmt.setString(4, dto.getAddr());
			psmt.setInt(5, dto.getSeq());
			
			psmt.executeQuery();
			
			close();
			
		} catch (Exception e) {
			logger.error("updateUser4s()...Error" + e.getMessage());
		}
		
	}
	
	public void deleteUser4(String seq) {
		
		try {
			logger.error("deleteUser4s()...");
			
			conn = getConnection();
			psmt = conn.prepareStatement("DELETE FROM `user4` WHERE seq = ?");
			psmt.setString(1, seq);
			psmt.executeUpdate();
			
			close();
			
		} catch (Exception e) {
			logger.error("deleteUser4s()...Error" + e.getMessage());
		}
	}
}
