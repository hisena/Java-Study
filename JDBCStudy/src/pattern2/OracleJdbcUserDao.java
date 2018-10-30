package pattern2;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

import org.apache.commons.dbcp2.BasicDataSource;

public class OracleJdbcUserDao extends JdbcUserDao {
	
	private static final String DRIVER = "oracle.jdbc.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "hr";
	private static final String PWD = "hr";
	
	@Override
	public Connection getConnection() throws Exception {
	// 커넥션 풀을 이용하여 connection 생성
		
//		Class.forName(DRIVER).newInstance();
//		return DriverManager.getConnection(URL, USER, PWD);
//		return UserConnectionPool.getInstance().getConnection();
		
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(DRIVER);
		dataSource.setUrl(URL);
		dataSource.setUsername(USER);
		dataSource.setPassword(PWD);
		dataSource.setInitialSize(5);
		dataSource.setMaxTotal(10); //내가 원하는 대로 설정 가능
		dataSource.setMaxIdle(7);
		return dataSource.getConnection();
	}

}
