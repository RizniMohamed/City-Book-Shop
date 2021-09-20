package rizni.citybookshop.reuseable;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoggedInDetails {
	public static String username;
	public static String RoleName;
	public static String picPath;
}

class LoggedInDetailsDAO extends LoggedInDetails  {

	public void getEmployeeDetails() {
		try {
			Connection con = new DBConnection().connect();
			String query="SELECT EPhoto, LUsername, RName"
					+ " FROM employee AS e"
					+ " INNER JOIN login AS l ON e.LID = l.LID"
					+ " INNER JOIN role AS r ON r.RID = l.RID"
					+ " WHERE LUsername= '"+ username + "'";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			if ( rs.next() ) {
				LoggedInDetails.RoleName = (rs.getString("RName"));
				if( (rs.getString("EPhoto")) == null)
					LoggedInDetails.picPath = "Profile.png";
				else
					LoggedInDetails.picPath = (rs.getString("EPhoto"));
			}

			rs.close();
			con.close();

		} catch (SQLException e1) {
			new MessageBox().showDialog("Error", "Database", " Get employee details : " + e1);
		}

	}
}
