package rizni.citybookshop.invoice;

import java.sql.SQLException;

import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.print.JobSettings;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import rizni.citybookshop.account.Account;
import rizni.citybookshop.book.Book;
import rizni.citybookshop.dashboard.Dashboard;
import rizni.citybookshop.login.Login;
import rizni.citybookshop.reuseable.UserPicture;
import rizni.citybookshop.reuseable.WinTitleBar;
import rizni.citybookshop.statistics.Statistics;
import rizni.citybookshop.transaction.Transaction;

public class invoiceController {

	@FXML
	private JFXTextField txtID;

	@FXML
	private JFXTextField txtName;

	@FXML
	private ImageView logo;
	
	@FXML
	private JFXTextField txtUPrice;

	@FXML
	private JFXTextField txtQuantity;

	@FXML
	private JFXTextField txtCategory;

	@FXML
	private Label lblID;

	@FXML
	private Label lblName;

	@FXML
	private Label lblCategory;

	@FXML
	private Label lblUPrice;

	@FXML
	private Label lblQuantity;

	@FXML
	private Label lblTPrice;

	@FXML
	private VBox invoiceDisplayArea;

	//Objects
	WinTitleBar titlebar = new WinTitleBar();  

	@FXML
	private ImageView userPic;

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
	void btnBooks_Clicked(MouseEvent event) {
		Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
		new Book().start(s);
	}

	@FXML
	void btnTransaction_Clicked(MouseEvent event) throws Exception {
		Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
		new Transaction().start(s);
	}
	
	@FXML
	void btnPrint_Clicked(MouseEvent event) {
		PrinterJob printerJob = PrinterJob.createPrinterJob();
        JobSettings jobSettings = printerJob.getJobSettings();
        PageLayout pageLayout = jobSettings.getPageLayout();
        Printer printer = printerJob.getPrinter();

        pageLayout = printer.createPageLayout(Paper.A4,
                							  PageOrientation.PORTRAIT,
                							  Printer.MarginType.EQUAL);
        
        printerJob.getJobSettings().setPageLayout(pageLayout);
        invoiceDisplayArea.setTranslateX(-350);
        printerJob.printPage(invoiceDisplayArea);
        invoiceDisplayArea.setTranslateX(0);
        printerJob.endJob();
	}

	
	@FXML
	void btnSearch_Clicked(MouseEvent event) throws SQLException {
		if(new InvoiceValidation().verfy(txtName))
			new InvoiceDAO().searchBookDetails(txtName,txtUPrice,txtCategory);
	}


	@FXML
	void btnSubmit_Clicked(MouseEvent event) throws NumberFormatException, SQLException {

		if ( 
			 (new InvoiceValidation().verfy(txtID,txtName,txtQuantity,txtUPrice,txtCategory)) &&
		     (new InvoiceDAO().createNewInvoice(txtID.getText(),txtName.getText(),txtQuantity.getText(),txtUPrice.getText(),txtCategory.getText()))
		){
			lblID.setText(txtID.getText());
			lblName.setText(txtName.getText());
			lblQuantity.setText(txtQuantity.getText());
			lblUPrice.setText("$"+txtUPrice.getText());
			lblCategory.setText(txtCategory.getText());
			lblTPrice.setText("$"+String.valueOf( Double.parseDouble(txtUPrice.getText())*Integer.parseInt(txtQuantity.getText())));
		}
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
	void btnAccount_Clicked(MouseEvent event) {
		Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
		new Account().start(s);
	}



	@FXML
	void initialize() {
		//Set user picture
		UserPicture.setPic(userPic);
		logo.setImage(new Image("bklogo.png"));
	}

}


