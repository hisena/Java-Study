
import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.jdbc.OracleDriver;

/**
 * JDBC API를 이용한 DB 연동
 * @author 류세은
 */
public class JDBCExample {

	public static void main(String[] args) {
		String driver = "oracle.jdbc.OracleDriver"; // 여기에 있는 이름만 바꿔주면 됨
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "hr";
		String password = "hr";

		String sql = "select employee_id, last_name, salary\r\n" + "from employees";

		// #1.JDBC 드라이버 로딩(객체생성)
//	Driver driver = new OracleDriver();

		// Class 클래스를 이용한 동적 객체 생성
//		Class.forName(driver).newInstance();
		try {
			Class.forName(driver);
//			System.out.println("JDBC 드라이버 생성 완료...");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// newinstance 안 써줘도 똑같이 newinstance 호출
		// 그런데 왠만하면 new instance까지 써주기

		// #2. DBMS 연결
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {

			// JDBC : connection, statement, resultset
			con = DriverManager.getConnection(url, username, password);
//			System.out.println("DBMS 연결 완료.." + con);

			// #3. SQL 서버 전송 및 결과집합 수신
			stmt = con.createStatement();
//			System.out.println(stmt);
			rs = stmt.executeQuery(sql);
//			System.out.println(rs);

			// #4. ResultSet에서 데이터 인출
			while (rs.next()) {
//				rs.getString(1);
				String employeeId = rs.getString("employee_id");
				// 컬럼 이름으로 적어주는 게 찾아오는 데 있어서 조금 더 명확
				String lastName = rs.getString("last_name");
				int salary = rs.getInt("salary");
				System.out.println(employeeId + ", " + lastName + ", " + salary);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
			if (rs != null) rs.close();
			if (stmt != null) stmt.close();
			if (con != null) con.close();
				} catch (SQLException e) {}

		}

	}

}
