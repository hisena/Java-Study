package kr.or.kosta.shoppingmall.junit;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Annotation을 이용하여 Test 클래스를 정의하는 방법(JUnit 4)
 */
public class SomeServiceTest3 {
	
	//JUnit 4버전은 상속을 배제하고 anntation을 만들었음. 이름을 마음대로 써도 되는 버전	


	SomeServiceImpl service = new SomeServiceImpl();
		
	
	@Before
	public void init() throws Exception { //이름 내 맘대로 정해도 됨. init으로 하든 setUp으로 하든
		// 선행 작업(자원할당 등)
	}

	@After
	public void destroy() throws Exception { //이름 내 맘대로 정해도 됨. destroy으로 하든 tearDown으로 하든
		// 후행 작업(자원해제 등)
	}

	@Test
	public void sumTest() {
		//Assert.assertEquals(20, calculator.sum(10, 10)); //assertEquals(expected, actual);
		assertEquals(20, service.sum(10, 10)); //static import 활용
		System.out.println(service.sum(10, 10));
	}
	
	@Test
	public void getMessageTest() {
		assertNotNull(service.getMessage());
	}

}
