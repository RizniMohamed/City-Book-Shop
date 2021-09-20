module CITY.BOOK.SHOP {

	requires com.jfoenix;
	requires java.sql;
	requires javafx.base;
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires de.jensd.fx.glyphs.fontawesome;
	requires java.mail;
	
	opens rizni.citybookshop.forgetpassword to javafx.graphics, javafx.fxml;
	opens rizni.citybookshop.book to javafx.graphics, javafx.fxml;
	opens rizni.citybookshop.login to javafx.graphics, javafx.fxml;
	opens rizni.citybookshop.resetpassword to javafx.graphics, javafx.fxml;
	opens rizni.citybookshop.account to javafx.graphics, javafx.fxml;
	opens rizni.citybookshop.statistics to javafx.graphics, javafx.fxml;
	opens rizni.citybookshop.invoice to javafx.graphics, javafx.fxml;
	opens rizni.citybookshop.transaction to javafx.graphics, javafx.fxml;
	opens rizni.citybookshop.dashboard to javafx.graphics, javafx.fxml;
	opens rizni.citybookshop.reuseable to javafx.base;
	
	
}