package rizni.citybookshop.dashboard;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import rizni.citybookshop.account.Account;
import rizni.citybookshop.book.Book;
import rizni.citybookshop.invoice.Invoice;
import rizni.citybookshop.login.Login;
import rizni.citybookshop.reuseable.WinTitleBar;
import rizni.citybookshop.statistics.Statistics;
import rizni.citybookshop.transaction.Transaction;

public class dashboardController {

	//Objects
	WinTitleBar titlebar = new WinTitleBar();  

	@FXML
	private JFXButton btnInvoice;

	@FXML
	private JFXButton btnTransaction;

	@FXML
	private JFXButton btnBook;

	@FXML
	private JFXButton btnAccount;

	@FXML
	private JFXButton btnStatistics;

	@FXML
	private JFXButton btnLogout;

	@FXML
	void btnbook_Clicked(MouseEvent event) {
		Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
		new Book().start(s);
	}

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
	void btnLogout_Clicked(MouseEvent event) throws Exception {
		Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
		new Login().start(s);
	}

	@FXML
	void btnTransaction_Clicked(MouseEvent event) throws Exception {
		Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
		new Transaction().start(s);
	}

	@FXML
	void btnInvoice_Clicked(MouseEvent event) throws Exception {
		Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
		new Invoice().start(s);
	}

	@FXML
	void btnStatistics_Clicked(MouseEvent event) throws Exception {
		Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
		new Statistics().start(s);
	}

	@FXML
	void btnAccount_Clicked(MouseEvent event) {
		Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
		new Account().start(s);
	}

}


