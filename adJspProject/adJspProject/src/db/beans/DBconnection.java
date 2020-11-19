package db.beans;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBconnection {
	
	public static Connection getConnection() throws Exception {
		
		System.out.println("DB연결시도");
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		String url = "jdbc:mysql://localhost:3306/t1?serverTimezone=UTC";
		String dbID = "root";
		String dbPasswd = "root";
		return DriverManager.getConnection(url, dbID, dbPasswd);
	}
	
	public static Connection getConnection(String ip, int port, String db, String user, String pw) throws Exception {
		System.out.println("DB연결시도");
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + db + "??serverTimezone=UTC", user, pw);
	}
}
