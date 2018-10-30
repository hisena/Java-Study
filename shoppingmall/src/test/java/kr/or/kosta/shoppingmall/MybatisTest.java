package kr.or.kosta.shoppingmall;

import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import kr.or.kosta.shoppingmall.employee.domain.Employee;

public class MybatisTest {

	private static final String NAMESPACE = "kr.or.kosta.shoppingmall.employee.";
	String resource = "mybatis-config.xml";
	SqlSessionFactory sqlSessionFactory;
	
	Logger logger = Logger.getLogger(MybatisTest.class);

	
	@Before
	public void setUp() {
		
		Reader reader = null;
		
		try {
			reader = Resources.getResourceAsReader(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader, "development");
		logger.debug("sqlSessionFactorty 생성 완료!");
	}
	
//	@Test 
		public void testMybatis() {
			SqlSession sqlSession = sqlSessionFactory.openSession();
			List<Employee> list = sqlSession.selectList(NAMESPACE + "selectAll2");
			for (Employee employee : list) {
				logger.debug(employee);
			}
			sqlSession.close(); //connection Pooling 반납하는 코드
		}
	
//	@Test 
	public void testSelectOne() {
		int num = 100;
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Employee employee = sqlSession.selectOne(NAMESPACE + "selectEmployeeById", num);
		logger.debug(employee);
		sqlSession.close(); 
	}

//	@Test 
	public void testSelectOne2() {
		int num = 100;
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int salary = sqlSession.selectOne(NAMESPACE + "selectSalaryById", num);
		logger.debug(salary);
		sqlSession.close();
	}

//	@Test
	public void testSelectList2() {
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("min", 3000);
		params.put("max", 4000);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<Employee> list = sqlSession.selectList(NAMESPACE + "selectEmployeesBySalary", params);
		for (Employee employee : list) {
			logger.debug(employee);
		}
		sqlSession.close();
	}
	
//	@Test
	public void testSelectListLike() {
		String name = "%E%";
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<Employee> list = sqlSession.selectList(NAMESPACE + "selectEmployeesByLastName", name);
		for (Employee employee : list) {
			logger.debug(employee);
		}
		sqlSession.close(); //connection Pooling 반납하는 코드
	}
	
//	@Test
	public void testSelectJoin() { //EmployeeMapper에 파라미터 없기 때문에 변수 선언해줄 것 없음
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<Map<String, Object>> list = sqlSession.selectList(NAMESPACE + "selectEmployeesWithDepartment");
		for (Map<String, Object> map : list) {
			BigDecimal id = (BigDecimal) map.get("id");
			String lastName = (String)map.get("lastName");
			logger.debug(id + ", " + lastName);
		}
		sqlSession.close(); //connection Pooling 반납하는 코드
	}
	
//	@Test
	public void testUpdate() {
		Employee emp = new Employee();
		emp.setId(100);
		emp.setSalary(7777);
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		sqlSession.update(NAMESPACE + "updateEmployee", emp);
//		sqlSession.commit(); 위에 오토커밋 안했으면 이렇게 직접 커밋해줘도 됨 
		logger.debug("업데이트 완료!");
		sqlSession.close(); //connection Pooling 반납하는 코드
	}
	
	@Test
	public void testDynamic() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("searchType", "id");
		map.put("searchValue", "100");
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<Employee> list = sqlSession.selectList(NAMESPACE + "dynamicSQL", map);
		for (Employee employee : list) {
			logger.debug(employee);
		}
		sqlSession.close(); //connection Pooling 반납하는 코드
	}
}