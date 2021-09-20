package rizni.citybookshop.account;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import rizni.citybookshop.reuseable.MessageBox;
import rizni.citybookshop.reuseable.NumberUtils;

 class AccountValidation {

	public boolean verfy(JFXComboBox<String> cbAccType, JFXTextField txtEmail, JFXTextField txtID, JFXTextField txtName,
					  JFXTextField txtPass, JFXTextField txtUsername, String picPath) {
		
		if (!( cbAccType.getValue() == null || txtEmail.getText().isEmpty() || txtID.getText().isEmpty() ||
			   txtName.getText().isEmpty() || txtPass.getText().isEmpty() || txtUsername.getText().isEmpty()
			   )) {			
			 
			if(!NumberUtils.isNumeric(txtID.getText())) {
				new MessageBox().showDialog("Error", "Validation", "ID can only be numeric value");
				return false;
			}else if ( !txtEmail.getText().matches( "^(.+)@(.+)$")) {
				new MessageBox().showDialog("Error", "Validation", "Invalid email address");
				return false;
			}
	
			return true;
		}else
			new MessageBox().showDialog("Error", "Validation", "Empty fields Detected");
		return false;
		
	}
	 
}
