package kr.or.kosta.jsp.board.dao;

import java.util.List;

import kr.or.kosta.jsp.board.domain.Board;


/**
 * Dao 패턴 적용을 위한 인터페이스 선언
 * @author 류세은
 *
 */
public interface BoardDao {
	
	public void create(Board board) throws Exception;
	
	public Board read(String boardId) throws Exception;
	
	public void update(Board board) throws Exception;

	public void delete(String userid) throws Exception;
	
	public List<Board> listAll() throws Exception;
	
	public Board certify(String id) throws Exception;
	
}
