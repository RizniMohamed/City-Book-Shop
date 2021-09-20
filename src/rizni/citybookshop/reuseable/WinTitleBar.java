package rizni.citybookshop.reuseable;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class WinTitleBar {

	//Custome variables
	Double xmouse,ymouse = 0.0;
	
	public void winBtnClick(MouseEvent event) {
		Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
		String value = ((Label)event.getSource()).getId();

		if (value.equals("close"))
			s.close();
		else if ( value.equals("maximize")) {
			if (s.isFullScreen())
				s.setFullScreen(false); 
			else
				s.setFullScreen(true);
		}else
			s.setIconified(true);
	}
 
	public void drgStart(MouseEvent event) {
		xmouse = event.getSceneX();
		ymouse = event.getSceneY();
	}

	public void drgEnd(MouseEvent event) {
		Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
		s.setX(event.getScreenX() - xmouse);
		s.setY(event.getScreenY() - ymouse);
	}

	public void WinTitleBar_Clicked(MouseEvent event) {
		if(event.getClickCount() > 1) {
			Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
			if (s.isFullScreen())
				s.setFullScreen(false);
			else
				s.setFullScreen(true);
		}
	}
	
}
