package rizni.citybookshop.book;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import rizni.citybookshop.reuseable.MessageBox;
import rizni.citybookshop.reuseable.NumberUtils;

class BookValidation {

	public boolean verfy(JFXComboBox<String> cbCategory, JFXTextField txtStock, JFXTextField txtID,
			JFXTextField txtName, JFXTextField txtUPrice) {
		if (!( cbCategory.getValue() == null || txtStock.getText().isEmpty() || txtID.getText().isEmpty() ||
				   txtName.getText().isEmpty() || txtUPrice.getText().isEmpty()	   )) {			
				
				if(!NumberUtils.isNumeric(txtID.getText())) {
					new MessageBox().showDialog("Error", "Validation", "ID can only be numeric value");
					return false;
				}else if ( !NumberUtils.isNumeric(txtUPrice.getText())) {
					new MessageBox().showDialog("Error", "Validation", "Unit price can only be numeric value");
					return false;
				}
				return true;
			}else
				new MessageBox().showDialog("Error", "Validation", "Empty fields Detected");
			return false;
			
		}

}
