package rizni.citybookshop.reuseable;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class UserPicture {
	public static void setPic(ImageView userPic) {
		new LoggedInDetailsDAO().getEmployeeDetails();
		userPic.setImage(new Image(LoggedInDetails.picPath));
		Rectangle rectangle = new Rectangle(0, 0, 100, 150);
		rectangle.setArcWidth(10.0);   // Corner radius
		rectangle.setArcHeight(10.0);
		userPic.setClip(rectangle);
	}

}
