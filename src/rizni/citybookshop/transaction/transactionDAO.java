package rizni.citybookshop.transaction;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import rizni.citybookshop.reuseable.DBConnection;
import rizni.citybookshop.reuseable.Invoice;
import rizni.citybookshop.reuseable.LoggedInDetails;
import rizni.citybookshop.reuseable.MessageBox;

class transactionDAO{
	Connection con = null;
	
	transactionDAO() throws SQLException{
		con = new DBConnection().connect();
	}
	
	public ObservableList<Invoice> getInvoiceDetails() {
		ObservableList<Invoice> iList = FXCollections.observableArrayList();

		try {
			
			String query="SELECT IID, IDate, Total, IQuantity, bc.CName, b.BName, bc.CName, e.EName"
					+ " FROM invoice AS c "
					+ " INNER JOIN book AS b ON b.BID = c.BID "
					+ " INNER JOIN book_category AS bc ON b.CID = bc.CID"
					+ " INNER JOIN employee AS e ON e.EID = c.EID";

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			while ( rs.next() ) {
				Invoice i = new Invoice();
				i.setIID(rs.getInt("IID"));
				i.setIDate(rs.getDate("IDate"));
				i.setTotal(rs.getDouble("Total"));
				i.setIQuantity(rs.getInt("IQuantity"));
				i.setBName(rs.getString("BName"));
				i.setCName(rs.getString("CName"));
				i.setEname(rs.getString("EName"));
				iList.add(i);
			}

			rs.close();
			con.close();

			return iList;

		} catch (SQLException e1) {
			new MessageBox().showDialog("Error", "Database", " Get employee details : " + e1);
		}
		return null;
	}


	public ObservableList<String> getBookCategory() {
		ObservableList<String> Clist = FXCollections.observableArrayList();
		
		try {
			
			String query="SELECT DISTINCT bc.CName"
					+ " FROM book AS b"
					+ " INNER JOIN book_category AS bc ON b.CID = bc.CID"
					+ " GROUP BY bc.CName";

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			while ( rs.next() ) {

				Clist.add(rs.getString("CName"));
			}

			rs.close();
			con.close();
			return Clist;

		} catch (SQLException e1) {
			new MessageBox().showDialog("Error", "Database", " Get employee details : " + e1);
		}
		return Clist;
	}


	public ObservableList<Invoice> searchInvoice(String cbCategory, String txtQuantity, String txtID, String txtName, String txtTotal,
			LocalDate datePicker) {
		ObservableList<Invoice> Clist = FXCollections.observableArrayList();
		
		try {
				
			String condition = "";

			if(!txtID.isEmpty()) 
				condition = " WHERE IID = "+ txtID + "";
			else if(cbCategory != null)
				condition = " WHERE CName = '"+ cbCategory + "'";
			else if(!txtTotal.isEmpty())
				condition = " WHERE Total = "+ txtTotal + "";
			else if(!txtName.isEmpty())
				condition = " WHERE BName = '"+ txtName + "'";
			else if(!txtQuantity.isEmpty())
				condition = " WHERE IQuantity = "+ txtQuantity + "";
			else if(datePicker != null ) {
				System.out.println(datePicker);
				condition = " WHERE IDate = '"+ datePicker + "'";
			}else
				new MessageBox().showDialog("Error", "Account DAO", "Empty fields detected");


			String query="SELECT IID, IDate, Total, IQuantity, bc.CName, b.BName, bc.CName, e.EName"
					+ " FROM invoice AS c "
					+ " INNER JOIN book AS b ON b.BID = c.BID "
					+ " INNER JOIN book_category AS bc ON b.CID = bc.CID"
					+ " INNER JOIN employee AS e ON e.EID = c.EID"
					+ condition;

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			while ( rs.next() ) {
				Invoice b = new Invoice();
				b.setIID(rs.getInt("IID"));
				b.setIDate(rs.getDate("IDate"));
				b.setTotal(rs.getDouble("Total"));
				b.setIQuantity(rs.getInt("IQuantity"));
				b.setBName(rs.getString("BName"));
				b.setCName(rs.getString("CName"));
				b.setEname(rs.getString("EName"));
				Clist.add(b);
			}

			rs.close();
			con.close();
			return Clist;
		} catch (SQLException e1) {
			new MessageBox().showDialog("Error", "Database", " Get employee details : " + e1);
			e1.printStackTrace();
		}
		return null;
	}


	public void deleteInvoice(JFXTextField txtID) {
		try {
						
			String queryL= "DELETE FROM invoice WHERE IID="+ txtID.getText();
			Statement stL = con.createStatement();

			int affectedRowsL = stL.executeUpdate(queryL);

			if (affectedRowsL == 0) {
				new MessageBox().showDialog("Error", "Remember" ,"Error on deletion of invoice");
			}else {
				new MessageBox().showDialog("Info", "Database" ,"Invoice deleted successfully");
			}

			con.close();
		} catch (SQLException e1) {
			new MessageBox().showDialog("Error", "Database", " Delete Invoice : " + e1);
			e1.printStackTrace();
		}
		
	}


	public void updateInvoice(String cbCategory, String txtQuantity, String txtID, String txtName, String txtTotal,
			LocalDate datePicker) {
		try {	

			ResultSet rsBookID =  con.createStatement().executeQuery("SELECT BID FROM book WHERE BName='"+ txtName + "'"); rsBookID.next();
			ResultSet rsEmployeeID =  con.createStatement().executeQuery("SELECT EID FROM employee WHERE EName='"+ LoggedInDetails.username + "'"); rsEmployeeID.next();
			String query="UPDATE `invoice` SET `IID`=?,`IQuantity`=?,`IDate`=?,`Total`=?,`BID`=?,`EID`=? WHERE IID = "+ txtID;
			

			PreparedStatement ps = con.prepareStatement(query);

			ps.setInt(1, Integer.parseInt(txtID));
			ps.setInt(2, Integer.parseInt(txtQuantity));
			ps.setDate(3, Date.valueOf(datePicker));
			ps.setDouble(4, Double.parseDouble(txtTotal));
			
			ps.setInt(5, rsBookID.getInt("BID"));
			ps.setInt(6, rsEmployeeID.getInt("EID"));

			int affectedRowsE = ps.executeUpdate();

			if (affectedRowsE == 0 ) {
				new MessageBox().showDialog("Error", "Remember" ,"Error on update transation");
			}else {
				new MessageBox().showDialog("Info", "Remember" ,"Transation upadated successfully");
			}

			con.close();
		} catch (SQLException e1) {
			new MessageBox().showDialog("Error", "Database", " update book : " + e1);
			e1.printStackTrace();
		}
		
	}

}
