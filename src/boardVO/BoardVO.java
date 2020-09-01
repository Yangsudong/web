package boardVO;

import java.sql.Date;

public class BoardVO {
	private Integer no;		
	private String poster;
	private String subject;
	private String contents; 
	private Date lastpost;
	private Integer views;
	private String filename;
	
	public BoardVO() {}
	
	public BoardVO(Integer no, String poster, String subject, String contents, Date lastpost, Integer views, String filename) {
		super();	
		this.no = no;
		this.poster = poster;
		this.subject = subject;
		this.contents = contents;
		this.lastpost = lastpost;
		this.views = views;
		this.filename = filename;
	}
	
	@Override
	public String toString() {
		return "BorderVO [no=" + no + ", poster=" + poster + ", subject=" + subject + ", contents=" + contents
				+ ", lastpost=" + lastpost + ", views=" + views + ", filename=" + filename + "]";
	}
	
	public Integer getNo() {
		return no;
	}
	public void setNo(Integer no) {
		this.no = no;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public Date getLastpost() {
		return lastpost;
	}
	public void setLastpost(Date lastpost) {
		this.lastpost = lastpost;
	}
	public Integer getViews() {
		return views;
	}
	public void setViews(Integer views) {
		this.views = views;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	
}
