package test;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import kr.or.kosta.shoppingmall.common.factory.XMLObjectFactory;
import kr.or.kosta.shoppingmall.log4j.SomeService;
import kr.or.kosta.shoppingmall.user.dao.UserDao;
import kr.or.kosta.shoppingmall.user.service.UserService;
import kr.or.kosta.shoppingmall.user.service.UserServiceImpl;

/**
 * Annotation을 이용하여 Test 클래스를 정의하는 방법(JUnit 4)
 */
public class Log4jTest {
	
	//JUnit 4버전은 상속을 배제하고 annotation을 만들었음. 이름을 마음대로 써도 되는 버전	

	String mapperLocation = "C:/KOSTA187/workspace/Model2Study/WebContent/WEB-INF/object-mapper.xml";
	SomeService service = new SomeService();
	XMLObjectFactory factory;
	UserDao dao;


	@Test
	public void test4J() {
		service.someMethod();
	}
}
