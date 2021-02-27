package util;

import java.io.File;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

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
}
