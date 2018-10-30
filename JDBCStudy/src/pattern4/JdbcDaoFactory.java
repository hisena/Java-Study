package pattern4;

import java.lang.reflect.Method;

import javax.sql.DataSource;

public class JdbcDaoFactory extends DaoFactory {

	@Override
	public UserDao getUserDao() {
		UserDao dao = new JdbcUserDao(); //dao 객체 생성
		Class cls = dao.getClass(); //datasource 동적생성
		
		// 동적 메소드호출
		Method method;
		try {
			method = cls.getMethod("setDataSource", DataSource.class);
			method.invoke(dao, createDataSource()); //동적호출. 데이터소스 받아서 dao에 넣어준 것
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dao;
	}
	
//	public BarDao getBarDao() {...};
//	public FooDao getFooDao() {...};

}
