package kr.co.farmstory2.dao;

import java.util.ArrayList;
import java.util.List;

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
		FileDTO dto = null;
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_FILE);
			psmt.setString(1, fno);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto = new FileDTO();
				dto.setFno(rs.getInt(1));
				dto.setAno(rs.getInt(2));
				dto.setOriName(rs.getString(3));
				dto.setNewName(rs.getString(4));
				dto.setDownload(rs.getInt(5));
				dto.setRdate(rs.getString(6));
			}
			
		} catch (Exception e) {
			logger.error("selectFile()" + e.getMessage() );
		}
		
		return dto;
		
	}
	
	public void selectFiles() {
		
	}
	
	public void updateFile() {
		
	}
	
	public List<String> deleteFile(String ano) {
		List<String> snames = new ArrayList<String>();
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_FILE_SNAMES); //파일 이름을 검색하기 위한 쿼리
			psmt.setString(1, ano);
			
			psmt1 = conn.prepareStatement(SQL.DELETE_FILE); // 파일 지우기
			psmt1.setString(1, ano);
			
			rs = psmt.executeQuery();
			psmt1.executeUpdate();
			
			while(rs.next()){
				snames.add(rs.getString(1));
			}
			
			close();
			
		} catch (Exception e) {
			logger.error("updateFile() " + e.getMessage());
		}
		return snames;
	}
	
	
	
}
