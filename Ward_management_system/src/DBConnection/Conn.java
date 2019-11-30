package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conn {
	static String driverClass = "oracle.jdbc.driver.OracleDriver";
	static String url="jdbc:oracle:thin:@localhost:1521:ORCL";
	static String user="SCOTT";
	static String password="123456";
	
	public static Connection getconn() {
		Connection conn=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection(url,user,password);
			System.out.println("连接成功");
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
