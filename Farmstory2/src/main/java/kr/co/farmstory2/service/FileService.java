package kr.co.farmstory2.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory2.dao.FileDAO;
import kr.co.farmstory2.dto.FileDTO;

public enum FileService {

	INSTANCE;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	FileDAO dao = new FileDAO();

	public void insertFile(FileDTO dto) {
		dao.insertFile(dto);
	}
	
	public void selectFile() {
		
	}
	
	public void selectFiles() {
		
	}
	
	public void updateFile() {
		
	}
	
	public void deleteFile() {
		
	}
	
	
}
