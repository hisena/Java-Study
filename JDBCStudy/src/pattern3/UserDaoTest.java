package pattern3;

import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

public class UserDaoTest {

	private static final String driver = "oracle.jdbc.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String username = "hr";
	private static final String password = "hr";

	
	public static void main(String[] args) {
		JdbcUserDao dao = new JdbcUserDao();
		
	BasicDataSource dataSource = new BasicDataSource();
	dataSource.setDriverClassName(driver);
	dataSource.setUrl(url);
	dataSource.setUsername(username);
	dataSource.setPassword(password);
	dataSource.setInitialSize(5);
	dataSource.setMaxTotal(10);
	dataSource.setMaxIdle(7);
		
	dao.setDataSource(dataSource);
	
	//내가 원하는 데이터베이스 테스트 추가
	try {
		System.out.println("**** 전체목록 테스트 ****");
//		List<User> list = dao.listAll();
	}
	}
}
	
