package kr.or.kosta.blog.article.dao;

import java.sql.SQLException;
import java.util.List;

import kr.or.kosta.blog.article.domain.Article;
import kr.or.kosta.blog.factory.DaoFactory;
import kr.or.kosta.blog.factory.JdbcDaoFactory;
import kr.or.kosta.blog.page.Params;

public interface ArticleDaoTest {
	
	public static void main(String[] args) {
		DaoFactory factory= new JdbcDaoFactory();
		ArticleDao dao = factory.getArticleDao();
		Article article = new Article();

/*
  System.out.println("**** 전체목록 테스트 ****");
  try {
//	    List<Article> list =  dao.listAll();
//      List<Article> list =  dao.listByPage(1);
      
//      List<Article> list =  dao.listByPage(1, 15);
//      List<Article> list =  dao.listByPage(1, 15, null, null);
//      List<Article> list =  dao.listByPage(1, 15, "name", "김");
//      List<Article> list =  dao.listByPage(new Params(1, 15, "name", "김"));
//      for (Article articleTest : list) {
//         System.out.println(articleTest);            
//      }
//      System.out.println("list 체크완료");
      
//      int count = dao.countBySearch(null);
      int count = dao.countBySearch("writer", "bangry");
      System.out.println("검색수: " + count);
      
} catch (Exception e) {
	  e.printStackTrace();
      System.out.println(e.getMessage());
      System.out.println();
      SQLException ex = (SQLException)e;
      System.out.println(ex.getErrorCode());
}
         
*/
		

		//article create 테스트 완료
		System.out.println("*****게시글 등록 테스트****");
		article.setWriter("bangry");
		article.setSubject("bangry가 작성한 게시글 제목입니다.");
		article.setContent("bangry가 작성한 게시글 내용입니다.");
		article.setHitcount(10);
		article.setIp("127.0.0.1");
		article.setPasswd("1111");
		article.setLevelNo(0);
		article.setOrderNo(0);
		try {
			dao.create(article);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(article);


/*
		  //article read 테스트
	      System.out.println("*****상세보기 테스트****");
	      try {
			article = dao.read(8);
		} catch (Exception e) {
			e.printStackTrace();
		}
	      System.out.println(article);

*/
		
/*		
			user = dao.certify("bangryyyy33", "1536784");
			System.out.println("확인 완료");
*/			
			
	      
/*			
	
*/

/*
 			//createReply 테스트
	      System.out.println("*****답변글 생성 테스트****");
	      int parentgroup = 1;
	      	article.setWriter("bangry");
			article.setSubject("bangry가 작성한 답변글입니다.");
			article.setContent("bangry가 작성한 답변글 내용입니다.");
			article.setIp("127.0.0.1");
			article.setPasswd("1111");
			article.setGroupNo(parentgroup);
	      System.out.println(article);
	      	try {
			dao.createReply(article);
			} catch (Exception e) {
				e.printStackTrace();
			}
*/
	}

}
