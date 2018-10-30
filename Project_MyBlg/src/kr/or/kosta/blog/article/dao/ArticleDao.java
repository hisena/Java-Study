package kr.or.kosta.blog.article.dao;

import java.util.List;

import kr.or.kosta.blog.article.domain.Article;
import kr.or.kosta.blog.page.Params;


/**
 * Dao 패턴 적용을 위한 인터페이스 선언
 * @author 류세은
 *
 */
public interface ArticleDao {
	
	public void create(Article article) throws Exception;
	
	public void createReply(Article article) throws Exception;
	
	public void createSubReply(Article article) throws Exception;
	
	public Article read(int articleId) throws Exception;

	public void update(Article article) throws Exception;

	public void delete(int articleId) throws Exception;
	
	public void uphitcount(int articleId) throws Exception;
	
	public List<Article> listAll() throws Exception;
	
	public List<Article> listByPage(Params params) throws Exception;
	
	public List<Article> listByPage(int page) throws Exception;
	
	public List<Article> listByPage(int page, int listSize) throws Exception;
	
	public List<Article> listByPage(int page, int listSize, String searchType, String searchValue) throws Exception;
	
	public int countBySearch(Params params) throws Exception;
	
	public int countBySearch(String searchType, String searchValue) throws Exception;
	
//	public Article certify(int articleId, String passwd) throws Exception;
	
}
