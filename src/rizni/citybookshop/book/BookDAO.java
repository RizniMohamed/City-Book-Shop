package rizni.citybookshop.book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import rizni.citybookshop.reuseable.Books;
import rizni.citybookshop.reuseable.DBConnection;
import rizni.citybookshop.reuseable.MessageBox;

class BookDAO {
	protected Connection con = null;

	BookDAO() throws SQLException{
		con = new DBConnection().connect();
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

	public ObservableList<Books> getBookDetails() {
		ObservableList<Books> bList = FXCollections.observableArrayList();

		try {
			
			String query="SELECT BID, BName, BUnitPrice, BStock, bc.CName"
					+ " FROM book AS b"
					+ " INNER JOIN book_category AS bc ON b.CID = bc.CID";

			Statement st = con.createStatement(); 
			ResultSet rs = st.executeQuery(query);

			while ( rs.next() ) {
				Books b = new Books();
				b.setBID(rs.getInt("BID"));
				b.setBName(rs.getString("BName"));
				b.setBStock(rs.getInt("BStock"));
				b.setBUnitPrice(rs.getDouble("BUnitPrice"));
				b.setCName(rs.getString("CName"));
				bList.add(b);
			}

			rs.close();
			con.close();

			return bList;

		} catch (SQLException e1) {
			new MessageBox().showDialog("Error", "Database", " Get employee details : " + e1);
		}
		return null;
	}

	public void createNewBook(String cbCategory, String txtStock, String txtID, String txtName, String txtUPrice) {
		try {
				

			//Create book category
			ResultSet rsBC =  con.createStatement().executeQuery("SELECT CName FROM `book_category` WHERE CName='"+ cbCategory + "'");;
			if(!rsBC.next()) {
				String queryC= "INSERT INTO `book_category` (`CName`) VALUES (?); ";
				PreparedStatement psC = con.prepareStatement(queryC);
				psC.setString(1, cbCategory);
				psC.executeUpdate();
			}

			

			String queryB="INSERT INTO `book` (`BID`, `BName`, `BUnitPrice`, `BStock`, `CID`) VALUES ( ?,?,?,?,? );  ";
			PreparedStatement psB = con.prepareStatement(queryB);
			psB.setInt(1, Integer.parseInt(txtID));
			psB.setString(2, txtName);
			psB.setDouble(3, Double.parseDouble(txtUPrice));
			psB.setInt(4, Integer.parseInt(txtStock));
			ResultSet rsB =  con.createStatement().executeQuery("SELECT CID FROM `book_category` WHERE CName='"+ cbCategory + "'"); rsB.next();
			psB.setInt(5,rsB.getInt("CID"));
			int affectedRowsE = psB.executeUpdate();

			if (affectedRowsE == 0) {
				new MessageBox().showDialog("Error", "Database" ,"Error on create new book");
			}else {
				new MessageBox().showDialog("Info", "Database" ,"New book created successfully");
			}

			con.close();
		} catch (SQLException e1) {
			new MessageBox().showDialog("Error", "Database", " Get employee details : " + e1);
			e1.printStackTrace();
		}

	}

	public void deleteBook(JFXTextField txtName) {
		try {
			String queryL= "DELETE FROM book WHERE BName='" + txtName.getText() + "'";
			Statement stL = con.createStatement();

			int affectedRowsL = stL.executeUpdate(queryL);

			if (affectedRowsL == 0) {
				new MessageBox().showDialog("Error", "Remember" ,"Error on deletion of book");
			}else {
				new MessageBox().showDialog("Info", "Database" ,"Book deleted successfully");
			}
			con.close();
		} catch (SQLException e1) {
			new MessageBox().showDialog("Error", "Database", " Delete book : " + e1);
			e1.printStackTrace();
		}
	}

	public void deleteBook(JFXComboBox<String> cbCategory) {
		try {
			String queryL= "DELETE FROM book_category WHERE CName='" + cbCategory.getValue() + "'";
			Statement stL = con.createStatement();

			int affectedRowsL = stL.executeUpdate(queryL);

			if (affectedRowsL == 0) {
				new MessageBox().showDialog("Error", "Remember" ,"Error on deletion of book category");
			}else {
				new MessageBox().showDialog("Info", "Database" ,"Book category deleted successfully");
			}

			con.close();
		} catch (SQLException e1) {
			new MessageBox().showDialog("Error", "Database", " Delete book Category : " + e1);
			e1.printStackTrace();
		}
	}

	public ObservableList<Books> searchBook(String cbCategory, String txtStock, String txtID, String txtName, String txtUPrice) {
		ObservableList<Books> eList = FXCollections.observableArrayList();
		try {
				

			String condition = "";

			if(!txtID.isEmpty()) 
				condition = " WHERE BID = "+ txtID + "";
			else if(!cbCategory.isEmpty())
				condition = " WHERE CName = '"+ cbCategory + "'";
			else if(!txtStock.isEmpty())
				condition = " WHERE EName = "+ txtStock + "";
			else if(!txtName.isEmpty())
				condition = " WHERE BName = '"+ txtName + "'";
			else if(!txtUPrice.isEmpty())
				condition = " WHERE BUnitPrice = "+ txtUPrice + "";
			else
				new MessageBox().showDialog("Error", "Account DAO", "Empty fields detected");


			String query="SELECT BID, BName, BUnitPrice, BStock, bc.CName"
					+ " FROM book AS b"
					+ " INNER JOIN book_category AS bc ON b.CID = bc.CID"
					+ condition;


			;
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			while ( rs.next() ) {
				Books b = new Books();
				b.setBID(rs.getInt("BID"));
				b.setBName(rs.getString("BName"));
				b.setBStock(rs.getInt("BStock"));
				b.setBUnitPrice(rs.getDouble("BUnitPrice"));
				b.setCName(rs.getString("CName"));
				eList.add(b);
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
	
	public void updateBook(String cbCategory, String txtStock, String txtID, String txtName, String txtUPrice) {
		try {
				

			ResultSet rscategoryID =  con.createStatement().executeQuery("SELECT CID FROM book_category WHERE CName='"+ cbCategory + "'"); rscategoryID.next();
			String query="UPDATE book SET `BID`=?,`BName`=?,`BUnitPrice`=?,`BStock`=?,`CID`=? WHERE BID = "+ txtID;


			PreparedStatement ps = con.prepareStatement(query);

			ps.setInt(1, Integer.parseInt(txtID));
			ps.setString(2, txtName);
			ps.setDouble(3, Double.parseDouble(txtUPrice));
			ps.setInt(4, Integer.parseInt(txtStock));
			ps.setInt(5, rscategoryID.getInt("CID"));

			int affectedRowsE = ps.executeUpdate();

			if (affectedRowsE == 0 ) {
				new MessageBox().showDialog("Error", "Remember" ,"Error on update book");
			}else {
				new MessageBox().showDialog("Info", "Database" ,"Book updated successfully");
			}

			con.close();
		} catch (SQLException e1) {
			new MessageBox().showDialog("Error", "Database", " update book : " + e1);
			e1.printStackTrace();
		}

	}

}
