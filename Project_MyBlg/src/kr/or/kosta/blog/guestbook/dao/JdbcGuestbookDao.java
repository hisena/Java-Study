package kr.or.kosta.blog.guestbook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import kr.or.kosta.blog.guestbook.domain.Guestbook;

/** 
 * 방명록 관리
 * @author 류세은
 */
public class JdbcGuestbookDao implements GuestbookDao {
	
	private DataSource dataSource;
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	/**
	 * 방명록 등록
	 */
	@Override
	public void create(Guestbook guestbook) throws Exception {
		Connection con =  null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO guestbook \r\n" + 
				     "VALUES     (GUESTBOOK_SEQ.NEXTVAL, \r\n" + 
				     "            ?, \r\n" + 
				     "            ?, \r\n" + 
				     "            SYSDATE)";
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, guestbook.getUserId());
			pstmt.setString(2, guestbook.getContents());
			pstmt.executeUpdate();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null)   con.close();
			}catch (Exception e) {}
		}
	}
	
	/**
	 * 방명록 목록 출력
	 */
	@Override
	public List<Guestbook> listAll() throws Exception {
		List<Guestbook> list = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT guestbook_id, \r\n" + 
					 "       user_id, \r\n" + 
					 "       CONTENTS, \r\n" + 
					 "       To_char(regdate, 'YYYY/MM/DD HH24:MI DAY') regdate \r\n" + 
					 "FROM   guestbook " +
					 "ORDER BY guestbook_id DESC";
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			list = new ArrayList<Guestbook>();
			while(rs.next()) {
				Guestbook guestbook = createGuestbook(rs);
				list.add(guestbook);
			}
		} finally {
			try {
				if(rs != null)    rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null)   con.close();
			}catch (Exception e) {}
		}
		return list;
	}
	
	/**
	 * 방명록 정보값 설정
	 */
	private Guestbook createGuestbook(ResultSet rs) throws SQLException{
		Guestbook guestbook = new Guestbook();
		guestbook.setGuestbookId(rs.getLong("guestbook_id"));
		guestbook.setUserId(rs.getString("user_id"));
		guestbook.setContents(rs.getString("contents"));
		guestbook.setRegdate(rs.getString("regdate"));
		return guestbook;
	}
	
	
}

