package rizni.citybookshop.invoice;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import com.jfoenix.controls.JFXTextField;

import rizni.citybookshop.reuseable.DBConnection;
import rizni.citybookshop.reuseable.LoggedInDetails;
import rizni.citybookshop.reuseable.MessageBox;

class InvoiceDAO {
	Connection con = null;

	InvoiceDAO() throws SQLException{
		con = new DBConnection().connect();
	}

	public void searchBookDetails(JFXTextField txtName, JFXTextField txtUPrice, JFXTextField txtCategory) {
		try {
			
			String query="SELECT BID, BName, BUnitPrice, BStock, bc.CName"
					+ " FROM book AS b"
					+ " INNER JOIN book_category AS bc ON b.CID = bc.CID"
					+ " WHERE BName LIKE'"+txtName.getText() + "%'";

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			if ( rs.next() ) {
				txtName.setText(rs.getString("BName"));
				txtCategory.setText(rs.getString("bc.CName"));
				txtUPrice.setText(String.valueOf(rs.getDouble("BUnitPrice")));
			}else {
				new MessageBox().showDialog("Error", "Database", "Book not found");
			}

			rs.close();
			con.close();


		} catch (SQLException e1) {
			new MessageBox().showDialog("Error", "Database", " Get Book details : " + e1);
		}
		
	}

	boolean createNewInvoice(String txtID, String txtName, String txtQuantity,String txtUPrice, String txtCategory) {
		try {
				

			ResultSet rsCheck =  con.createStatement().executeQuery("SELECT BStock FROM `book` WHERE BName='"+ txtName + "'"); rsCheck.next();
			if (rsCheck.getInt("BStock") > Integer.parseInt(txtQuantity)) {
				
				String queryI="INSERT INTO `invoice` (`IID`, `IQuantity`, `IDate`, `Total` , `BID`, `EID`) VALUES ( ?,?,?,?,?,? );  ";
				
				PreparedStatement psI = con.prepareStatement(queryI);
				
				psI.setInt(1, Integer.parseInt(txtID));
				psI.setInt(2, Integer.parseInt(txtQuantity));
				psI.setDate(3, Date.valueOf(LocalDate.now()));
				psI.setDouble(4, Double.parseDouble(txtUPrice)*Integer.parseInt(txtQuantity));
				
				ResultSet rs1 =  con.createStatement().executeQuery("SELECT BID FROM `book` WHERE BName='"+ txtName + "'"); rs1.next();
				psI.setInt(5,rs1.getInt("BID"));
				
				ResultSet rs2 =  con.createStatement().executeQuery("SELECT EID FROM `employee` WHERE EName='"+ LoggedInDetails.username + "'"); rs2.next();
				psI.setInt(6,rs2.getInt("EID"));
				
				int affectedRowsE = psI.executeUpdate();
				
				if (affectedRowsE == 0) 
					new MessageBox().showDialog("Error", "Database" ,"Error on create new Invoice");
				else {
					new MessageBox().showDialog("Info", "Database" ,"Invoice created successfully");
					String query="UPDATE `Book` SET BStock="+ ( rsCheck.getInt("BStock") - Integer.parseInt(txtQuantity) )+" WHERE BName='"+ txtName + "'";
					con.createStatement().executeUpdate(query);
					return true;
				}
				
				
			}else {
				new MessageBox().showDialog("Info", "Database" ,"Out of stock: " + rsCheck.getInt("BStock") + " quantities available");
			}
			

			con.close();
		} catch (SQLException e1) {
			new MessageBox().showDialog("Error", "Database", " Get employee details : " + e1);
			e1.printStackTrace();
		}
		return false;
		
	}


}
