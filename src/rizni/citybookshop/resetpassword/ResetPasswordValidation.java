package rizni.citybookshop.resetpassword;

import java.util.Arrays;

import com.jfoenix.controls.JFXPasswordField;

import rizni.citybookshop.reuseable.MessageBox;

class ResetPasswordValidation  {

	public boolean verify(JFXPasswordField txtNewPass, JFXPasswordField txtConfirmPass) {
		
		if (!(txtNewPass.getText().isEmpty() || txtConfirmPass.getText().isEmpty())) {
			
			if( Arrays.equals(txtConfirmPass.getText().toCharArray(),txtNewPass.getText().toCharArray()) ) {
				return true;
			} else {
				new MessageBox().showDialog("Error","Validation","Passwords mismatched : New password and Confirm password not matched");
			}
			
		}else {
			new MessageBox().showDialog("Error","Validation","Empty fileds detected : New password or Confirm password cannot be empty");
		}
		return false;
	}

}
