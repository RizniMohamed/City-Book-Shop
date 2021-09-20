package rizni.citybookshop.transaction;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.scene.control.DatePicker;
import rizni.citybookshop.reuseable.MessageBox;
import rizni.citybookshop.reuseable.NumberUtils;

class transactionValidation {

	public boolean verfy(JFXComboBox<String> cbCategory, JFXTextField txtQuantity, JFXTextField txtID,
			JFXTextField txtName, JFXTextField txtTotal, DatePicker datePicker) {
		
		if (!( cbCategory.getValue() == null || txtQuantity.getText().isEmpty() || txtID.getText().isEmpty() ||
				   txtName.getText().isEmpty() || txtTotal.getText().isEmpty() || datePicker == null
				   )) {			
				 
				if(!NumberUtils.isNumeric(txtID.getText())) {
					new MessageBox().showDialog("Error", "Validation", "ID can only be numeric value");
					return false;
				}else if(!NumberUtils.isNumeric(txtQuantity.getText())) {
					new MessageBox().showDialog("Error", "Validation", "Quantity can only be numeric value");
					return false;
				}else if(!NumberUtils.isNumeric(txtTotal.getText())) {
					new MessageBox().showDialog("Error", "Validation", "Total can only be numeric value");
					return false;
				}
				return true;
			}else
				new MessageBox().showDialog("Error", "Validation", "Empty fields Detected");
			return false;
	}

	
}
