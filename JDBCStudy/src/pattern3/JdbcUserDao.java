package pattern3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

public class JdbcUserDao implements UserDao {

	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
		

	@Override
	public void create(User user) throws Exception { // 완료
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO users \r\n" + "VALUES     (?, \r\n" + "            ?, \r\n" + "            ?, \r\n"
				+ "            ?, \r\n" + "            SYSDATE)";
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getPasswd());
			pstmt.setString(4, user.getEmail());
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// 사용자Exception이 있을 때는 catch가 있어야 하고, 아니면 catch 없어도 됨
			// 위에 throw 최상위 Exception이 존재하기 때문
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
					if (con != null)
					con.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

	}

	@Override
	public User read(String id) throws Exception { // 완료
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		User user = null;

		String sql = "SELECT id, \r\n" + "       name, \r\n" + "       passwd, \r\n" + "       email, \r\n"
				+ "       To_char(regdate, 'YYYY\"년\" MM\"월\" DD\"일\" DAY') regdate \r\n" + "FROM   users \r\n"
				+ "WHERE  id = ?";

		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery(); // select문이니까 executeQuery해줘야 함
			if (rs.next()) {
				user = new User();
				user.setId(rs.getString("id"));
				user.setName(rs.getString("name"));
				user.setPasswd(rs.getString("passwd"));
				user.setEmail(rs.getString("email"));
				user.setRegdate(rs.getString("regdate"));
			}

		} catch (Exception e) {

			user = null;

		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
			}

		}

		return user;
	}

	@Override
	public void update(User user) throws Exception { // User user로 해야하는데 어떻게 할 수 있지?

		Connection con = null;
		PreparedStatement pstmt = null;

		String sql = "UPDATE users \r\n" + "SET    name = ?, \r\n" + "       passwd = ?, \r\n" + "       email = ? \r\n"
				+ "WHERE  id = ?";

		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getPasswd());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getId());
			pstmt.executeUpdate();

		} catch (Exception e) {

		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
			}

		}

	}

	@Override
	public void delete(String id) throws Exception { // 완료

		Connection con = null;
		PreparedStatement pstmt = null;

		String sql = "Delete from users\r\n" + "where id = ?";

		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			con.setAutoCommit(false); // 오토커밋을 해주는 경우와 안해주는 경우?

			// SQL 전처리
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);

			int count = pstmt.executeUpdate();
			con.commit();

			System.out.println(count + "행이 삭제되었습니다..");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {

			}

		}

	}

	@Override
	public List<User> listAll() throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		
		User user = null;

		String sql = "SELECT id, \r\n" + "       name, \r\n" + "       passwd, \r\n" + "       email, \r\n"
				+ "       To_char(regdate, 'YYYY\"년\" MM\"월\" DD\"일\" DAY') regdate \r\n" + "FROM   users \r\n";

		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery(); // select문이니까 executeQuery해줘야 함
			List list = new ArrayList<User>();
			while (rs.next()) {
				for (int i = 1; i <= ; i++) {
				}
			}
//			while
			
			if (rs.next()) {
				user = new User();
				user.setId(rs.getString("id"));
				user.setName(rs.getString("name"));
				user.setPasswd(rs.getString("passwd"));
				user.setEmail(rs.getString("email"));
				user.setRegdate(rs.getString("regdate"));
			}
//
//		} catch (Exception e) {
//
//			user = null;
//
//		} finally {
//			try {
//				if (stmt != null)
//					stmt.close();
//				if (rs != null)
//					rs.close();
//				if (con != null)
//					con.close();
//			} catch (Exception e) {
//			}
//
//		}
//
//		return user;
//	}
//		
//		Connection con = null;
////		User user;
////		Resultset
////		statement
//
////		
////		stmt = con.createStatement();
////		rs = stmt.executeQuery(sql);
//
////		String sql = "SELECT * \r\n" + 
////					 "FROM   users";
////		
////		try {
////			con = getConnection();
////			pstmt = con.prepareStatement(sql);
////
////			
////		} catch (Exception e) {
////			
////		} finally {
////			try {
////				if (pstmt != null)
////					pstmt.close();
////				if (con != null)
////					con.close();
////			} catch (Exception e) {
////			}
////			

		return null;
	}

	@Override
	public User certify(String id, String passwd) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		User user = null;

		String sql = "SELECT id, \r\n" + "       name, \r\n" + "       passwd, \r\n" + "       email, \r\n"
				+ "       To_char(regdate, 'YYYY\"년\" MM\"월\" DD\"일\" DAY') regdate \r\n" + "FROM   users \r\n"
				+ "WHERE  id = ? And passwd = ?";

		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, passwd);

			rs = pstmt.executeQuery(); // select문이니까 executeQuery해줘야 함
			if (rs.next()) {
				user = new User();
				user.setId(rs.getString("id"));
				user.setName(rs.getString("name"));
				user.setPasswd(rs.getString("passwd"));
				user.setEmail(rs.getString("email"));
				user.setRegdate(rs.getString("regdate"));
			} else {
				System.out.println("해당하는 유저의 아이디 혹은 비밀번호가 잘못되었습니다. 다시 입력해주십시오.");
			}

		} catch (Exception e) {

			user = null;

		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
			}

		}

		return user;
	}

	public List<Map<String, String>> employeeList() throws Exception { // 사원번호, 이름, 급여, 부서이름, 도시명
		List<Map<String, String>> list = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT          e.employee_id     eid, \r\n" + 
					 "                e.last_name       ename, \r\n" + 
					 "                e.salary          salary, \r\n" + 
					 "                d.department_name dname, \r\n" + 
					 "                l.city            city, \r\n" + 
					 "                e2.last_name      mname \r\n" + 
					 "left outer join departments d \r\n" + 
					 "ON              e.department_id = d.department_id \r\n" + 
					 "left outer join locations l \r\n" + 
					 "ON              d.location_id = l.location_id \r\n" + 
					 "left outer join employees e2 \r\n" + 
					 "ON              e.manager_id = e2.employee_id \r\n" + 
					 "ORDER BY        eid ASC";

		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery(); // select문이니까 executeQuery해줘야 함
			list = new ArrayList<Map<String, String>>();
			ResultSetMetaData rsd = rs.getMetaData();
			int columnCount = rsd.getColumnCount();
			while (rs.next()) {
				Map<String, String> row = new HashMap<String, String>();
				for (int i = 1; i <= columnCount; i++) {
					String columnName = rsd.getColumnLabel(i);
					String columnValue = rs.getString(i);
				}
			}

		} catch (Exception e) {

			list = null;

		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
			}

		}

		return list;
	}

	@Override
	public Connection getConnection() throws Exception {
		Class.forName(driver).newInstance();
		return DriverManager.getConnection(url, username, password);

	}

}
