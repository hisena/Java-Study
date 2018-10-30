
import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import oracle.jdbc.OracleDriver;

/**
 * @author 류세은
 *
 */
public class DMLExample {
	
	String driver = "oracle.jdbc.OracleDriver"; // 여기에 있는 이름만 바꿔주면 됨
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String username = "hr";
	String password = "hr";
	
	public static void create(String departmentName, int managerId, int locationId) {
		String mId = "NULL";
		String lId = "NULL";
		
		if(managerId != 0) {
			mId = managerId + "";
		}
		
		if(locationId != 0) {
			lId = locationId + "";
		}
		
		String sql = "INSERT INTO departments \r\n" + 
					 "            (department_id, \r\n" + 
					 "             department_name, \r\n" + 
					 "             manager_id, \r\n" + 
					 "             location_id) \r\n" + 
					 "VALUES     (departments_seq.NEXTVAL, \r\n" + 
					 "            '" + departmentName + "', \r\n" + 
					 "            " + mId + ", \r\n" + 
					 "            " + lId + ")";
		
		//이런 방식 잘 안 씀
	}
		
	public void create2(String departmentName, int managerId, int locationId) {
		create2(new Department(0, departmentName, managerId, locationId));
	}
	
	
		public void create2(Department department) {
			String sql = "INSERT INTO departments \r\n" + 
					 "            (department_id, \r\n" + 
					 "             department_name, \r\n" + 
					 "             manager_id, \r\n" + 
					 "             location_id) \r\n" + 
					 "VALUES     (departments_seq.NEXTVAL, \r\n" + 
					 "            ?, \r\n" + 
					 "            ?, \r\n" + 
					 "            ?)";
		
		
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver).newInstance();
			con = DriverManager.getConnection(url, username, password);
			con.setAutoCommit(false);
			
			// SQL 전처리
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, department.getDepartmentName()); // '코스타'
			
			if(department.getManagerId() != 0) {
				pstmt.setInt(2, department.getManagerId());
			} else {
				pstmt.setNull(2, Types.INTEGER);		
			}
			
			if(department.getLocationId() != 0) {
				pstmt.setInt(3, department.getLocationId());
			} else {
				pstmt.setNull(3, Types.INTEGER);		
			}
		
			
			int count = pstmt.executeUpdate();
			con.commit();
			System.out.println(count + "행이 추가되었습니다..");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch (Exception e) {}
			
			}
	
		}
	
	//부서번호 받아서 부서를 하나 삭제하는 메소드 만들기
	public void delete(int departmentId) {
		String sql = "Delete from departments\r\n" + 
					 "where department_id = ?";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver).newInstance();
			con = DriverManager.getConnection(url, username, password);
			con.setAutoCommit(false);
			
			// SQL 전처리
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, departmentId); // '코스타'
					
			int count = pstmt.executeUpdate();
			con.commit();
			System.out.println(count + "행이 삭제되었습니다..");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch (Exception e) {}
			
			}
	
		
	}
	
	public static void main(String[] args) {
		
	DMLExample exam = new DMLExample();
//	exam.create("코스타", 0, 0);	
//	exam.create2("코스타2", 100, 1700);	
	exam.create2("코스타3", 0 ,0);
	exam.delete(900);

	}
}
