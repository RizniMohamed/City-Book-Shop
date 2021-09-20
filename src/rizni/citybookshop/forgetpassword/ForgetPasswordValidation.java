package rizni.citybookshop.forgetpassword;

import java.sql.SQLException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.scene.input.MouseEvent;
import rizni.citybookshop.reuseable.Employee;
import rizni.citybookshop.reuseable.MessageBox;

class ForgetPasswordValidation {

	boolean verify(JFXTextField txtUsername,JFXTextField txtCode, JFXButton btnSubmit, MouseEvent event, Integer code) {
		String username = txtUsername.getText();
		Employee e = new Employee();

		if(!username.isEmpty() ){
			try {
				e.setLUsername(new ForgetPasswordDAO().getUsername(username).getLUsername());
			} catch (SQLException e1) {
				new MessageBox().showDialog("Error", "Database", "Cannot find username : " + e1);
			}

			if (username.equals(e.getLUsername())) {
				if(txtCode.isDisable()) {

					try { new MessageBox().showDialog("Info", "Email verification", "Your vefification code has been sent to your " + new ForgetPasswordDAO().getMail(username).getEMail() +" email address");
					} catch (SQLException e2) {e2.printStackTrace();}
 
					Thread t1 = new Thread( () -> {
						try {
							System.out.println(" Mail Started");
							new SendMail().send(new ForgetPasswordDAO().getMail(username).getEMail(),"City bookshop - forget password verification","Your reset code is : " + code);
							System.out.println(" mail end");
						} catch (SQLException e1) {
							new MessageBox().showDialog("Error", "Database", "Unable to get mail address");
						}    
					} );

					t1.start();			

					txtCode.setDisable(false);
					btnSubmit.setText("Submit");
				}else {
					if(txtCode.getText().equals(code.toString())) {
						return true;
					}else if (!txtCode.getText().isEmpty()) {
						new MessageBox().showDialog("Error", "Login validation" ,"Invalid code : Check your code and try again");
					}else {
						new MessageBox().showDialog("Error", "Login validation" ,"Empty field detected : code cannot be empty");
					}
				}

			}else {
				new MessageBox().showDialog("Error", "Login validation" ,"Invalid username : Check your username and try again");
			}


		}else {
			new MessageBox().showDialog("Error", "Validation", "Empty fields detected : Username cannot be empty");
		}
		return false;

	}

}
