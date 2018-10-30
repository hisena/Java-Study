package pattern3;

import java.sql.Connection;
import java.util.List;

/**
 * Dao 패턴 적용을 위한 인터페이스 선언
 * @author 류세은
 *
 */
public interface UserDao {

	public void create(User user) throws Exception;

	public User read(String id) throws Exception;

	public void update(User user) throws Exception;

	public void delete(String id) throws Exception;
	
	public List<User> listAll() throws Exception;
	
	public User certify(String id, String passwd) throws Exception;
	
	//public ??? employeeList() throws Exception; //사원번호, 이름, 급여, 부서이름, 도시명
	
	
}
