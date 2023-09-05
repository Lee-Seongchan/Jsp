package kr.co.farmstroy2.controller.board;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory2.dto.FileDTO;
import kr.co.farmstory2.service.ArticleService;
import kr.co.farmstory2.service.FileService;

@WebServlet("/board/fileDownload.do")
public class FileDownload extends HttpServlet{
	private static final long serialVersionUID = 6854628866959143216L;

	Logger logger = LoggerFactory.getLogger(this.getClass());
	FileService fService = FileService.INSTANCE;
	ArticleService aService = ArticleService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//데이터 수신
		String fno = request.getParameter("fno");
		logger.debug("fno = " + fno);
		
		
		//파일조회 
		FileDTO fileDto = fService.selectFile(fno);
		logger.debug(fileDto.toString());
		
        // response 파일 다운로드 헤더 수정(복붙)
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename="+URLEncoder.encode(fileDto.getOriName(), "utf-8"));
        response.setHeader("Content-Transfer-Encoding", "binary");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "private");
        
        //업로드 경로 구하기
        ServletContext ctx = request.getServletContext();
		String path = ctx.getRealPath("/upload");
        
        //response 파일 스트림 작업
        File file = new File(path + "/"+ fileDto.getNewName());

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
        }

        bos.close();
        bis.close();		
		
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
	
	
}
