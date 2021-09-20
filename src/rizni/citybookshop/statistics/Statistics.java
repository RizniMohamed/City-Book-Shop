package rizni.citybookshop.statistics;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Statistics extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			boolean fullscreen = primaryStage.isFullScreen(); 
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("statistics.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("statistics.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.getIcons().add(new Image("winLogo.png"));	
			
			primaryStage.setFullScreen(fullscreen);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}