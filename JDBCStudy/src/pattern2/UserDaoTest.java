package pattern2;

import java.sql.SQLException;

public class UserDaoTest {

	public static void main(String[] args) {

		UserDao dao = new OracleJdbcUserDao();
//		UserDao dao = new XXXXUserDao(); 기술 이름은 바뀔 수 있음
//		UserDao dao = new YYYYUserDao();
		
		User user = new User();
		
		String id = new String();
		String name = new String();
		String passwd = new String();
		String email = new String();
		
		user.setId("bangry2");
		user.setName("김기정2");
		user.setPasswd("111132");
		user.setEmail("bangry313@naver.com");
		
		
		try {
//			if(dao.read(user.getId())!=null) { //이게 좀 이상하게 나옴
				dao.create(user); 
//				System.out.println("회원가입 완료");	
////			} else {
//				System.out.println("이미 등록된 사용자입니다.");
////			}
			 
			dao.delete("bangry");
			System.out.println("삭제완료");
			
//			List<User> list = 
			
			
			User usr = dao.read("bangry");
			if(usr == null) {
				System.out.println("존재하지 않은 사용자 아이디입니다.");
			} else {
				System.out.println("조회된 사용자 정보입니다.");
				System.out.println("ID : " + user.getId() + ", " + "이름 : " + user.getName() + ", " + "패스워드 : " + user.getPasswd() + ", " + "이메일 : " + user.getEmail());
			}
			
			user = dao.certify("bangryyyy33", "1536784");
			System.out.println("확인 완료");
			
			System.out.println("사원목록");
//			List<Map<String, String>>
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println();
			SQLException ex = (SQLException)e;
			System.out.println(ex.getErrorCode());
		}
	}

}
