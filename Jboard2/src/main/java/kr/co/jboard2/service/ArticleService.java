package kr.co.jboard2.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.co.jboard2.dao.ArticleDAO;
import kr.co.jboard2.dto.ArticleDTO;
import kr.co.jboard2.dto.FileDTO;

public enum ArticleService {

	Instance;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	ArticleDAO dao = new ArticleDAO();
	
	public int insertArticle(ArticleDTO dto) {
		return dao.insertArticle(dto);
	}
	
	public ArticleDTO selectArticle(String no) {
		return dao.selectArticle(no);
	}
	
	public List<ArticleDTO> selectArticles(int start, String search) {
		return dao.selectArticles(start, search);
	}
	
	public void updateArticle(ArticleDTO dto) {
		dao.updateArticle(dto);
	}
	
	
	public void deleteArticle(String no) {
		dao.deleteArticle(no);
	}
	
	//전체 게시물 갯수
	public int selectCountTotal(String search) {
		return dao.selectCountTotal(search);
	}
	
	
	//페이지 마지막 번호
	public int getLastPageNum(int total) {
		return 0;
	}
	
	// 댓글 입력
	public int insertComment(ArticleDTO dto) {
		return dao.insertComment(dto);
	}
	
	
	// 댓글 출력
	public List<ArticleDTO> selectComments(String parent) {
		return dao.selectComments(parent);
	}
	
	
	public int deleteComment(String no) {
		return dao.deleteComment(no);
	}
	
	
	//댓글 카운터 플러스
	public void commentCountPlus(String no) {
		dao.commentCountPlus(no);
	}
	
	//댓글 카운터 마이너스
	public void commentCountMinus(String no) {
		dao.commentCountMinus(no);
	}
	
	
	
	
	
	//업로드 경로 구하기
	public String getFilePath(HttpServletRequest req) {
		// 파일 업로드 경로 구하기
		//ctx는 application 객체
		ServletContext ctx = req.getServletContext();
		String path = ctx.getRealPath("/upload");
		
		return path;
	}
	
	// 파일명 수정
	public String renameToFile(HttpServletRequest req, String oName) {
		String path = getFilePath(req);
		
		int i = oName.lastIndexOf(".");
		String ext = oName.substring(i);
		
		String uuid = UUID.randomUUID().toString();
		String sName = uuid + ext;
		
		File f1 = new File(path+"/"+oName);
		File f2 = new File(path+"/"+sName);
		
		// 파일명 수정
		f1.renameTo(f2);
		
		return sName;
	}
	
	
	
	//파일 업로드
	public MultipartRequest uploadFile(HttpServletRequest req) {
		MultipartRequest mr = null;
		
		//파일 경로 구하기
		String path = getFilePath(req);
		
		// 최대 업로드 파일 크기
		int maxSize = 1024 * 1024 * 10;
		
		// 파일 업로드
		try {						//5개 리퀘스트, 경로, 사이즈, 인코딩 값, 파일 이름 수정하는 객체
			mr = new MultipartRequest(req, path, maxSize,"UTF-8", new DefaultFileRenamePolicy());
									//생성자 업로드 로직을 수행함...(스트림 처리) 패킷단위로 실시간으로 수신 하는 것을 라이브러리를 통해 한 줄로 끝냄.
		} catch (IOException e) {
			logger.error("uploadFile() : " + e.getMessage());		
		}
								
		return mr;
	}
	
	//파일 다운로드
	public void downloadFile(HttpServletRequest request, HttpServletResponse response, FileDTO dto) throws IOException {
		
		
		
		// response 파일 다운로드 헤더 수정(복붙)
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename="+URLEncoder.encode(dto.getOfile(), "utf-8"));	
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "private");

		//response 파일 스트림 작업
		String path = getFilePath(request);
		File file = new File(path + "/"+ dto.getSfile());
		
		//스트림 생성(연결)
		//입력 스트림
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
		
		//출력 스트림
		BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream()); 
		
		while(true){
			
			//파일 읽기(byte 단위의 데이터 이기 때문에 int)
			int data = bis.read();
			if(data == -1){
				break;
			}
			//파일쓰기
			bos.write(data);
			
			
			bos.close();
			bis.close();
		}
		
	}
}
