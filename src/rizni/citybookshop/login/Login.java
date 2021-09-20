package rizni.citybookshop.login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Login extends Application {
	
	private static int count = 0;
	
	@Override
	public void start(Stage primaryStage) {
		try { 
			boolean fullscreen = primaryStage.isFullScreen(); 
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("login.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
			primaryStage.setScene(scene);
			if ( count == 0) {
				count++;
				primaryStage.initStyle(StageStyle.TRANSPARENT);
			}
			primaryStage.getIcons().add(new Image("winLogo.png"));	
			
			primaryStage.setFullScreen(fullscreen);
			
			primaryStage.show();
		} catch(Exception e) {e.printStackTrace();}
	}

	public static void main(String[] args) {
		launch(args);
	}
}