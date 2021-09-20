package rizni.citybookshop.forgetpassword;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import rizni.citybookshop.reuseable.DBConnection;
import rizni.citybookshop.reuseable.Employee;

class ForgetPasswordDAO {

	Connection con = null;

	ForgetPasswordDAO() throws SQLException{
		con = new DBConnection().connect();
	}
	
	Employee getUsername(String eUsername) throws SQLException {
		

		Employee e = new Employee();
		e.setLUsername(eUsername);

		String query="SELECT LUsername FROM `login` WHERE LUsername= '"+ e.getLUsername() + "'";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);


		if ( rs.next() ) {
			e.setLUsername(rs.getString("LUsername"));
		}else {
			e.setLUsername("");
		}

		rs.close();
		con.close();

		return e;
	}
	
	Employee getMail(String eUsername) throws SQLException {
		

		Employee e = new Employee();
		e.setLUsername(eUsername);

		String query="SELECT EEmail FROM employee INNER JOIN login ON employee.LID=login.LID WHERE LUsername= '"+ e.getLUsername() + "'";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);


		if ( rs.next() ) {
			e.setEMail(rs.getString("EEmail"));
		}
		
		rs.close();
		con.close();

		return e;
	}
}
