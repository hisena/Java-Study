package kr.or.kosta.blog.guestbook.domain;

/** 
 * 방명록 클래스
 * @author 류세은
 */
public class Guestbook {
	
	private long guestbookId;
	private String userId;
	private String contents;
	private String regdate;
	
	
	/**
	 * 방명록 생성자
	 */
	public Guestbook() {
		super();
	}
	
	public Guestbook(long guestbookId, String userId, String contents, String regdate) {
		super();
		this.guestbookId = guestbookId;
		this.userId = userId;
		this.contents = contents;
		this.regdate = regdate;
	}

	/**
	 * getter/setter
	 */
	public long getGuestbookId() {
		return guestbookId;
	}

	public void setGuestbookId(long guestbookId) {
		this.guestbookId = guestbookId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	
	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getRegdate() {
		return regdate;
	}
	
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	/**
	 * toString
	 */
	@Override
	public String toString() {
		return "Guestbook [guestbookId=" + guestbookId + ", userId=" + userId + ", contents=" + contents + ", regdate="
				+ regdate + "]";
	}
	
}
