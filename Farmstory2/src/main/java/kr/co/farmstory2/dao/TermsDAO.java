package kr.co.farmstory2.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory2.db.DBHelper;
import kr.co.farmstory2.db.SQL;
import kr.co.farmstory2.dto.TermsDTO;

public class TermsDAO extends DBHelper {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
		//약관
		public TermsDTO selectTerms() {
			
			TermsDTO dto = null;
			try {
				conn = getConnection();
				psmt = conn.prepareStatement(SQL.SELECT_TERMS);
				rs = psmt.executeQuery();
			
				if(rs.next()) {
					dto = new TermsDTO();
					dto.setTerms(rs.getString(1));
					dto.setPrivacy(rs.getString(2));
				}
				
			} catch (Exception e) {
				logger.error("selectTerms()" + e.getMessage());
			}
			
			return dto;
		}
	
	
}
