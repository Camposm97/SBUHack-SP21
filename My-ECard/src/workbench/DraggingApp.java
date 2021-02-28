package workbench;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import util.FXUtil;

public class DraggingApp extends Application {
    Scene scene;
    
    @Override
    public void start(Stage primaryStage) {
        BorderPane root =(BorderPane) FXUtil.loadLayout("ui/Dragger.fxml");
        Group group = (Group) root.getCenter();
        scene = new Scene(root, 800, 700);
        Image image = FXUtil.loadImage("resources/prof.jpg");
        ImageViewBox imageViewBox = new ImageViewBox(image, group, scene);
        ImageView iv = imageViewBox.getImageView();

        group.getChildren().add(iv);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("java-buddy");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
