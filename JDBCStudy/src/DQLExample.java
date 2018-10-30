
import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.jdbc.OracleDriver;

/**
 * @author 류세은
 *
 */
public class DQLExample {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		String driver = "oracle.jdbc.OracleDriver"; // 여기에 있는 이름만 바꿔주면 됨
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "hr";
		String password = "hr";

		String sql = "SELECT e.employee_id     id, \r\n" + 
					 "       e.last_name       ename, \r\n" + 
					 "       e.salary          salary, \r\n" + 
					 "       TO_CHAR(hire_date, 'YYYY-MM-DD HH24:MI:SS') hiredate, \r\n" + 
					 "       d.department_name dname \r\n" + 
					 "FROM   employees e \r\n" + 
					 "       join departments d \r\n" + 
					 "         ON e.department_id = d.department_id";

			Class.forName(driver).newInstance();

			//#2. DBMS 연결
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;


			con = DriverManager.getConnection(url, username, password);

			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				String id = rs.getString("id");
				String ename = rs.getString("ename");
				int salary = rs.getInt("salary");
				String hiredate = rs.getString("hiredate");
				String dname = rs.getString("dname");
				
				System.out.println(id + ", " + ename + ", " + salary + ", " + hiredate + ", " + dname);
			}
			
			rs.close();
			stmt.close();
			con.close();
	}

}
