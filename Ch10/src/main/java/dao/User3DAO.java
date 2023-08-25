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

import dto.User1DTO;
import dto.User2DTO;
import dto.User3DTO;

public class User3DAO {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	public void insertUser3(User1DTO dto) {
		try {
			Context initCtx = new InitialContext();
			Context ctx = (Context)initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource) ctx.lookup("jdbc/userdb");
			Connection conn = ds.getConnection();
			PreparedStatement psmt = conn.prepareStatement("INSETR INTO VALUE(?, ?, ?, ? )");
			psmt.setString(1, dto.getUid());
			psmt.setString(2, dto.getName());
			psmt.setString(3, dto.getHp());
			psmt.setInt(4, dto.getAge());
			psmt.executeUpdate();
			
			psmt.close();
			conn.close();
			
		} catch (Exception e) {
			logger.error("insert insertUser3()... Error" + e.getMessage());
		}
	}
	
	public User3DTO selectUser3(String uid) {
		User3DTO dto = new User3DTO();
		try {
			Context initCtx = new InitialContext();
			Context ctx = (Context)initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource)ctx.lookup("jdbc/userdb");
			Connection conn = ds.getConnection();
			PreparedStatement psmt = conn.prepareStatement("SELECT * FROM `user3` WHERE `uid` = ? ");
			psmt.setString(1, uid);
			ResultSet rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto.setUid(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setHp(rs.getString(3));
				dto.setAge(rs.getInt(4));
			}
			
			
		} catch (Exception e) {
			logger.error("User3DAO selectUser3()...Error" + e.getMessage());
		}
		
		
		return dto;	
	}
	
	
	public List<User3DTO> selectUser3s() {
		List<User3DTO> users = new ArrayList<>();
		
		try {
			logger.info("selectUser3()...");
			
			Context initCtx = new InitialContext();
			Context ctx = (Context)initCtx.lookup("java:comp/env");
			DataSource ds =  (DataSource)ctx.lookup("jdbc/userdb");
			Connection conn = ds.getConnection();
			PreparedStatement psmt = conn.prepareStatement("SELECT * FROM user3");
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()) {
				User3DTO dto = new User3DTO();
				dto.setUid(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setHp(rs.getString(3));
				dto.setAge(rs.getInt(4));
				
				users.add(dto);
			}
			
		}catch (Exception e) {
			logger.error("selectUser3()...Error" + e.getMessage());
		}
		
		return users;
	}
	
	public void updateUser3(User3DTO dto) {
		
		try {
			logger.info("selectUser3()...");
			
			Context initCtx = new InitialContext();
			Context ctx = (Context)initCtx.lookup("java:comp/env");
			DataSource ds =  (DataSource)ctx.lookup("jdbc/userdb");
			Connection conn = ds.getConnection();
			PreparedStatement psmt = conn.prepareStatement("UPDATE `user3` SET `name` = ?, `hp` = ?, `age` = ? WHERE `uid`= ? ");
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getHp());
			psmt.setInt(3, dto.getAge());
			psmt.setString(4, dto.getUid());
			psmt.executeUpdate();
					
		} catch (Exception e) {
			logger.error("updateUser3()...Error" + e.getMessage());
		}
	}
	
	public void deleteUser3(String uid) {
		
		try {
			logger.info("deleteUser3()...");
			
			Context initCtx = new InitialContext();
			Context ctx = (Context)initCtx.lookup("java:comp/env");
			DataSource ds =  (DataSource)ctx.lookup("jdbc/userdb");
			Connection conn = ds.getConnection();
			PreparedStatement psmt = conn.prepareStatement("DELETE FROM `user3` WHERE `uid`= ? ");
			psmt.setString(1, uid);
			psmt.executeUpdate();
			
		} catch (Exception e) {
			logger.error("DeleteUser3()...Error" + e.getMessage());
		}
	}
}
