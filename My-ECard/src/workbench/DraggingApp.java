package workbench;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.FXUtil;


public class DraggingApp extends Application {
    @Override
    public void start(Stage stage) {
        AnchorPane root =(AnchorPane) FXUtil.loadLayout("ui/Dragger.fxml");
        Scene scene = new Scene(root, 1100, 700);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("My E-Card");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
