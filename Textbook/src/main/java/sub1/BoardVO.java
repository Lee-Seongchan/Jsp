package sub1;

import javax.xml.crypto.Data;

public class BoardVO {
	
	private int seq;
	private String title;
	private String writer;
	private String content;
	private Data regDate;
	private int cnt;

	public BoardVO(int seq, String title, String writer, String content, Data regDate, int cnt) {
		super();
		this.seq = seq;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.regDate = regDate;
		this.cnt = cnt;
	}
	
	public BoardVO() {
		
	}
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Data getRegDate() {
		return regDate;
	}
	public void setRegDate(Data regDate) {
		this.regDate = regDate;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
	@Override
	public String toString() {
		return "BoardVO [seq=" + seq + ", title=" + title + ", writer=" + writer + ", content =" + content +
				", regDate= " + regDate + ", cnt= " + cnt + "]";
	}
	
	
	
}
