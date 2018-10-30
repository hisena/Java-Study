
import java.security.interfaces.RSAKey;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import oracle.jdbc.OracleDriver;

/**
 * 동적 SQL 실행
 * 
 * @author 류세은
 *
 */
public class ProcedureCallExample {

	String driver = "oracle.jdbc.OracleDriver"; // 여기에 있는 이름만 바꿔주면 됨
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String username = "hr";
	String password = "hr";

	public void callProcedure() {

		Connection con = null;
		CallableStatement cstmt = null;

		String sql = "{call getEmployee(?, ?, ?, ?)}";
		try {
			Class.forName(driver).newInstance();
			con = DriverManager.getConnection(url, username, password);
			cstmt = con.prepareCall(sql);

			cstmt.setInt(1, 100);
			cstmt.registerOutParameter(2, Types.INTEGER);
			cstmt.registerOutParameter(3, Types.VARCHAR);
			cstmt.registerOutParameter(4, Types.INTEGER);

			cstmt.execute();

			int employeeId = cstmt.getInt(2);
			String firstName = cstmt.getString(3);
			int salary = cstmt.getInt(4);

			System.out.println(employeeId + "\t" + firstName + "\t" + salary);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (cstmt != null)
					cstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
			}
		}
	}

	public static void main(String[] args) {

		ProcedureCallExample exam = new ProcedureCallExample();
		
		exam.callProcedure(); //이거 이렇게 쓰는 게 맞는지?
		
//		exam.executeSQL("select * from employees");
//
//		String sql = "create table my_table(somecolumn varchar2(20) not null)";
//
//		exam.executeSQL(sql);

	}

}
