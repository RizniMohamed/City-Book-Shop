package rizni.citybookshop.login;
import java.sql.SQLException;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import rizni.citybookshop.dashboard.Dashboard;
import rizni.citybookshop.forgetpassword.ForgetPassword;
import rizni.citybookshop.reuseable.LoggedInDetails;
import rizni.citybookshop.reuseable.WinTitleBar;

public class loginController {

	//FXML Variables
	@FXML
	protected JFXTextField txtUsername;
	
    @FXML
    private JFXCheckBox chkbxRemember;

	@FXML
	protected JFXPasswordField txtPassword;
	
    @FXML
    private ImageView logo;

	//Objects
	WinTitleBar titlebar = new WinTitleBar();
	LoginValidation lv = new LoginValidation();

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
	void btnLogin_Clicked(MouseEvent event) throws SQLException {
		if(lv.verify(txtUsername,txtPassword,chkbxRemember)) {
			new LoginValidation().rememberMe(chkbxRemember.isSelected(), txtUsername.getText().toString());
			LoggedInDetails.username = txtUsername.getText().toString();
			Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
			new Dashboard().start(s);
		}
	}

	@FXML
	void forgetPass_Clicked(MouseEvent event) {
		LoggedInDetails.username = txtUsername.getText().toString();
		Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
		new ForgetPassword().start(s);
	}

	
	@FXML
	public void initialize() {
		txtUsername.setText(new LoginValidation().getRememberMe()[0]);
		txtPassword.setText(new LoginValidation().getRememberMe()[1]);
		logo.setImage(new Image("bklogo.png"));
	}

}
