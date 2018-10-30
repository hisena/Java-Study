package kr.or.kosta.blog.article.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import kr.or.kosta.blog.article.domain.Article;
import kr.or.kosta.blog.page.Params;


/** 게시글 CRUD 및 페이징처리
 * @author 류세은
 *
 */
public class JdbcArticleDao implements ArticleDao {
	
	private DataSource dataSource;
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * 게시글 생성 메소드
	 */
	@Override
	public void create(Article article) throws Exception {
		Connection con =  null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO article \r\n" + 
					 "            (article_id, \r\n" + 
					 "             board_id, \r\n" + 
					 "             writer, \r\n" + 
					 "             subject, \r\n" + 
					 "             content, \r\n" + 
					 "             regdate, \r\n" + 
					 "             hitcount, \r\n" + 
					 "             ip, \r\n" + 
					 "             passwd, \r\n" + 
					 "             attach_file, \r\n" + 
					 "             group_no, \r\n" + 
					 "             level_no, \r\n" + 
					 "             order_no) \r\n" + 
					 "VALUES      (article_id_seq.NEXTVAL, \r\n" + 
					 "             1, \r\n" + 
					 "             ?, \r\n" + //writer
					 "             ?, \r\n" + //subject
					 "             ?, \r\n" + //content
					 "             SYSDATE, \r\n" + 
					 "             0, \r\n" + //hitcount
					 "             ?, \r\n" + //ip
					 "             ?, \r\n" + //passwd
					 "             NULL, \r\n" + //attach_file
					 "             article_id_seq.CURRVAL, \r\n" + 
					 "             ?, \r\n" + //level_no
					 "             ?) "; //order_no
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, article.getWriter());
			pstmt.setString(2, article.getSubject());
			pstmt.setString(3, article.getContent());
			pstmt.setString(4, article.getIp());
			pstmt.setString(5, article.getPasswd());
			pstmt.setInt(6, article.getLevelNo());
			pstmt.setInt(7, article.getOrderNo());
			pstmt.executeUpdate();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null)   con.close();
			}catch (Exception e) {}
		}
	}

	/**
	 * 답변글 생성 메소드
	 */
	@Override
	public void createReply(Article article) throws Exception {
		Connection con =  null;
		PreparedStatement pstmt = null;
		
		
		String sql = "INSERT INTO article \r\n" + 
					 "            (article_id, \r\n" + 
					 "             board_id, \r\n" + 
					 "             writer, \r\n" + 
					 "             subject, \r\n" + 
					 "             content, \r\n" +
					 "             regdate, \r\n" + 
					 "             hitcount, \r\n" + 
					 "             ip, \r\n" + 
					 "             passwd, \r\n" +
					 "             attach_file, \r\n" + 
					 "             group_no, \r\n" + 
					 "             level_no, \r\n" + 
					 "             order_no) \r\n" + 
					 "VALUES      (article_id_seq.nextval, \r\n" + 
					 "             1, \r\n" + 
					 "             ?, \r\n" + //writer 
					 "             ?, \r\n" + //subject
					 "             ?, \r\n" + //content
					 "             SYSDATE, \r\n" +
					 "             0, \r\n" + //hitcount
					 "             ?, \r\n" + //ip
					 "             ?, \r\n" + //passwd
					 "             NUll, \r\n" + //attach_file
					 "             ?, \r\n" + //group_no
					 "             1, \r\n" + 
					 "             (SELECT MAX(order_no) + 1 \r\n" + 
					 "              FROM   article \r\n" + 
					 "              WHERE  board_id = 1 \r\n" + 
					 "                     AND group_no = ?)) "; //group_no
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, article.getWriter());
			pstmt.setString(2, article.getSubject());
			pstmt.setString(3, article.getContent());
			pstmt.setString(4, article.getIp());
			pstmt.setString(5, article.getPasswd());
			pstmt.setInt(6, article.getGroupNo());
			pstmt.setInt(7, article.getGroupNo());
			pstmt.executeUpdate();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null)   con.close();
			}catch (Exception e) {}
		}
	}
	
	/**
	 * 답변의 답변글 생성
	 */
	@Override
	public void createSubReply(Article article) throws Exception {
		Connection con =  null;
		PreparedStatement pstmt = null;
		String orderSql = "UPDATE article \r\n" + 
					      "SET    order_no = order_no + 1 \r\n" + 
					      "WHERE  board_id = 1 \r\n" + 
					      "       AND group_no = ? \r\n" + 
					      "       AND order_no > (SELECT order_no \r\n" + 
					      "                       FROM   article \r\n" + 
					      "                       WHERE  article_id = ?)"; //parent article_id
		
		String createSql = "INSERT INTO article \r\n" + 
				 		   "            (article_id, \r\n" + 
				 		   "             board_id, \r\n" + 
				 		   "             writer, \r\n" + 
				 		   "             subject, \r\n" + 
				 		   "             content, \r\n" +
				 		   "             regdate, \r\n" + 
				 		   "             hitcount, \r\n" + 
				 		   "             ip, \r\n" + 
				 		   "             passwd, \r\n" +
				 		   "             attach_file, \r\n" + 
				 		   "             group_no, \r\n" + 
				 		   "             level_no, \r\n" + 
				 		   "             order_no) \r\n" + 
						   "VALUES      (article_id_seq.nextval, \r\n" + 
						   "             1,   \r\n" + 
						   "             ?, \r\n" + //writer 
						   "             ?, \r\n" + //subject
						   "             ?, \r\n" + //content
						   "             SYSDATE, \r\n" + 
						   "             0, \r\n" + 
						   "             ?, \r\n" + //ip
						   "             ?, \r\n" + //passwd
						   "             NULL, \r\n" + 
						   "             ?, \r\n" + //group_no
						   "             (SELECT level_no + 1 \r\n" + 
						   "              FROM   article \r\n" + 
						   "              WHERE  article_id = ?), \r\n" + //level_no
						   "             (SELECT order_no + 1 \r\n" + 
						   "              FROM   article \r\n" + 
						   "              WHERE  article_id = ?))" ; //parent article_id
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(orderSql);
			pstmt.setInt(1, article.getGroupNo());
			pstmt.setInt(2, article.getArticleId());
			pstmt.executeUpdate();

			pstmt = con.prepareStatement(createSql);
			pstmt.setString(1, article.getWriter());
			pstmt.setString(2, article.getSubject());
			pstmt.setString(3, article.getContent());
			pstmt.setString(4, article.getIp());
			pstmt.setString(5, article.getPasswd());
			pstmt.setInt(6, article.getGroupNo());
			pstmt.setInt(7, article.getArticleId());
			pstmt.setInt(8, article.getArticleId());
			pstmt.executeUpdate();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null)   con.close();
			}catch (Exception e) {}
		}
	}
	
	
	/**
	 * 게시글 상세보기를 위한 메소드
	 */
	 @Override
   public Article read(int articleId) throws Exception {
      Article article = null;
      
      Connection con =  null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      
      String sql = "SELECT article_id, \r\n" + 
      			   "       board_id, \r\n" + 
      			   "       writer, \r\n" + 
      			   "       subject, \r\n" + 
      			   "       content, \r\n" + 
      			   "       regdate, \r\n" + 
      			   "       hitcount, \r\n" + 
      			   "       ip, \r\n" + 
      			   "       passwd, \r\n" + 
      			   "       attach_file, \r\n" + 
      			   "       group_no, \r\n" + 
      			   "       level_no, \r\n" + 
      			   "       order_no \r\n" + 
      			   "FROM   article \r\n" + 
      			   "WHERE  board_id = 1 \r\n" + 
      			   "       AND article_id =? \r\n" + 
      			   "ORDER  BY group_no DESC, \r\n" + 
      			   "          order_no ASC";
      try {
         con = dataSource.getConnection();
         pstmt = con.prepareStatement(sql);
         pstmt.setInt(1, articleId);
         rs = pstmt.executeQuery();
         if(rs.next()) {
//            article = createArticle(rs);
        	article = new Article();
     		article.setArticleId(rs.getInt("article_id"));
     		article.setBoardId(rs.getInt("board_id"));
     		article.setWriter(rs.getString("writer"));
     		article.setSubject(rs.getString("subject"));
     		article.setContent(rs.getString("content"));
     		article.setRegdate(rs.getString("regdate"));
     		article.setHitcount(rs.getInt("hitcount"));
     		article.setIp(rs.getString("ip"));
     		article.setPasswd(rs.getString("passwd"));
     		article.setAttachFile(rs.getString("attach_file"));
     		article.setGroupNo(rs.getInt("group_no"));
     		article.setLevelNo(rs.getInt("level_no"));
     		article.setOrderNo(rs.getInt("order_no"));
         }
      }finally {
         try {
            if(rs != null)    rs.close();
            if(pstmt != null) pstmt.close();
            if(con != null)   con.close();
         }catch (Exception e) {}
      }
      return article;
   }

	 /**
	  * 게시글 수정을 위한 메소드
	  */
	 @Override
   public void update(Article article) throws Exception {
      Connection con =  null;
      PreparedStatement pstmt = null;
      String sql = "UPDATE article \r\n" + 
      			   "SET    subject = ?, \r\n" + 
      			   "       content = ? \r\n" + 
      			   "WHERE  article_id = ?";
      try {
         con = dataSource.getConnection();
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, article.getSubject());
         pstmt.setString(2, article.getContent());
         pstmt.setInt(3, article.getArticleId());
         pstmt.executeUpdate();
      }finally {
         try {
            if(pstmt != null) pstmt.close();
            if(con != null)   con.close();
         }catch (Exception e) {}
      }
   }

	 /**
	  * 게시글 삭제를 위한 메소드
	  */
	@Override
	public void delete(int articleId) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE article \r\n" + 
					 "SET    subject = '해당 게시글은 삭제되었습니다.' \r\n" + 
					 "WHERE  article_id = ?";

		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			con.setAutoCommit(false);
			pstmt.setInt(1, articleId);
			pstmt.executeUpdate();
			con.commit();
		} finally {
			try {
				if (pstmt != null)  pstmt.close();
				if (con != null)	con.close();
			} catch (Exception e) {}
		}
	}

	/**
	  * 게시물 조회수 증가 메소드
	  */
	@Override
	public void uphitcount(int articleId) throws Exception {
		 Connection con =  null;
	      PreparedStatement pstmt = null;
	      String sql = "UPDATE article \r\n" + 
	      			   "SET    hitcount = hitcount + 1 \r\n" + 
	      			   "WHERE  board_id = 1 \r\n" + 
	      			   "       AND article_id = ?";
	      try {
	         con = dataSource.getConnection();
	         pstmt = con.prepareStatement(sql);
	         pstmt.setInt(1, articleId);
	         pstmt.executeUpdate();
	      }finally {
	         try {
	            if(pstmt != null) pstmt.close();
	            if(con != null)   con.close();
	         }catch (Exception e) {}
	      }
	   }
	
	/**
	  * 게시판에 게시물 전체 출력하는 메소드
	  */
	@Override
	public List<Article> listAll() throws Exception {
		List<Article> list = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT article_id, \r\n" + 
					 "       board_id, \r\n" + 
					 "       writer, \r\n" + 
					 "       subject, \r\n" + 
					 "       content, \r\n" + 
					 "       regdate, \r\n" + 
					 "       hitcount, \r\n" + 
					 "       ip, \r\n" + 
					 "       passwd, \r\n" + 
					 "       attach_file, \r\n" + 
					 "       group_no, \r\n" + 
					 "       level_no, \r\n" + 
					 "       order_no \r\n" + 
					 "FROM   article \r\n" + 
					 "WHERE  board_id = 1 \r\n" + 
					 "ORDER  BY group_no DESC, \r\n" + 
					 "          order_no ASC";
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			list = new ArrayList<Article>();
			while(rs.next()) {
//				Article article = createArticle(rs);
				Article article = new Article();
				article.setArticleId(rs.getInt("article_id"));
				article.setBoardId(rs.getInt("board_id"));
				article.setWriter(rs.getString("writer"));
				article.setSubject(rs.getString("subject"));
				article.setContent(rs.getString("content"));
				article.setRegdate(rs.getString("regdate"));
				article.setHitcount(rs.getInt("hitcount"));
				article.setIp(rs.getString("ip"));
				article.setPasswd(rs.getString("passwd"));
				article.setAttachFile(rs.getString("attach_file"));
				article.setGroupNo(rs.getInt("group_no"));
				article.setLevelNo(rs.getInt("level_no"));
				article.setOrderNo(rs.getInt("order_no"));
				list.add(article);
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
	
	 @Override
	   public List<Article> listByPage(int page) throws Exception {
	      List<Article> list = null;
	      
	      Connection con = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      
	      String sql ="SELECT article_id,\r\n" + 
	      			  "       subject, \r\n" + 
	      			  "       writer, \r\n" + 
	      			  "       regdate, \r\n" + 
	      			  "       ip, \r\n" + 
	      			  "       hitcount \r\n" + 
	      			  "FROM   (SELECT CEIL(rownum / 10) request_page, \r\n" + 
	      			  "               article_id,\r\n" + 
	      			  "               subject, \r\n" + 
	      			  "               writer, \r\n" + 
	      			  "               TO_CHAR(regdate, 'YYYY-MM-DD HH24:MI') regdate, \r\n" + 
	      			  "               ip, \r\n" + 
	      			  "               hitcount \r\n" + 
	      			  "        FROM   (SELECT article_id,\r\n" + 
	      			  "                       subject, \r\n" + 
	      			  "                       writer, \r\n" + 
	      			  "                       regdate, \r\n" + 
	      			  "                       ip, \r\n" + 
	      			  "                       hitcount \r\n" + 
	      			  "                FROM   article \r\n" + 
	      			  "                WHERE  board_id = 1 \r\n" + 
	      			  "                ORDER  BY group_no DESC, \r\n" + 
	      			  "                          order_no ASC)) \r\n" + 
	      			  "WHERE  request_page = ?";
	      try {
	         con = dataSource.getConnection();
	         pstmt = con.prepareStatement(sql);
	         pstmt.setInt(1, page);
	         rs = pstmt.executeQuery();
	         list = new ArrayList<Article>();
	         while(rs.next()) {
//	            Article article = createArticle(rs);
	        	 Article article = new Article();
		     		article.setArticleId(rs.getInt("article_id"));
		     		article.setWriter(rs.getString("writer"));
		     		article.setSubject(rs.getString("subject"));
		     		article.setRegdate(rs.getString("regdate"));
		     		article.setHitcount(rs.getInt("hitcount"));
		     		article.setIp(rs.getString("ip"));
	            list.add(article);
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

	   @Override
	   public List<Article> listByPage(int page, int listSize) throws Exception {
	      List<Article> list = null;
	      
	      Connection con = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      
	      String sql ="SELECT article_id, \r\n" + 
	      			  "       subject, \r\n" + 
	      			  "       writer, \r\n" + 
	      			  "       regdate, \r\n" + 
	      			  "       ip, \r\n" + 
	      			  "       hitcount \r\n" + 
	      			  "FROM   (SELECT Ceil(ROWNUM / ?) request_page, \r\n" + 
	      			  "               article_id, \r\n" + 
	      			  "               subject, \r\n" + 
	      			  "               writer, \r\n" + 
	      			  "               To_char(regdate, 'YYYY-MM-DD HH24:MI') regdate, \r\n" + 
	      			  "               ip, \r\n" + 
	      			  "               hitcount \r\n" + 
	      			  "        FROM   (SELECT article_id, \r\n" + 
	      			  "                       subject, \r\n" + 
	      			  "                       writer, \r\n" + 
	      			  "                       regdate, \r\n" + 
	      			  "                       ip, \r\n" + 
	      			  "                       hitcount \r\n" + 
	      			  "                FROM   article \r\n" + 
	      			  "                WHERE  board_id = 1 \r\n" + 
	      			  "                ORDER  BY group_no DESC, \r\n" + 
	      			  "                          order_no ASC)) \r\n" + 
	      			  "WHERE  request_page = ?";
	      try {
	         con = dataSource.getConnection();
	         pstmt = con.prepareStatement(sql);
	         pstmt.setInt(1, listSize);
	         pstmt.setInt(2, page);
	         rs = pstmt.executeQuery();
	         list = new ArrayList<Article>();
	         while(rs.next()) {
//	            Article article = createArticle(rs);
	        	 Article article = new Article();
		     		article.setArticleId(rs.getInt("article_id"));
		     		article.setWriter(rs.getString("writer"));
		     		article.setSubject(rs.getString("subject"));
		     		article.setRegdate(rs.getString("regdate"));
		     		article.setHitcount(rs.getInt("hitcount"));
		     		article.setIp(rs.getString("ip"));
	            list.add(article);
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

	   @Override
	   public List<Article> listByPage(int page, int listSize, String searchType, String searchValue) throws Exception {
	      List<Article> list = null;
	      
	      Connection con = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      
	      String sql ="SELECT article_id,\r\n" + 
	      			  "       subject, \r\n" + 
	      			  "       writer, \r\n" + 
	      			  "       regdate, \r\n" + 
	      			  "       ip, \r\n" + 
	      			  "       hitcount \r\n" + 
	      			  "FROM   (SELECT CEIL(rownum / ?) request_page, \r\n" + 
	      			  "               article_id,\r\n" + 
	      			  "               subject, \r\n" + 
	      			  "               writer, \r\n" + 
	      			  "               TO_CHAR(regdate, 'YYYY-MM-DD HH24:MI') regdate, \r\n" + 
	      			  "               ip, \r\n" + 
	      			  "               hitcount \r\n" + 
	      			  "        FROM   (SELECT article_id,\r\n" + 
	      			  "                       subject, \r\n" + 
	      			  "                       writer, \r\n" + 
	      			  "                       regdate, \r\n" + 
	      			  "                       ip, \r\n" + 
	      			  "                       hitcount \r\n" + 
	      			  "                FROM   article \r\n" + 
	      			  "                WHERE  board_id = 1 \r\n"; 
	      
	      // 검색 유형별 WHERE 절 동적 추가
	      if(searchType != null){
	         switch (searchType) {
	            case "writer":
	               sql += "AND  writer = ? \r\n";
	               break;
	            case "subject":  
	               sql += "AND  subject LIKE ? \r\n";
	               searchValue = "%" + searchValue + "%";
	               break;
	            case "content":  
		           sql += "AND  content LIKE ? \r\n";
		           searchValue = "%" + searchValue + "%";
		           break;
	         }
	      }
	      sql += "				 ORDER  BY group_no DESC, \r\n" + 
	      		 "                          order_no ASC, \r\n" + 
	      		 "                          regdate DESC)) \r\n" + 
	      		 "WHERE  request_page = ?";
	      
	      try {
	         con = dataSource.getConnection();
	         pstmt = con.prepareStatement(sql);
	         pstmt.setInt(1, listSize);
	         
	         // 전체검색이 아닌경우
	         if(searchType != null){
	            pstmt.setString(2, searchValue);
	            pstmt.setInt(3, page);
	         }else{// 전체검색인 경우
	            pstmt.setInt(2, page);
	         }
	         
	         rs = pstmt.executeQuery();
	         list = new ArrayList<Article>();
	         while(rs.next()) {
//	            Article article = createArticle(rs);
	        	Article article = new Article();
	     		article.setArticleId(rs.getInt("article_id"));
	     		article.setWriter(rs.getString("writer"));
	     		article.setSubject(rs.getString("subject"));
	     		article.setRegdate(rs.getString("regdate"));
	     		article.setHitcount(rs.getInt("hitcount"));
	     		article.setIp(rs.getString("ip"));
	        	 list.add(article);
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

	   @Override
	   public List<Article> listByPage(Params params) throws Exception {
	      return listByPage(params.getPage(), params.getListSize(),  params.getSearchType(), params.getSearchValue());
	   }

	   @Override
	   public int countBySearch(String searchType, String searchValue) throws Exception {
	      int count = 0;
	      
	      Connection con = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      
	      String sql ="SELECT COUNT(article_id) count\r\n" + 
	               	  "FROM   article\r\n";
	      
	      // 검색 유형별 WHERE 절 동적 추가
	      if(searchType != null){
	    	  switch (searchType) {
	            case "writer":
	               sql += "WHERE  writer = ? \r\n";
	               break;
	            case "subject":  
	               sql += " WHERE  subject LIKE ? \r\n";
	               searchValue = "%" + searchValue + "%";
	               break;
	            case "content":  
		           sql += " WHERE  content LIKE ? \r\n";
		           searchValue = "%" + searchValue + "%";
		           break;
	         }
	      }
	      
	      try {
	         con = dataSource.getConnection();
	         pstmt = con.prepareStatement(sql);
	         
	         // 전체검색이 아닌경우
	         if(searchType != null){
	            pstmt.setString(1, searchValue);
	         }

	         rs = pstmt.executeQuery();
	         if (rs.next()) {
	            count = rs.getInt("count");
	         }
	      } finally {
	         try {
	            if(rs != null)    rs.close();
	            if(pstmt != null) pstmt.close();
	            if(con != null)   con.close();
	         }catch (Exception e) {}
	      }
	      return count;
	   }

	   @Override
	   public int countBySearch(Params params) throws Exception {
	      return countBySearch(params.getSearchType(), params.getSearchValue());
	   }
	
	   /*
	private Article createArticle(ResultSet rs) throws SQLException{
		Article article = new Article();
		article.setArticleId(rs.getInt("article_id"));
		article.setBoardId(rs.getInt("board_id"));
		article.setWriter(rs.getString("writer"));
		article.setSubject(rs.getString("subject"));
		article.setContent(rs.getString("content"));
		article.setRegdate(rs.getString("regdate"));
		article.setHitcount(rs.getInt("hitcount"));
		article.setIp(rs.getString("ip"));
		article.setPasswd(rs.getString("passwd"));
		article.setAttachFile(rs.getString("attach_file"));
		article.setGroupNo(rs.getInt("group_no"));
		article.setLevelNo(rs.getInt("level_no"));
		article.setOrderNo(rs.getInt("order_no"));
		return article;
	}
	*/
}

