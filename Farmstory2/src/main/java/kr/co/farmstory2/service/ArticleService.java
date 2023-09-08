package kr.co.farmstory2.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.co.farmstory2.dao.ArticleDAO;
import kr.co.farmstory2.db.SQL;
import kr.co.farmstory2.dto.ArticleDTO;

public enum ArticleService {

	INSTANCE;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	ArticleDAO dao = new ArticleDAO();
	
	public int insertArticle(ArticleDTO dto) {
		return dao.insertArticle(dto);
	}
	
	public ArticleDTO selectArticle(String no) {
		return dao.selectArticle(no);
	}
	
	public List<ArticleDTO> selectArticles(String cate, int start) {
		return dao.selectArticles(cate, start);
	}	
	
	public void updateArticle(ArticleDTO dto) {
		dao.updateArticle(dto);
	}
	
	public void deleteArticle(String no) {
		dao.deleteArticle(no);
	}
	
	
	//전체글 조회
	public int selectCountTotal(String cate) {
		return dao.selectCountTotal(cate);
	}
	
	public int insertComment(ArticleDTO dto) {
		return dao.insertComment(dto);
	}
	
	
	public List<ArticleDTO> selectComments(String parent) {
		return dao.selectComments(parent);
	}
	
	
	public int updateComment(String content, String no) {
		return dao.updateComment(content, no);
	}
	
	public int deleteComment(String no) {
		return dao.deleteComment(no);
	}
	
	//조회수 
	public void updateViewHit(String no) {
		dao.updateViewHit(no);
	}
	
	//최신글 
	public List<ArticleDTO> selectArticleLateste(String cate, int end) {
		return dao.selectArticleLateste(cate, end);
	}
	
	
	public String getPath(HttpServletRequest req, String dir) {
		// 파일 업로드 경로 구하기 
		ServletContext ctx = req.getServletContext();
		String path = ctx.getRealPath(dir);
		return path;
	}
	
	// 파일 업로드
		public MultipartRequest uploadFile(HttpServletRequest req, String path) {
			// 최대 업로드 파일 크기
			int maxSize = 1024 * 1024 * 10;
			
			// 파일 업로드 및 Multipart 객체 생성
			MultipartRequest mr = null;
			
			try {
				mr = new MultipartRequest(req, 
										  path, 
										  maxSize, 
										  "UTF-8", 
										  new DefaultFileRenamePolicy());
			} catch (IOException e) {
				logger.error("uploadFile : " + e.getMessage());
			}
			
			return mr;
		}
	
		
}
