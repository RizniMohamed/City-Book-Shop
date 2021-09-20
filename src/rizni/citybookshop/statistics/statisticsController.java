package rizni.citybookshop.statistics;

import java.sql.SQLException;

import com.jfoenix.controls.JFXComboBox;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.DatePicker;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import rizni.citybookshop.account.Account;
import rizni.citybookshop.book.Book;
import rizni.citybookshop.dashboard.Dashboard;
import rizni.citybookshop.invoice.Invoice;
import rizni.citybookshop.login.Login;
import rizni.citybookshop.reuseable.UserPicture;
import rizni.citybookshop.reuseable.WinTitleBar;
import rizni.citybookshop.transaction.Transaction;

public class statisticsController { 


	@FXML
	private DatePicker dpDay;

	@FXML
	private LineChart<String, Number> lineMonth;

	@FXML
	private JFXComboBox<String> cbCategory;

	@FXML
	private JFXComboBox<String> cbYear;

	@FXML
	private ImageView userPic;

	@FXML
	private PieChart pieBooks;

	@FXML
	private PieChart pieDaySales;

	//Objects
	WinTitleBar titlebar = new WinTitleBar();

	@FXML
	void winBtnClick(MouseEvent event) {
		titlebar.winBtnClick(event);
	}	

	@FXML
	void drgStart(MouseEvent event) {
		titlebar.drgStart(event);
	}

	@FXML
	void drgEnd(MouseEvent event) {
		titlebar.drgEnd(event);
	}

	@FXML
	void WinTitleBar_Clicked(MouseEvent event) {
		titlebar.WinTitleBar_Clicked(event);
	}
	
	@FXML
	void btnTransaction_Clicked(MouseEvent event) throws Exception {
		Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
		new Transaction().start(s);
	}

	@FXML
	void btnDashboard_Clicked(MouseEvent event) throws Exception {
		Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
		new Dashboard().start(s);
	}
	
	@FXML
	void btnLogout_Clicked(MouseEvent event) throws Exception {
		Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
		new Login().start(s);
	}

	@FXML
	void btnBooks_Clicked(MouseEvent event) throws Exception {
		Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
		new Book().start(s);
	}

	@FXML
	void btnInvoice_Clicked(MouseEvent event) throws Exception {
		Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
		new Invoice().start(s);
	}

	@FXML
	void btnAccount_Clicked(MouseEvent event) throws Exception {
		Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
		new Account().start(s);
	}

	@FXML
	void lineMonth_Clicked(MouseEvent event) throws SQLException {
		new Charts().setLineMonthlySale(lineMonth,cbYear);
	}

	@FXML
	void pieYear_Clicked(MouseEvent event) throws SQLException {
		new Charts().setPieBooks(pieBooks,cbCategory);
	}

	@FXML
	void category_onAction(ActionEvent event) throws SQLException {
		new Charts().setPieBooks(pieBooks,cbCategory);
	}

	@FXML
	void year_onAction(ActionEvent event) throws SQLException {
		new Charts().setLineMonthlySale(lineMonth,cbYear);
	}

	@FXML
	void pieDay_Clicked(MouseEvent event) throws SQLException {
		new Charts().setPieDailySale(pieDaySales,dpDay);
	}

	@FXML
	void dpDay_onAction(ActionEvent event) throws SQLException {
		new Charts().setPieDailySale(pieDaySales,dpDay);
	}

	@FXML
	void initialize() throws SQLException {
		ObservableList<String> categoryList = new statisticsDAO().getBookCategory(); categoryList.add(0,"All");
		cbCategory.getItems().setAll(categoryList);

		ObservableList<String> yearList = new statisticsDAO().getSaleYears(); yearList.add(0,"All");
		cbYear.getItems().setAll(yearList);



		new Charts().setPieBooks(pieBooks,cbCategory);
		new Charts().setLineMonthlySale(lineMonth,cbYear);
		new Charts().setPieDailySale(pieDaySales,dpDay);

		UserPicture.setPic(userPic);

	}


}
