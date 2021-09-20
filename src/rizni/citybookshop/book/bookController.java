package rizni.citybookshop.book;

import java.sql.SQLException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import rizni.citybookshop.account.Account;
import rizni.citybookshop.dashboard.Dashboard;
import rizni.citybookshop.invoice.Invoice;
import rizni.citybookshop.login.Login;
import rizni.citybookshop.reuseable.Books;
import rizni.citybookshop.reuseable.LoggedInDetails;
import rizni.citybookshop.reuseable.MessageBox;
import rizni.citybookshop.reuseable.TableUtils;
import rizni.citybookshop.reuseable.UserPicture;
import rizni.citybookshop.reuseable.WinTitleBar;
import rizni.citybookshop.statistics.Statistics;
import rizni.citybookshop.transaction.Transaction;

public class bookController {


	//Objects
	WinTitleBar titlebar = new WinTitleBar();  

	@FXML
	private JFXButton btnBooks;

	@FXML
	private JFXRadioButton rbDeleteCategory;

	@FXML
	private JFXButton btnLogout;

	@FXML
	private JFXTextField txtID;

	@FXML
	private JFXTextField txtName;

	@FXML
	private JFXComboBox<String> cbCategory;

	@FXML
	private JFXTextField txtUPrice;

	@FXML
	private JFXTextField txtStock;

	@FXML
	private JFXRadioButton rbCreate;

	@FXML
	private ToggleGroup Rrbtrans;

	@FXML
	private JFXRadioButton rbUpdate;

	@FXML
	private JFXRadioButton rbDelete;

	@FXML
	private JFXRadioButton rbSearch;

	@FXML
	private TableColumn<Books, Integer> colID;

	@FXML
	private TableColumn<Books, String> colName;

	@FXML
	private TableColumn<Books, String> colCategory;

	@FXML
	private TableColumn<Books, Double> colUPrice;

	@FXML
	private TableColumn<Books, Integer> colStock;

	@FXML
	private ImageView userPic;

	@FXML
	private TableView<Books> TVBook;

	@FXML
	void btnSubmit_Clicked(MouseEvent event) throws SQLException {
		if (rbSearch.isSelected()) {
			TVBook.setItems( new BookDAO().searchBook(cbCategory.getValue(),txtStock.getText(),txtID.getText(),txtName.getText(),txtUPrice.getText()));
		}else if(new BookValidation().verfy(cbCategory,txtStock,txtID,txtName,txtUPrice)) {
			if (rbCreate.isSelected()) { 
				new BookDAO().createNewBook(cbCategory.getValue(),txtStock.getText(),txtID.getText(),txtName.getText(),txtUPrice.getText());
			}else if (rbDelete.isSelected()) {
				if (new MessageBox().ConfrimDialog("Confirm","Account Validation", "Are you sure do you want to remove this book? ").get() == ButtonType.OK)
					new BookDAO().deleteBook(txtName);
			}else if (rbDeleteCategory.isSelected()) {
				if (new MessageBox().ConfrimDialog("Confirm","Account Validation", "Are you sure do you want to remove this category? all books will be deleted. cannot be undone. ").get() == ButtonType.OK)
					new BookDAO().deleteBook(cbCategory);				
			}else if (rbUpdate.isSelected()) {
				if (new MessageBox().ConfrimDialog("Confirm","Account Validation", "Are you sure do you want to update this account ").get() == ButtonType.OK)
					new BookDAO().updateBook(cbCategory.getValue(),txtStock.getText(),txtID.getText(),txtName.getText(),txtUPrice.getText());				
			}else {
				new MessageBox().showDialog("Error","Validation","Empty option detected : Select an option among create, delete, search and update.");
			}
		}
		initialize();
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
	void btnDashboard_Clicked(MouseEvent event) throws Exception {
		Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
		new Dashboard().start(s);
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

	@FXML
	void Category_MouseExited(MouseEvent event) {
		set_catogoryPromtText();
	}

	@FXML
	void getDataTable(MouseEvent event) {
		ObservableList<Books> b =  TVBook.getSelectionModel().getSelectedItems();
		txtID.setText(String.valueOf(b.get(0).getBID()));
		txtName.setText(b.get(0).getBName());
		txtStock.setText(String.valueOf(b.get(0).getBStock()));
		txtUPrice.setText(String.valueOf(b.get(0).getBUnitPrice()));
		cbCategory.getSelectionModel().select(b.get(0).getCName());
	}


	@FXML
	void initialize() throws SQLException {
		set_catogoryPromtText();

		cbCategory.getItems().setAll(new BookDAO().getBookCategory());

		//Set Table
		colID.setCellValueFactory(new PropertyValueFactory<Books, Integer>("BID"));
		colName.setCellValueFactory(new PropertyValueFactory<Books, String> ("BName"));
		colCategory.setCellValueFactory(new PropertyValueFactory<Books, String>("CName"));
		colStock.setCellValueFactory(new PropertyValueFactory<Books, Integer>("BStock"));
		colUPrice.setCellValueFactory(new PropertyValueFactory<Books, Double>("BUnitPrice"));


		//Set user picture
		UserPicture.setPic(userPic);
		
		GUI_Access_Control();

		TableUtils.autoResizeColumns(TVBook);

	}

	void GUI_Access_Control() throws SQLException {

		if(LoggedInDetails.RoleName.contains("Admin")) {
			cbCategory.setEditable(true);
		}else {
			rbDelete.setDisable(true);
			rbDeleteCategory.setDisable(true);
			cbCategory.setEditable(true);
		}

		if(!rbSearch.isSelected())
			TVBook.setItems(new BookDAO().getBookDetails());

	}

	void set_catogoryPromtText() {
		cbCategory.getEditor().focusedProperty().addListener(
				(observable, oldValue, newValue) -> {
					if (oldValue == false) 
						cbCategory.setPromptText(null); 
					else
						cbCategory.setPromptText("Category");
				}
		);
	}


}


