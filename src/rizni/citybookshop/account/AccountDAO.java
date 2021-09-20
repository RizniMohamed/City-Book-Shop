package rizni.citybookshop.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import rizni.citybookshop.reuseable.DBConnection;
import rizni.citybookshop.reuseable.Employee;
import rizni.citybookshop.reuseable.LoggedInDetails;
import rizni.citybookshop.reuseable.MessageBox;

 class AccountDAO {
	Connection con = null;
	
	AccountDAO() throws SQLException{
		con = new DBConnection().connect();
	}

	ObservableList<Employee> getEmployeeDetails() {
		ObservableList<Employee> eList = FXCollections.observableArrayList();

		String Conidtion_Query = (LoggedInDetails.RoleName.contains("Admin"))? 
				"" : " WHERE RName= '" + LoggedInDetails.RoleName + "'";
		try {
			String query="SELECT EID, EName, EEmail, EPhoto, LUsername, LPassword, RName"
					+ " FROM employee AS e"
					+ " INNER JOIN login AS l ON e.LID = l.LID"
					+ " INNER JOIN role AS r ON r.RID = l.RID"
					+ Conidtion_Query ;
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			while ( rs.next() ) {
				Employee e = new Employee();
				e.setLUsername(rs.getString("LUsername"));
				e.setEID(rs.getInt("EID"));
				e.setEMail(rs.getString("EEmail"));
				e.setEName(rs.getString("EName"));
				e.setLPassword(rs.getString("LPassword"));
				e.setLUsername(rs.getString("LUsername"));
				e.setPhoto(rs.getString("EPhoto"));
				e.setRname(rs.getString("RName"));
				eList.add(e);
			}

			rs.close();
			con.close();

			return eList;

		} catch (SQLException e1) {
			new MessageBox().showDialog("Error", "Database", " Get employee details : " + e1);
		}
		return null;


	}

	public void DeleteAccount(String username) {

		try {		

			String queryL= "DELETE FROM login WHERE LUsername ='" + username + "'";
			Statement stL = con.createStatement();

			int affectedRowsL = stL.executeUpdate(queryL);

			if (affectedRowsL == 0) {
				new MessageBox().showDialog("Error", "Remember" ,"Error on delete account");
			}else {
				new MessageBox().showDialog("Info", "Database" ,"Account deleted successfully");
			}

			con.close();
		} catch (SQLException e1) {
			new MessageBox().showDialog("Error", "Database", " Delete employee account : " + e1);
			e1.printStackTrace();
		}
	}

	public void createNewAccount(String accType, String email, String id, String name, String pass, String username, String picPath) {

		try {
			
			String queryE="INSERT INTO `employee` (`EID`, `EName`, `EEmail`, `EPhoto`, `LID`) VALUES ( ?,?,?,?,? );  ";
			String queryL= "INSERT INTO `login` (`LUsername`, `LPassword`, `LRemember`, `RID`) VALUES ( ?,?,?,?); ";

			PreparedStatement psE = con.prepareStatement(queryE);
			PreparedStatement psL = con.prepareStatement(queryL);

			psL.setString(1, username);
			psL.setString(2, pass);
			psL.setInt(3, 0);

			if ( accType.contains("Admin")) {
				psL.setInt(4, 1);
			}else {
				psL.setInt(4, 2);
			}

			int affectedRowsL = psL.executeUpdate();

			psE.setInt(1, Integer.parseInt(id));
			psE.setString(2, name);
			psE.setString(3, email);
			psE.setString(4, picPath);
			ResultSet rs =  con.createStatement().executeQuery("SELECT LID FROM login WHERE LUsername='"+ username + "'"); rs.next();
			psE.setInt(5,rs.getInt("LID"));
			int affectedRowsE = psE.executeUpdate();

			if (affectedRowsE == 0 || affectedRowsL == 0) {
				new MessageBox().showDialog("Error", null ,"Error on create new account");
			}else {
				new MessageBox().showDialog("Info", null ,"New account created successfully");
			}

			con.close();
		} catch (SQLException e1) {
			new MessageBox().showDialog("Error", "Database", " Get employee details : " + e1);
			e1.printStackTrace();
		}
	}

	public void updateAccount(String accType, String email, String id, String name, String pass, String username, String picPath) {
		try {
			
			String update_Role_Query = "";
			if ( accType.contains("Admin")) {
				update_Role_Query= " `RID`=1 ";
			}else {
				update_Role_Query= " `RID`=2 ";
			}

			ResultSet rs =  con.createStatement().executeQuery("SELECT LID FROM login WHERE LUsername='"+ username + "'"); rs.next();

			String queryE="UPDATE employee SET `EID`=?,`EName`=?,`EEmail`=?,`EPhoto`=?,`LID`=? WHERE LID = "+rs.getInt("LID")+";";
			String queryL= "UPDATE login SET `LPassword`=?, "+ update_Role_Query + " WHERE LUsername= '"+username+"'";

			PreparedStatement psE = con.prepareStatement(queryE);
			PreparedStatement psL = con.prepareStatement(queryL);

			psL.setString(1, pass);
			int affectedRowsL = psL.executeUpdate();

			psE.setInt(1, Integer.parseInt(id));
			psE.setString(2, name);
			psE.setString(3, email);
			psE.setString(4, picPath);
			psE.setInt(5,rs.getInt("LID"));
			int affectedRowsE = psE.executeUpdate();

			if (affectedRowsE == 0 || affectedRowsL == 0) {
				new MessageBox().showDialog("Error", "Remember" ,"Error on update account");
			}else {
				new MessageBox().showDialog("Info", null ,"Account updated successfully");
			}

			con.close();
		} catch (SQLException e1) {
			new MessageBox().showDialog("Error", "Database", " Get employee details : " + e1);
			e1.printStackTrace();
		}

	}

	public ObservableList<Employee> searchAccount(String accType, String email, String id, String name, String username) {
		ObservableList<Employee> eList = FXCollections.observableArrayList();
		try {	
			
			String RID = "", condition = "";
			if (accType != null) {
				if ( accType.contains("Admin")) {
					RID= " 2 ";
				}else {
					RID= " 1 ";
				}
			}
			
			if(!id.isEmpty()) 
				condition = " WHERE EID = "+ id + "";
			else if(!email.isEmpty())
				condition = " WHERE EEmail = '"+ email + "'";
			else if(!name.isEmpty())
				condition = " WHERE EName = '"+ name + "'";
			else if(!username.isEmpty())
				condition = " WHERE LUsername = '"+ username + "'";
			else if(!accType.isEmpty())
				condition = " WHERE l.RID = "+ RID + "";
			else
				new MessageBox().showDialog("Error", "Account DAO", "Empty fields detected");
				
			
			String query="SELECT EID, EName, EEmail, EPhoto, LUsername, LPassword, RName, l.RID"
					+ " FROM employee AS e"
					+ " INNER JOIN login AS l ON e.LID = l.LID"
					+ " INNER JOIN role AS r ON r.RID = l.RID"
					+ condition;
					
					
			;
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			while ( rs.next() ) {
				Employee e = new Employee();
				e.setLUsername(rs.getString("LUsername"));
				e.setEID(rs.getInt("EID"));
				e.setEMail(rs.getString("EEmail"));
				e.setEName(rs.getString("EName"));
				e.setLPassword(rs.getString("LPassword"));
				e.setLUsername(rs.getString("LUsername"));
				e.setPhoto(rs.getString("EPhoto"));
				e.setRname(rs.getString("RName"));
				eList.add(e);
			}

			rs.close();
			con.close();
			return eList;
		} catch (SQLException e1) {
			new MessageBox().showDialog("Error", "Database", " Get employee details : " + e1);
			e1.printStackTrace();
		}
		return null;


	}
}
