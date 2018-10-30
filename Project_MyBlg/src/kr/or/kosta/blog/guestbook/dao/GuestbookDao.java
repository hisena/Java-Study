package kr.or.kosta.blog.guestbook.dao;

import java.util.List;

import kr.or.kosta.blog.guestbook.domain.Guestbook;

/**
 * Dao 패턴 적용을 위한 인터페이스 선언
 * @author 류세은
 *
 */
public interface GuestbookDao {
	
	public void create(Guestbook guestbook) throws Exception;
	
	public List<Guestbook> listAll() throws Exception;
	
}
