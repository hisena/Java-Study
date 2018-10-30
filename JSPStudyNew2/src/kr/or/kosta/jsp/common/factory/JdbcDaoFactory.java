package kr.or.kosta.jsp.common.factory;

import java.lang.reflect.Method;
import java.util.Hashtable;

import javax.sql.DataSource;

import kr.or.kosta.jsp.dao.UserDao;

public class JdbcDaoFactory extends DaoFactory {
	
	private Hashtable<String, Object> daos;
	
	private String[] daoNames = {"kr.or.kosta.jsp.dao.JdbcUserDao"};
	
	public JdbcDaoFactory() {
		daos = new Hashtable<String, Object>();
		
		for (String className : daoNames) {
			try {
				Object dao = Class.forName(className).newInstance();
				addDataSource(dao);
				daos.put(className, dao);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public UserDao getUserDao() {
		return (UserDao)daos.get("kr.or.kosta.jsp.dao.JdbcUserDao");
	}
	
	private void addDataSource(Object dao) {
		Class cls = dao.getClass();
		// 동적 메소드호출
		Method method;
		try {
			method = cls.getMethod("setDataSource", DataSource.class);
			method.invoke(dao, getDataSource());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	public BarDao getBarDao() {...};
//	public FooDao getFooDao() {...};

}
