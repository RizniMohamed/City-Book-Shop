package rizni.citybookshop.statistics;

import java.sql.SQLException;

import com.jfoenix.controls.JFXComboBox;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tooltip;
import rizni.citybookshop.reuseable.MessageBox;

class Charts {
	void setPieBooks(PieChart pieBooks, JFXComboBox<String> cbCategory) throws SQLException {
		ObservableList<Data> dataBookList = FXCollections.observableArrayList(new statisticsDAO().getBookDetail(cbCategory.getValue())) ;


		pieBooks.setData(dataBookList);

		for (final Data data : pieBooks.getData()) {
			Tooltip.install(data.getNode(), new Tooltip(  data.getName() + "\n" +String.valueOf((int)data.getPieValue())));

			data.getNode().setOnMouseEntered(event -> data.getNode().getStyleClass().add("onHover"));
			data.getNode().setOnMouseExited(event -> data.getNode().getStyleClass().remove("onHover"));
		}

	}

	@SuppressWarnings("unchecked")
	public void setLineMonthlySale(LineChart<String, Number> lineMonth, JFXComboBox<String> cbYear) throws SQLException {
		XYChart.Series<String, Number> monthlySales =new statisticsDAO().getMonthlySale(cbYear.getValue());
		monthlySales.setName("Monthly sales");

		lineMonth.getData().setAll(monthlySales);

		for (XYChart.Series<String, Number> s : lineMonth.getData()) {
			for (XYChart.Data<String, Number> d : s.getData()) {
				Tooltip.install(d.getNode(), new Tooltip(
						d.getXValue().toString() + "\n" +"Total sales : " + d.getYValue()
						));

				//Adding class on hover
				d.getNode().setOnMouseEntered(event -> d.getNode().getStyleClass().add("onHover"));

				//Removing class on exit
				d.getNode().setOnMouseExited(event -> d.getNode().getStyleClass().remove("onHover"));
			}
		}
	}

	public void setPieDailySale(PieChart pieDaySales, DatePicker dpDay) throws SQLException {
		ObservableList<Data> dataDailySales = FXCollections.observableArrayList(new statisticsDAO().getDailySale(dpDay.getValue())) ;
		
		if ( !dataDailySales.isEmpty()) {
			pieDaySales.setData(dataDailySales);

			for (final Data data : pieDaySales.getData()) {
				Tooltip.install(data.getNode(), new Tooltip(  data.getName() + "\n" +String.valueOf((int)data.getPieValue())));

				data.getNode().setOnMouseEntered(event -> data.getNode().getStyleClass().add("onHover"));
				data.getNode().setOnMouseExited(event -> data.getNode().getStyleClass().remove("onHover"));
			}

		}else {
			new MessageBox().showDialog("Error", null, "No sales occured");
		}

	}


}
