package kr.co.jboard2.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.jboard2.db.DBHelper;
import kr.co.jboard2.db.SQL;
import kr.co.jboard2.dto.ArticleDTO;
import kr.co.jboard2.dto.FileDTO;

public class ArticleDAO extends DBHelper{
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	public int insertArticle(ArticleDTO dto) {
		
		int no = 0;
		
		try {
			conn = getConnection();
			conn.setAutoCommit(false);//transaction 시작(두개의 실행을 하나의 실행으로 묶는것 트랜잭션)
			
			stmt = conn.createStatement();
			psmt = conn.prepareStatement(SQL.INSERT_ARTICLE);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setInt(3, dto.getFile());
			psmt.setString(4, dto.getWriter());
			psmt.setString(5, dto.getRegip());
			
			psmt.executeUpdate(); //insert를 먼저하고 select를 해야함
			
			rs = stmt.executeQuery(SQL.SELECT_MAX_NO); //방금 insert된 글 번호
			conn.commit(); //작업확정
			
			if(rs.next()) {
				no = rs.getInt(1);
			}
			
			close();
			
		} catch (Exception e) {
			logger.error("insertArticle()...error " + e.getMessage());
		}
		return no;
	}
	
	public ArticleDTO selectArticle(String no) {
		ArticleDTO article = null;
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_ARTICLE);
			psmt.setString(1, no);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				article = new ArticleDTO();
				article.setNo(rs.getString(1));
				article.setParent(rs.getInt(2));
				article.setComment(rs.getInt(3));
				article.setCate(rs.getString(4));
				article.setTitle(rs.getString(5));
				article.setContent(rs.getString(6));
				article.setFile(rs.getInt(7));
				article.setHit(rs.getInt(8));
				article.setWriter(rs.getString(9));
				article.setRegip(rs.getString(10));
				article.setRdate(rs.getString(11));
				
				//파일정보
				FileDTO fileDto = new FileDTO();
				fileDto.setFno(rs.getInt(12));
				fileDto.setAno(rs.getInt(13));
				fileDto.setOfile(rs.getString(14));
				fileDto.setSfile(rs.getString(15));
				fileDto.setDownload(rs.getInt(16));
				fileDto.setRdate(rs.getString(17));
				
				article.setFileDto(fileDto);
				
				
			}
			close();
			
		} catch (Exception e) {
			logger.error("selectArticle error" + e.getMessage());
		}
		
		
		return article;
	}
	
	public List<ArticleDTO> selectArticles(int start, String search) {
		List<ArticleDTO> articles = new ArrayList<>();
		try {
			conn = getConnection();
			if(search == null) {
				psmt = conn.prepareStatement(SQL.SELECT_ARTICLES);
				psmt.setInt(1, start);
			}else {
				psmt = conn.prepareStatement(SQL.SELECT_ARTICLES_FOR_SEARCH);				
				psmt.setString(1, "%"+search+"%");
				psmt.setInt(2, start);
			}
			
			rs  = psmt.executeQuery();
			
			while(rs.next()) {
				ArticleDTO dto = new ArticleDTO();
				dto.setNo(rs.getInt(1));
				dto.setParent(rs.getInt(2));
				dto.setComment(rs.getInt(3));
				dto.setCate(rs.getString(4));
				dto.setTitle(rs.getString(5));
				dto.setContent(rs.getString(6));
				dto.setFile(rs.getInt(7));
				dto.setHit(rs.getInt(8));
				dto.setWriter(rs.getString(9));
				dto.setRegip(rs.getString(10));
				dto.setRdate(rs.getString(11));
				dto.setNick(rs.getString(12));
				articles.add(dto);
			}
			
			close();
			
		} catch (Exception e) {
			logger.error("selectArticles error" + e.getMessage());
		}
		
		
		return articles;
	}
	
	public void updateArticle(ArticleDTO dto) {
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.UPDATE_ARTICLE);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setInt(3, dto.getNo());
			psmt.executeUpdate();
			close();
			
		} catch (Exception e) {
			logger.error("updateArticle() " + e.getMessage());
		}
		
	}
	
	public void deleteArticle(String no) {
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.DELETE_ARTICLE);
			psmt.setString(1, no);
			psmt.setString(2, no);
			psmt.executeUpdate();
			close();
			
		} catch (Exception e) {
			logger.error("deleteArticle()" + e.getMessage());
		}
		
	}
	
	
	//추가 
	public int insertComment(ArticleDTO dto) {
		
		int result = 0;
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.INSERT_COMMENT);
			psmt.setInt(1, dto.getParent());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getWriter());
			psmt.setString(4, dto.getRegip());
			result = psmt.executeUpdate();
			
			close();
		} catch (Exception e) {
			logger.error("insertComment()... " + e.getMessage());
		}
		return result;
	}
	
	
	public List<ArticleDTO> selectComments(String parent) {
		List<ArticleDTO> comments = new ArrayList<>();
		
		try{
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_COMMENTS);
			psmt.setString(1, parent);
			
			rs = psmt.executeQuery();
			while(rs.next()) {
				ArticleDTO dto = new ArticleDTO();
				
				dto.setNo(rs.getInt(1));
				dto.setParent(rs.getInt(2));
				dto.setComment(rs.getInt(3));
				dto.setCate(rs.getString(4));
				dto.setTitle(rs.getString(5));
				dto.setContent(rs.getString(6));
				dto.setFile(rs.getInt(7));
				dto.setHit(rs.getInt(8));
				dto.setWriter(rs.getString(9));
				dto.setRegip(rs.getString(10));
				dto.setRdate(rs.getString(11));
				dto.setNick(rs.getString(12));
				
				comments.add(dto);
				
			}
			close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return comments;
	}
	
	
	
	// 전체 글 조회
	public int selectCountTotal(String search) {
		int total = 0;
		
		try {
			conn = getConnection();
			if(search == null) {
				psmt = conn.prepareStatement(SQL.SELECT_COUNT_TOTAL);
			}else {
				psmt = conn.prepareStatement(SQL.SELECT_COUNT_TOTAL_FOR_SEARCH);
				psmt.setString(1, "%"+search+"%");
			}
			
			rs = psmt.executeQuery();
			if(rs.next()) {
				total = rs.getInt(1);
			}
			close();
			
		} catch (Exception e) {
			logger.error("selectCountTotal() " + e.getMessage());
		}
		
		return total;
	}
	
	
	
	public int deleteComment(String no) {
		
		int result = 0;
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.DELETE_ARTICLE);
			psmt.setString(1, no);
			psmt.setString(2, no);
			result = psmt.executeUpdate();
			close();
			
		} catch (Exception e) {
			logger.error("deleteComment()..." + e.getMessage());
		}
		
		
		
		return result;
	}
	
	
	
	
	public void commentCountPlus(String no) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.UPDATE_ARTICLE_FOR_COMMENT_PLUS);
			psmt.setString(1, no);
			psmt.executeUpdate();
			close();
			
		} catch (Exception e) {
			logger.error("commentCountPlus() " + e.getMessage());
		}
	}
	
	public void commentCountMinus(String no) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.UPDATE_ARTICLE_FOR_COMMENT_MINUS);
			psmt.setString(1, no);
			psmt.executeUpdate();
			close();
			
		} catch (Exception e) {
			logger.error("commentCountMinus() " + e.getMessage());
		}
	}
	
	
	
	
}
