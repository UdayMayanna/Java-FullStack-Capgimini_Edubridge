package myBankPro.packge;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

	private static Connection conn;
	
	 static Connection makeConnection() throws ClassNotFoundException, SQLException {
		if(conn == null)
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn =DriverManager.getConnection("jdbc:mysql://localhost:3306/uday","root","root");
			
		}
		return conn;
	}
	
}
