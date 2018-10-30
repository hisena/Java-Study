
import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.jdbc.OracleDriver;

/**
 * 동적 SQL 실행
 * 
 * @author 류세은
 *
 */
public class DynamicSQLExample {

	String driver = "oracle.jdbc.OracleDriver"; // 여기에 있는 이름만 바꿔주면 됨
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String username = "hr";
	String password = "hr";

	public void executeSQL(String sql) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver).newInstance();
			con = DriverManager.getConnection(url, username, password);
			pstmt = con.prepareStatement(sql);
			boolean existRS = pstmt.execute();
			if (existRS) {
				rs = pstmt.getResultSet();
				ResultSetMetaData rsm = rs.getMetaData();
				int columncount = rsm.getColumnCount();

				// 컬럼명 출력
				for (int i = 1; i <= columncount; i++) {
					String columnName = rsm.getColumnLabel(i);
					System.out.println(columnName + "\t");
				}
				// 컬럼값 출력
				while (rs.next()) {
					for (int i = 1; i <= columncount; i++) {
						String columnName = rsm.getColumnLabel(i);
						String columnValue = rs.getString(columnName);
						System.out.println(columnValue + "\t");
					}
					System.out.println();
				}

			} else {
				int count = pstmt.getUpdateCount();
				System.out.println(count + "행이 변경되었습니다..");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		DynamicSQLExample exam = new DynamicSQLExample();
		exam.executeSQL("select * from employees");
//
//		String sql = "create table my_table(somecolumn varchar2(20) not null)";
//
//		exam.executeSQL(sql);

	}

}
