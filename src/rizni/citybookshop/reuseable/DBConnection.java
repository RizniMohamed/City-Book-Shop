package rizni.citybookshop.reuseable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	public Connection connect() throws SQLException{
		String url = "jdbc:mysql://localhost:3306/city_bookshop";
		String user = "root";
		String password = "";
		return DriverManager.getConnection(url, user, password);
	} 

}
