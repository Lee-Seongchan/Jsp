package kr.co.farmstory2.service;

import java.util.List;

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
	
	public FileDTO selectFile(String fno) {
		return dao.selectFile(fno);
	}
	
	public void selectFiles() {
		
	}
	
	public void updateFile() {
		
	}
	
	public List<String> deleteFile(String ano) {
		return dao.deleteFile(ano);
	}
	
	
}
