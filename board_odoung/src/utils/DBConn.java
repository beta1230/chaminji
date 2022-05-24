package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(
				"jdbc:oracle:thin:@np.moran.works:1521/xe", "ODOUNG", "1234");
		
//		conn.getAutoCommit(); // true
//		conn.setAutoCommit(false);
//		
//		conn.commit();
//		conn.rollback();
		return conn;
	}
	public static void main(String[] args) throws Exception{
		System.out.println(getConnection());
	}
}
