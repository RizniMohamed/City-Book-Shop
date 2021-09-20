package rizni.citybookshop.reuseable;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MessageBox {

	public void showDialog(String title,String headerText,String body) {

		Alert alert = null;

		if (title == ("Info"))
			alert = new Alert(AlertType.INFORMATION);
		else
			alert = new Alert(AlertType.ERROR);

		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image("winLogo.png"));
		alert.setTitle("Mars Simulation Project");
		alert.setHeaderText("Multiplayer Host");
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(body);
		alert.setResizable(true);
		alert.showAndWait().ifPresent(rs -> {
			if (rs == ButtonType.OK) {
				System.out.println("Pressed OK.");
			}
		});
	}

	public Optional<ButtonType> ConfrimDialog(String title,String headerText,String body) {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setResizable(true);
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image("winLogo.png"));
		alert.setTitle("Mars Simulation Project");
		alert.setHeaderText("Multiplayer Host");
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(body);
		Optional<ButtonType> result = alert.showAndWait();
		return result;
	}

}
