package rizni.citybookshop.account;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import rizni.citybookshop.book.Book;
import rizni.citybookshop.dashboard.Dashboard;
import rizni.citybookshop.invoice.Invoice;
import rizni.citybookshop.login.Login;
import rizni.citybookshop.reuseable.Employee;
import rizni.citybookshop.reuseable.LoggedInDetails;
import rizni.citybookshop.reuseable.MessageBox;
import rizni.citybookshop.reuseable.TableUtils;
import rizni.citybookshop.reuseable.UserPicture;
import rizni.citybookshop.reuseable.WinTitleBar;
import rizni.citybookshop.statistics.Statistics;
import rizni.citybookshop.transaction.Transaction;

public class accountController { 

	//Custom Variable
	String picPath = "";
	File Photo;

	//FXML Variable
	@FXML
	private JFXButton btnAccount;

	@FXML
	private JFXTextField txtID;

	@FXML
	private JFXTextField txtName;

	@FXML
	private JFXTextField txtUsername;

	@FXML
	private JFXTextField txtPass;

	@FXML
	private JFXComboBox<String> cbAccType =  new JFXComboBox<>();

	@FXML
	private JFXTextField txtEmail;

	@FXML
	private JFXButton btnPic;

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
	private JFXButton btnSubmit;

	@FXML
	private ImageView userPic;

	@FXML
	private TableView<Employee> TVAccount;

	@FXML
	private TableColumn<Employee, Integer> colID;

	@FXML
	private TableColumn<Employee, String> colName;

	@FXML
	private TableColumn<Employee, String> colUsername;

	@FXML
	private TableColumn<Employee, String> colPassword;

	@FXML
	private TableColumn<Employee, String> colAcctype;

	@FXML
	private TableColumn<Employee, String> colEmail;

	@FXML
	private TableColumn<Employee, String> colPic;

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
	void btnPic_Clicked(MouseEvent event) throws IOException {
		Photo = new FileChooser().showOpenDialog(null);
		picPath = new File("").toURI().relativize(Photo.toURI()).getPath();
	}

	@FXML
	void btnSubmit_Clicked(MouseEvent event) throws InterruptedException, SQLException {
		if (rbSearch.isSelected()) {
			TVAccount.setItems( new AccountDAO().searchAccount(cbAccType.getValue(),txtEmail.getText(),txtID.getText(),txtName.getText(),txtUsername.getText()));
		}else if(new AccountValidation().verfy(cbAccType,txtEmail,txtID,txtName,txtPass,txtUsername,picPath)) {
			if (rbCreate.isSelected()) { 
				new AccountDAO().createNewAccount(cbAccType.getValue(),txtEmail.getText(),txtID.getText(),txtName.getText(),txtPass.getText(),txtUsername.getText(),savePic());
			}else if (rbDelete.isSelected()) {
				if (new MessageBox().ConfrimDialog("Confirm","Account Validation", "Are you sure do you want to remove this account ").get() == ButtonType.OK)
					new AccountDAO().DeleteAccount(txtUsername.getText());
			}else if (rbUpdate.isSelected()) {
				if (new MessageBox().ConfrimDialog("Confirm","Account Validation", "Are you sure do you want to update this account ").get() == ButtonType.OK)
					new AccountDAO().updateAccount(cbAccType.getValue(),txtEmail.getText(),txtID.getText(),txtName.getText(),txtPass.getText(),txtUsername.getText(),savePic());				
			}else {
				new MessageBox().showDialog("Error","Validation","Empty option detected : Select an option among create, delete, search and update.");
			}
		}
		initialize();
	}

	private String savePic() throws InterruptedException {
		if (Photo != null) {
			Path pathSource = Photo.toPath();
			Path pathTarget = new File("C:\\Users\\user\\Desktop\\JAVA\\CITY BOOK SHOP\\Work space\\bin\\img\\user\\"+LoggedInDetails.username+".jpg").toPath();
			Thread t1 = new Thread( () -> {
				try {
					Files.copy(pathSource,pathTarget, StandardCopyOption.REPLACE_EXISTING).toFile().length();
					Thread.sleep(3000);
				}catch (IOException | InterruptedException e) {
					new MessageBox().showDialog(picPath, picPath, e.toString());
				}
			});
			userPic.setImage(null);
			t1.start();t1.join();
			userPic.setImage(new Image(LoggedInDetails.picPath));
			return pathTarget.getFileName().toString();
		}
		return null;
	}

	@FXML
	void btnLogout_Clicked(MouseEvent event) throws Exception {
		Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
		new Login().start(s);
	}

	@FXML
	void btnDashboard_Clicked(MouseEvent event) throws Exception {
		Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
		new Dashboard().start(s);
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
	void btnInvoice_Clicked(MouseEvent event) throws Exception {
		Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
		new Invoice().start(s);
	}

	@FXML
	void btnTransaction_Clicked(MouseEvent event) throws Exception {
		Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
		new Transaction().start(s);
	}

	@FXML
	void getDataTable(MouseEvent event) {
		ObservableList<Employee> e =  TVAccount.getSelectionModel().getSelectedItems();
		txtID.setText(String.valueOf(e.get(0).getEID()));
		txtName.setText(e.get(0).getEName());
		txtUsername.setText(e.get(0).getLUsername());
		txtPass.setText(e.get(0).getLPassword());
		txtEmail.setText(e.get(0).getEMail());
		picPath = (e.get(0).getPhoto());
		cbAccType.getSelectionModel().select(e.get(0).getRname());
	}

	@FXML
	void initialize() throws SQLException {
		//Set Account type
		cbAccType.getItems().setAll("Admin","Cashier");

		//Set Table
		colID.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("EID"));
		colName.setCellValueFactory(new PropertyValueFactory<Employee, String> ("EName"));
		colUsername.setCellValueFactory(new PropertyValueFactory<Employee, String>("LUsername"));
		colPassword.setCellValueFactory(new PropertyValueFactory<Employee, String>("LPassword"));
		colAcctype.setCellValueFactory(new PropertyValueFactory<Employee, String>("Rname"));
		colEmail.setCellValueFactory(new PropertyValueFactory<Employee, String>("EMail"));
		colPic.setCellValueFactory(new PropertyValueFactory<Employee, String>("Photo"));


		//Set user picture
		UserPicture.setPic(userPic);

		GUI_Access_Control();

		TableUtils.autoResizeColumns(TVAccount);

	}

	void GUI_Access_Control() throws SQLException {

		if(LoggedInDetails.RoleName.contains("Cashier")) {
			cbAccType.getSelectionModel().select(1);
			cbAccType.setDisable(true);
			rbCreate.setDisable(true);
			rbSearch.setDisable(true);
			rbDelete.setDisable(true);
			TVAccount.setItems( new AccountDAO().searchAccount(cbAccType.getValue(),
					txtEmail.getText(),
					txtID.getText(),
					txtName.getText(),
					LoggedInDetails.username
					));
		}else {
			if(!rbSearch.isSelected())
				TVAccount.setItems(new AccountDAO().getEmployeeDetails());
			cbAccType.setEditable(true);
		}

	}
}


