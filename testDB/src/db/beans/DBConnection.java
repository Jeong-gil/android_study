package db.beans;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	public static Connection getConnection() throws Exception {
		System.out.println("DB연결시도");
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String dbID = "madang";
		String dbPasswd = "madang";
		return DriverManager.getConnection(url, dbID, dbPasswd);
	}
	
	public static Connection getConnection(String ip, int port, String db, String user, String pw) throws Exception {
		System.out.println("DB연결시도");
		Class.forName("oracle.jdbc.driver.OracleDriver");
		return DriverManager.getConnection("jdbc:oracle:thin:@" + ip + ":" + port + ":" + db, user, pw);
	}
}
