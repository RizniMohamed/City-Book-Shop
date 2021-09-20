package rizni.citybookshop.invoice;

import com.jfoenix.controls.JFXTextField;

import rizni.citybookshop.reuseable.MessageBox;
import rizni.citybookshop.reuseable.NumberUtils;

class InvoiceValidation {

	public boolean verfy(JFXTextField txtID, JFXTextField txtName, JFXTextField txtQuantity, JFXTextField txtUPrice, JFXTextField txtCategory) {

		if (!( txtID.getText().isEmpty() || txtID.getText().isEmpty() || txtCategory.getText().isEmpty() ||
				txtName.getText().isEmpty() || txtUPrice.getText().isEmpty()	 || txtQuantity.getText().isEmpty()  )) {			

			if(!NumberUtils.isNumeric(txtID.getText())) {
				new MessageBox().showDialog("Error", "Validation", "ID can only be numeric value");
				return false;
			}else if ( !NumberUtils.isNumeric(txtUPrice.getText())) {
				new MessageBox().showDialog("Error", "Validation", "Unit price can only be numeric value");
				return false;
			}else if ( !NumberUtils.isNumeric(txtQuantity.getText())) {
				new MessageBox().showDialog("Error", "Validation", "Quantity can only be numeric value");
				return false;
			}
			return true;
		}else
			new MessageBox().showDialog("Error", "Validation", "Empty fields Detected");
		return false;
	}

	public boolean verfy(JFXTextField txtName) {

		if (!(txtName.getText().isEmpty() ))
			return true;
		else
			new MessageBox().showDialog("Error", "Validation", "Empty fields Detected : Book name cannot be empty");

		return false;
	}
}
