package rizni.citybookshop.statistics;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import rizni.citybookshop.reuseable.DBConnection;
import rizni.citybookshop.reuseable.MessageBox;

class statisticsDAO {
	Connection con = null;
	
	statisticsDAO() throws SQLException{
		con = new DBConnection().connect();
	}
	
	ObservableSet<Data> getBookDetail(String categotyName) {

		ObservableSet <Data> piechartdata = FXCollections.observableSet();

		try {
			
			String query;
			if(categotyName == null || categotyName == "All") {
				query="SELECT DISTINCT SUM(BStock) as s, bc.CName, BName, BStock"
						+ " FROM book AS b"
						+ " INNER JOIN book_category AS bc ON b.CID = bc.CID"
						+ " GROUP BY bc.CName";
			}else{
				query="SELECT BName, BStock"
						+ " FROM book AS b"
						+ " INNER JOIN book_category AS bc ON b.CID = bc.CID"
						+ " WHERE bc.CName='" + categotyName + "'";
			}

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			while ( rs.next() ) {

				if(categotyName == null || categotyName == "All") 
					piechartdata.add(new PieChart.Data(rs.getString("CName"), rs.getInt("s")));
				else
					piechartdata.add(new PieChart.Data(rs.getString("BName"), rs.getInt("BStock")));
			}

			rs.close();
			con.close();
			return piechartdata;

		} catch (SQLException e1) {
			new MessageBox().showDialog("Error", "Database", " Get book details : " + e1);
		}
		return null;
	}

	public Series<String, Number> getMonthlySale(String year) {
		Series<String, Number> lineChartData = new XYChart.Series<String, Number>();
		Map <Integer,String> monthList =new HashMap<>();
		for ( Month month : Month.values () ) {
			monthList.put(month.getValue(),month.toString());
		}
		try {
			
			String query;
			if(year == null) 
				query="SELECT IDate,Total,EXTRACT(MONTH FROM IDate) AS date FROM invoice AS b WHERE EXTRACT(YEAR FROM IDate)="+LocalDate.now().getYear();
			else if( year == "All")
				query="SELECT IDate,Total,EXTRACT(MONTH FROM IDate) AS date FROM invoice AS b";
			else
				query="SELECT IDate,Total,EXTRACT(MONTH FROM IDate) AS date FROM invoice AS b WHERE EXTRACT(YEAR FROM IDate)="+year;
				

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			while ( rs.next() ) {
				lineChartData.getData().add(new XYChart.Data<>(monthList.get(rs.getInt("date")) , rs.getDouble("Total")));
			}

			rs.close();
			con.close();
			return lineChartData;

		} catch (SQLException e1) {
			new MessageBox().showDialog("Error", "Database", " Get sale details : " + e1);
		}
		return null;
	}
	
	public ObservableList<String> getSaleYears() {
		ObservableList<String> ylist = FXCollections.observableArrayList();
		
		try {
			
			String query="SELECT DISTINCT EXTRACT(YEAR FROM IDate) AS year FROM invoice AS b";

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			while ( rs.next() ) {

				ylist.add(rs.getString("year"));

			}

			rs.close();
			con.close();
			return ylist;

		} catch (SQLException e1) {
			new MessageBox().showDialog("Error", "Database", " Get employee details : " + e1);
		}
		return ylist;

	}

	ObservableSet<Data> getDailySale(LocalDate day) {
		ObservableSet <Data> piechartdata = FXCollections.observableSet();

		try {
			String query;
			if(day == null) {
				query="SELECT DISTINCT bc.CName, SUM(c.IQuantity) as s, c.IDate"
						+ " FROM invoice AS c "
						+ " INNER JOIN book AS b ON b.BID = c.BID "
						+ " INNER JOIN book_category AS bc ON b.CID = bc.CID"
						+ " GROUP BY bc.CName";
			}else{
				query="SELECT bc.CName, SUM(IQuantity) as s, c.IDate"
						+ " FROM invoice AS c "
						+ " INNER JOIN book AS b ON b.BID = c.BID "
						+ " INNER JOIN book_category AS bc ON b.CID = bc.CID "
						+ " WHERE IDate='"+day+"'"
						+ " GROUP BY bc.CName ";
			}
			
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			while ( rs.next() ) {
					piechartdata.add(new PieChart.Data(rs.getString("CName"), rs.getInt("s")));
			}
			
			rs.close();
			con.close();
			return piechartdata;

		} catch (SQLException e1) {
			new MessageBox().showDialog("Error", "Database", " Get book details : " + e1);
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


}



