package rizni.citybookshop.resetpassword;

import java.sql.SQLException;

import com.jfoenix.controls.JFXPasswordField;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import rizni.citybookshop.dashboard.Dashboard;
import rizni.citybookshop.reuseable.LoggedInDetails;
import rizni.citybookshop.reuseable.MessageBox;
import rizni.citybookshop.reuseable.WinTitleBar;

public class resetpasswordController   {
	
	//FXML Variable
    @FXML
    private JFXPasswordField txtNewPass;
    
    @FXML
    private ImageView logo;

    @FXML
    private JFXPasswordField txtConfirmPass;
    
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
    void btnSubmit_Clicked(MouseEvent event) throws SQLException {
		if(new ResetPasswordValidation().verify(txtNewPass,txtConfirmPass)) {
			new ResetPasswordDAO().setNewPassword(txtNewPass.getText(), LoggedInDetails.username);
			new MessageBox().showDialog("Info", null, "Password reset successfully");
			Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
			new Dashboard().start(s);
		}
    }
    
    @FXML
	public void initialize() {
		logo.setImage(new Image("bklogo.png"));
	}
}
