package kr.co.farmstory2.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory2.db.DBHelper;
import kr.co.farmstory2.db.SQL;
import kr.co.farmstory2.dto.FileDTO;

public class FileDAO extends DBHelper{

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void insertFile(FileDTO dto) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.INSERT_FILE);
			psmt.setInt(1, dto.getAno());
			psmt.setString(2, dto.getOriName());
			psmt.setString(3, dto.getNewName());
			psmt.executeUpdate();
			
			close();
			
		} catch (Exception e) {
			logger.error("insertFile()..." + e.getMessage());
		}
	}
	
	public FileDTO selectFile(String fno) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_FILE);
			psmt.setString(1, fno);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				
			}
			
		} catch (Exception e) {
			logger.error("selectFile()" + e.getMessage() );
		}
		
		return null;
		
	}
	
	public void selectFiles() {
		
	}
	
	public void updateFile() {
		
	}
	
	public void deleteFile() {
		
	}
	
	
	
}
