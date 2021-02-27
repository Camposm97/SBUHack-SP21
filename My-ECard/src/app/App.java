package app;

import java.io.File;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.FXUtil;

public class App extends Application {
	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXUtil.loadLayout("ui/App.fxml");
		stage.setScene(new Scene(root));
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
