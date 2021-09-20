package rizni.citybookshop.transaction;

import java.sql.Date;
import java.sql.SQLException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import rizni.citybookshop.account.Account;
import rizni.citybookshop.book.Book;
import rizni.citybookshop.dashboard.Dashboard;
import rizni.citybookshop.login.Login;
import rizni.citybookshop.reuseable.Invoice;
import rizni.citybookshop.reuseable.LoggedInDetails;
import rizni.citybookshop.reuseable.MessageBox;
import rizni.citybookshop.reuseable.TableUtils;
import rizni.citybookshop.reuseable.UserPicture;
import rizni.citybookshop.reuseable.WinTitleBar;
import rizni.citybookshop.statistics.Statistics;

public class transactionController { 

    
	@FXML
	private ImageView userPic;

	@FXML
	private JFXButton btnInvoice;

	@FXML
	private JFXButton btnTransaction;

	@FXML
	private JFXButton btnBooks;

	@FXML
	private JFXButton btnAccount;

	@FXML
	private JFXButton btnLogout;

	@FXML
	private JFXButton btnStatistics;

	@FXML
	private JFXTextField txtID;

	@FXML
	private JFXTextField txtName;

	@FXML
	private JFXTextField txtQuantity;

	@FXML
	private JFXTextField txtTotal;

	@FXML
	private JFXComboBox<String> cbCategory;

	@FXML
	private DatePicker datePicker;

	@FXML
	private JFXRadioButton rbSearch;

	@FXML
	private ToggleGroup Rrbtrans;

	@FXML
	private JFXRadioButton rbUpdate;

	@FXML
	private JFXRadioButton rbDelete;

	@FXML
	private JFXButton btnSubmit;

	@FXML
	private TableView<Invoice> TVInvoice;

	@FXML
	private TableColumn<Invoice,Integer> colID;

	@FXML
	private TableColumn<Invoice,String> colName;

	@FXML
	private TableColumn<Invoice,Integer> colQuantity;

	@FXML
	private TableColumn<Invoice,Double> colTotal;

	@FXML
	private TableColumn<Invoice,String> colCategory;
	
	@FXML
	private TableColumn<Invoice,String> colEmployee;

	@FXML
	private TableColumn<Invoice,Date> colDate;

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
	void btnLogout_Clicked(MouseEvent event) throws Exception {
		Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
		new Login().start(s);
	}

	@FXML
	void btnStatistics_Clicked(MouseEvent event) throws Exception {
		Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
		new Statistics().start(s);
	}

	@FXML
	void btnBooks_Clicked(MouseEvent event) throws Exception {
		Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
		new Book().start(s);
	}

	@FXML
	void btnDashboard_Clicked(MouseEvent event) throws Exception {
		Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
		new Dashboard().start(s);
	}
	
	@FXML
	void btnInvoice_Clicked(MouseEvent event) throws Exception {
		Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
		new rizni.citybookshop.invoice.Invoice().start(s);
	}

	@FXML
	void btnAccount_Clicked(MouseEvent event) throws Exception {
		Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
		new Account().start(s);
	}

	@FXML
	void getDataTable(MouseEvent event) {
		ObservableList<Invoice> b =  TVInvoice.getSelectionModel().getSelectedItems();
		txtID.setText(String.valueOf(b.get(0).getIID()));
		txtName.setText(b.get(0).getBName());
		txtQuantity.setText(String.valueOf(b.get(0).getIQuantity()));
		txtTotal.setText(String.valueOf(b.get(0).getTotal()));
		cbCategory.getSelectionModel().select(b.get(0).getCName());
		datePicker.setValue(b.get(0).getIDate().toLocalDate());
	}
	
	@FXML
	void btnSubmit_Clicked(MouseEvent event) throws SQLException {
		if (rbSearch.isSelected()) {
			TVInvoice.setItems( new transactionDAO().searchInvoice(cbCategory.getValue(),txtQuantity.getText(),txtID.getText(),txtName.getText(),txtTotal.getText(),datePicker.getValue()));
		}else if(new transactionValidation().verfy(cbCategory,txtQuantity,txtID,txtName,txtTotal,datePicker)) {
			if (rbDelete.isSelected()) {
				if (new MessageBox().ConfrimDialog("Confirm","Invoice Validation", "Are you sure do you want to remove this invoice? ").get() == ButtonType.OK)
					new transactionDAO().deleteInvoice(txtID);
			}else if (rbUpdate.isSelected()) {
				if (new MessageBox().ConfrimDialog("Confirm","Invoice Validation", "Are you sure do you want to update this invoice ").get() == ButtonType.OK)
					new transactionDAO().updateInvoice(cbCategory.getValue(),txtQuantity.getText(),txtID.getText(),txtName.getText(),txtTotal.getText(),datePicker.getValue());				
			}else {
				new MessageBox().showDialog("Error","Validation","Empty option detected : Select an option among delete, search and update.");
			}
		}
		initialize();
	}

	@FXML
	void initialize() throws SQLException {

		cbCategory.getItems().setAll(new transactionDAO().getBookCategory());
	
		//Set Table
		colID.setCellValueFactory(new PropertyValueFactory<Invoice, Integer>("IID"));
		colName.setCellValueFactory(new PropertyValueFactory<Invoice, String> ("BName"));
		colCategory.setCellValueFactory(new PropertyValueFactory<Invoice, String>("CName"));
		colDate.setCellValueFactory(new PropertyValueFactory<Invoice, Date>("IDate"));
		colQuantity.setCellValueFactory(new PropertyValueFactory<Invoice, Integer>("IQuantity"));
		colTotal.setCellValueFactory(new PropertyValueFactory<Invoice, Double>("Total"));
		colEmployee.setCellValueFactory(new PropertyValueFactory<Invoice, String>("Ename"));

		
		//Set user picture
		UserPicture.setPic(userPic);
		
		GUI_Access_Control();
		
		TableUtils.autoResizeColumns(TVInvoice);
		
	}

	void GUI_Access_Control() throws SQLException {

		if(LoggedInDetails.RoleName.contains("Cashier")) {
			rbDelete.setDisable(true);
			rbUpdate.setDisable(true);
		}
		if(!rbSearch.isSelected())
			TVInvoice.setItems(new transactionDAO().getInvoiceDetails());

	}
}


