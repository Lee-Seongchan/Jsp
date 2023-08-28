package kr.co.jboard2.service;

import kr.co.jboard2.dao.TermsDAO;
import kr.co.jboard2.dto.TermsDTO;

public class TermsService {

	private TermsDAO dao = new TermsDAO();
	
	public void insertTerms() {
		dao.insertTerms();
	}
	
	public TermsDTO selectTerms() {
		return dao.selectTerms();
	}
	
	public void updateTerms() {
		dao.updateTerms();
	}
	
	public void deleteTerms() {
		dao.deleteTerms();
	}
	
	
}
