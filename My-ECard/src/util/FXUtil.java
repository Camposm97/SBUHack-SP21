package util;

import java.io.File;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser.ExtensionFilter;

public class FXUtil {
	public static Parent loadLayout(String path) {
		try {
			File file = new File(path);
			Parent parent = FXMLLoader.load(file.toURI().toURL());
			return parent;
		} catch (Exception e) {
			e.printStackTrace();
			return new Pane();
		}
	}
	
	public static ExtensionFilter getDefaultExtFilter() {
		return new ExtensionFilter("Data", "*.dat");
	}
	
	public static void showWIP() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setHeaderText("WIP");
		alert.setContentText("Not Implemented Yet");
		alert.showAndWait();
	}
}
