package rizni.citybookshop.resetpassword;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import rizni.citybookshop.reuseable.DBConnection;
import rizni.citybookshop.reuseable.Employee;
import rizni.citybookshop.reuseable.MessageBox;

class ResetPasswordDAO {
	
	Connection con = null;

	ResetPasswordDAO() throws SQLException{
		con = new DBConnection().connect();
	}
	
	void setNewPassword(String password,String username) {

		try {
			
			Employee e = new Employee();
			e.setLPassword(password);
			e.setLUsername(username);
			
			String query="UPDATE `login` SET LPassword='" + e.getLPassword() + "' WHERE LUsername='" + e.getLUsername() + "'";
			
			Statement st = con.createStatement();
			int affectedRows = st.executeUpdate(query);

			if (affectedRows == 0) {
				new MessageBox().showDialog("Error", "Remember" ,"Error on set new password");
			}

			st.close();
			con.close();

		}catch(Exception e) {
			new MessageBox().showDialog("Error", "Database" ,"Unable to get database");
		}

	}

}
