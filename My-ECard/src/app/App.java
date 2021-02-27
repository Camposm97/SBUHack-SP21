package app;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.FXUtil;

public class App extends Application {
	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXUtil.loadLayout("ui/App.fxml");
		stage.setTitle("My E-Card");
		stage.setScene(new Scene(root));
		stage.setResizable(false);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
