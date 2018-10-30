package pattern;

import java.util.List;
import java.util.Map;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDaoTest {

	public static void main(String[] args) {

		UserDao dao = new JdbcUserDao();
//		UserDao dao = new XXXXUserDao(); 기술 이름은 바뀔 수 있음
//		UserDao dao = new YYYYUserDao();
		List<Map<String, String>> list;
		User user = new User();
		
		
		user.setId("bangry2");
		user.setName("김기정2");
		user.setPasswd("111132");
		user.setEmail("bangry313@naver.com");
		
		
		try {
			//유저 생성
			dao.create(user); 
				System.out.println("회원가입 완료");
				
			//유저 삭제
			dao.delete("bangry");
			System.out.println("삭제완료");
			
			//유저 조회
			User usr = dao.read("bangry");
			if(usr == null) {
				System.out.println("존재하지 않은 사용자 아이디입니다.");
			} else {
				System.out.println("====================조회된 유저 정보입니다.====================");
				System.out.println(user.toString());
			}

			//유저 확인
			user = dao.certify("bangry2", "2222");
			if(user == null) {
				System.out.println("해당하는 유저의 아이디 혹은 비밀번호가 잘못되었습니다. 다시 입력해주십시오.");
			} else {
				System.out.println(user.toString());
			}

			//유저 정보 수정
			user.setName("김기정정");
			user.setPasswd("235325");
			user.setEmail("bangry134@naver.com");
			user.setId("bangry2");
			
			System.out.println("수정완료 : " + user.toString());

			//employeeList 출력
			list = dao.employeeList();
			for (Map<String, String> map : list) {
				System.out.println(map);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println();
			SQLException ex = (SQLException)e;
			System.out.println(ex.getErrorCode());
		}
	}

}
