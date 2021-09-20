package rizni.citybookshop.login;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import rizni.citybookshop.reuseable.DBConnection;
import rizni.citybookshop.reuseable.Employee;
import rizni.citybookshop.reuseable.MessageBox;

class LoginDAO {

	Connection con = null;

	LoginDAO() throws SQLException{
		con = new DBConnection().connect();
	}
	
	void makeRememberALLNone() {

		try {
			
			String queryGetOld="SELECT * FROM `login` WHERE LRemember=1";
			Statement stGetOld = con.createStatement();
			ResultSet rsGetOld = stGetOld.executeQuery(queryGetOld);


			if (rsGetOld.next()) {
				String query="UPDATE `login` SET LRemember=0 WHERE LID=" + rsGetOld.getInt(1);
				Statement st = con.createStatement();
				int affectedRows = st.executeUpdate(query);

				if (affectedRows == 0) {
					new MessageBox().showDialog("Error", "Remember" ,"Error on make all 0");
				}

				st.close();
			}

			rsGetOld.close();
			con.close();

		}catch(Exception e) {
			new MessageBox().showDialog("Error", "Database" ,"Unable to get database");
		}

	}

	void makeRemember(String username) {

		try {
			
			Employee e = new Employee();
			e.setLUsername(username);
			
			String query="UPDATE `login` SET LRemember=1 WHERE LUsername='" + e.getLUsername() + "'";
			
			Statement st = con.createStatement();
			int affectedRows = st.executeUpdate(query);

			if (affectedRows == 0) {
				new MessageBox().showDialog("Error", "Remember" ,"Error on make 1");
			}

			st.close();
			con.close();

		}catch(Exception e) {
			new MessageBox().showDialog("Error", "Database" ,"Unable to get database");
		}

	}

	Employee getUsernamePassword(String eUsername) throws SQLException {
		
		Employee e = new Employee();
		e.setLUsername(eUsername);
		
		String query="SELECT LUsername,LPassword FROM `login` WHERE LUsername= '"+ e.getLUsername() + "'";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);

		
		if ( rs.next() ) {
			e.setLPassword(rs.getString("LPassword"));
			e.setLUsername(rs.getString("LUsername"));
		}else {
			e.setLUsername("");
		}
		
		rs.close();
		con.close();

		return e;
	}
	

	Employee getRememberMe() throws SQLException {

		String query="SELECT * FROM `login` WHERE LRemember=1";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);


		Employee e = new Employee();
		if ( rs.next() ) {
			e.setLUsername(rs.getString("LUsername"));
			e.setLPassword( rs.getString("LPassword"));
		}

		rs.close();
		st.close();
		con.close();

		return e;
	}

}
