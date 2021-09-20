package rizni.citybookshop.login;

import java.sql.SQLException;
import java.util.Arrays;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import rizni.citybookshop.reuseable.Employee;
import rizni.citybookshop.reuseable.MessageBox;

class LoginValidation 
{

	boolean verify(JFXTextField txtUsername, JFXPasswordField txtPassword, JFXCheckBox chkbxRemember) throws SQLException {
		char[] password = txtPassword.getText().toCharArray();
		String username = txtUsername.getText();


		if( password.length != 0 && !username.isEmpty()) {

			Employee e1 = new Employee();
			e1.setLUsername(username);

			try {
				e1.setLPassword(new LoginDAO().getUsernamePassword(username).getLPassword());
				e1.setLUsername(new LoginDAO().getUsernamePassword(username).getLUsername());
			} catch (SQLException e2) {
				new MessageBox().showDialog("Error", "Database" ,"Unable to get database" + e2);
			}

			if (username.equals(e1.getLUsername())) {
				if(Arrays.equals(password , e1.getLPassword().toCharArray())) {
					return true;
				}else {
					new MessageBox().showDialog("Error", "Login validation" ,"Invalid password : Check your password and try again");
				}

			}else {
				new MessageBox().showDialog("Error", "Login validation" ,"Invalid username : Check your username and try again");
			}

		}else {
			new MessageBox().showDialog("Error", "Login Verification" , "Empty fields detected : username or password cannot be empty");
		}
		return false;
	}

	void rememberMe(boolean check, String username) throws SQLException {
		
		if (check) {
			new LoginDAO().makeRememberALLNone();
			new LoginDAO().makeRemember(username);
		} else {
			new LoginDAO().makeRememberALLNone();
		}

	}

	String[] getRememberMe() {
		
		Employee e1 = new Employee();
		try {
			e1 = new LoginDAO().getRememberMe();
		} catch (SQLException e) {
			new MessageBox().showDialog("Error", "Database" , "Unable to get database : " + e);
		}
		String[] usernamePassword = {e1.getLUsername(),e1.getLPassword()};
		return usernamePassword;


	}

}
