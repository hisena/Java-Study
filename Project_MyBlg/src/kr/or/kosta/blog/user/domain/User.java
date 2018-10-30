package kr.or.kosta.blog.user.domain;

/** 유저 클래스
 * @author 류세은
 *
 */
public class User {
	
	private String id;
	private String name;
	private String passwd;
	private String email;
	private String regdate;
	
	
	/**
	 * 유저 생성자
	 */
	public User() {
		super();
	}
	
	/**
	 * 유저 생성자
	 */
	public User(String id, String name, String passwd, String email, String regdate) {
		super();
		this.id = id;
		this.name = name;
		this.passwd = passwd;
		this.email = email;
		this.regdate = regdate;
	}
	
	/**
	 * getter/setter
	 */
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
		return "User [id=" + id + ", name=" + name + ", passwd=" + passwd + ", email=" + email + ", regdate=" + regdate
				+ "]";
	}
}
