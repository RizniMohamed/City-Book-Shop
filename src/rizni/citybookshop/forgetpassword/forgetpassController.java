package rizni.citybookshop.forgetpassword;

import java.util.Random;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import rizni.citybookshop.resetpassword.ResetPassword;
import rizni.citybookshop.reuseable.WinTitleBar;

public class forgetpassController {

	//Custom variable 
	Integer code =  new Random().nextInt(99999);

	//FXML Variable
	@FXML
	public JFXTextField txtUsername;

	@FXML
	private ImageView logo;

	@FXML
	private JFXTextField txtCode;

	@FXML
	private JFXButton btnSubmit;

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
	void btnSubmit(MouseEvent event) {
		if(new ForgetPasswordValidation().verify(txtUsername,txtCode,btnSubmit,event,code)) {
			Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
			new ResetPassword().start(s);
		}
	}

	@FXML
	public void initialize() {
		System.out.println(code);
		txtCode.setDisable(true);
		btnSubmit.setText("Search");
		logo.setImage(new Image("bklogo.png"));
	}

}
