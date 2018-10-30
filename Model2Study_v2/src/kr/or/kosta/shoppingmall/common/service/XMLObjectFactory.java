package kr.or.kosta.shoppingmall.common.service;

import java.lang.reflect.Method;
import java.util.Hashtable;

import javax.sql.DataSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.sun.xml.internal.ws.util.StringUtils;

import kr.or.kosta.shoppingmall.common.dao.DaoFactory;
import kr.or.kosta.shoppingmall.user.service.UserService;
import kr.or.kosta.shoppingmall.user.service.UserServiceImpl;
import oracle.net.aso.b;

public class XMLObjectFactory extends DaoFactory {

	private Hashtable<String, Object> serviceList;
	private Hashtable<String, Object> daoList;

	public XMLObjectFactory(String objectMapperLocation) throws Exception {
		serviceList = new Hashtable<String, Object>();
		daoList = new Hashtable<String, Object>();
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringElementContentWhitespace(true);
		DocumentBuilder parser = factory.newDocumentBuilder();

		String xmlPath = objectMapperLocation;
		Document document = parser.parse(xmlPath);
		Element beanListElement = document.getDocumentElement();
		NodeList beanElements = beanListElement.getChildNodes();
		
		for (int i = 0; i < beanElements.getLength(); i++) {
			Element beanElement = (Element)beanElements.item(i);
			String type = beanElement.getAttribute("type");
			String name = beanElement.getAttribute("name");
			String value = beanElement.getAttribute("class");
			
			if (type.equals("dao")) {
				String daoName = name;
				Object daoObject = Class.forName(value).newInstance();
				addDataSource(daoObject);
				daoList.put(daoName, daoObject);
				System.out.println(daoName + "=" + daoObject);
			}
			
			if (type.equals("service")) {
				NodeList propertyElements = beanElement.getChildNodes();
				String ref = null;
				for (int j = 0; j < propertyElements.getLength(); j++) {
					Element propertyElement = (Element)propertyElements.item(j);
					ref = propertyElement.getAttribute("ref");
				}
				System.out.println(ref);
				Object serviceObject = Class.forName(value).newInstance();
				serviceList.put(value, serviceObject);
				
				String methodName = "set" + StringUtils.capitalize(ref);
				Class cls = serviceObject.getClass();
				Method method = null;
				try {
					String interfaceName = getDao(ref).getClass().getInterfaces()[0].getName();
					method = cls.getMethod(methodName,Class.forName(interfaceName));
					method.invoke(serviceObject, getDao(ref));
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println(value + "=" + serviceObject);
			}
		}
	}


	public Object getService(String serviceName) {
		return serviceList.get(serviceName);
	}

	public Object getService(Class cls) {
		return getService(cls.getName());
	}

	public Object getDao(String daoName) {
		return daoList.get(daoName);
	}

	public Object getDao(Class cls) {
		return getDao(cls.getName());
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
	
	private void addDao(Object service, String daoName, String methodName, Object dao) {
		Class cls = service.getClass();
		Method method;
		try {
			method = cls.getMethod(methodName, Class.forName(daoName));
			method.invoke(service, getDataSource());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		String mapperLocation = "C:/KOSTA187/workspace/Model2Study_v2/WebContent/WEB-INF/object-mapper.xml";
		XMLObjectFactory factory = new XMLObjectFactory(mapperLocation);
		UserService userService = (UserService) factory.getService(UserServiceImpl.class);
		System.out.println(userService.list());
	}
}
