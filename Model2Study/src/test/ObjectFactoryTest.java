package test;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import kr.or.kosta.shoppingmall.common.factory.XMLObjectFactory;
import kr.or.kosta.shoppingmall.user.dao.UserDao;
import kr.or.kosta.shoppingmall.user.service.UserService;
import kr.or.kosta.shoppingmall.user.service.UserServiceImpl;

/**
 * Annotation을 이용하여 Test 클래스를 정의하는 방법(JUnit 4)
 */
public class ObjectFactoryTest {
	
	//JUnit 4버전은 상속을 배제하고 annotation을 만들었음. 이름을 마음대로 써도 되는 버전	

	String mapperLocation = "C:/KOSTA187/workspace/Model2Study/WebContent/WEB-INF/object-mapper.xml";
	UserService userService;
	XMLObjectFactory factory;
	UserDao dao;

	@Before
	public void init() throws Exception { //이름 내 맘대로 정해도 됨. init으로 하든 setUp으로 하든
		// 선행 작업(자원할당 등)
		factory = new XMLObjectFactory(mapperLocation);
		userService = (UserService) factory.getBean(UserServiceImpl.class);
		 dao = (UserDao)factory.getBean("userDao");
	}

	@After
	public void destroy() throws Exception { //이름 내 맘대로 정해도 됨. destroy으로 하든 tearDown으로 하든
		// 후행 작업(자원해제 등)
	}

	@Test
	public void sumTest() {
		//Assert.assertEquals(20, calculator.sum(10, 10)); //assertEquals(expected, actual);
	}
	
	@Test
	public void getMessageTest() {
	}
	
	@Test
	public void testObjectFactory() {
	try {
		System.out.println(userService.list());
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

}
