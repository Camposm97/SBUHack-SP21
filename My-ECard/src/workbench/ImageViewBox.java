package workbench;

import com.sun.istack.internal.NotNull;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;

public class ImageViewBox {
    private double orgSceneX, orgSceneY, orgTranslateX, orgTranslateY;
    private ImageView iv;
    private Line xLine, yLine;
    private Group root;
    private Scene scene;

    public ImageViewBox(@NotNull Image image, @NotNull Group root, @NotNull Scene scene) {
        iv = new ImageView(image);
        this.root = root;
        this.scene = scene;
        xLine = new Line(0, scene.getHeight() / 2, scene.getWidth(), scene.getHeight() / 2);
        yLine = new Line(scene.getWidth() / 2, 0, scene.getWidth() / 2, scene.getHeight());
        addListeners();
    }

    private void addListeners() {
        iv.setOnMousePressed(e -> {
            orgSceneX = e.getSceneX();
            orgSceneY = e.getSceneY();
            orgTranslateX = ((ImageView)(e.getSource())).getTranslateX();
            orgTranslateY = ((ImageView)(e.getSource())).getTranslateY();
        });
        iv.setOnMouseDragged(e -> {
            double offsetX = e.getSceneX() - orgSceneX;
            double offsetY = e.getSceneY() - orgSceneY;
            double newTranslateX = orgTranslateX + offsetX;
            double newTranslateY = orgTranslateY + offsetY;

            iv.setTranslateX(newTranslateX);
            iv.setTranslateY(newTranslateY);
            double xHalfScene = scene.getWidth() / 2.0;
            double yHalfScene = scene.getHeight() / 2.0;
            double xCenter = iv.getTranslateX() + iv.getFitWidth() / 2.0;
            double yCenter = iv.getTranslateY() + iv.getFitHeight() / 2.0;
            // Check if image is touching y-axis
            if (isNearAxis(xCenter, xHalfScene)) {
                iv.setTranslateX(xHalfScene - (iv.getFitWidth() / 2.0));
                if (!root.getChildren().contains(yLine)) {
                    root.getChildren().add(yLine);
                }
            } else {
                root.getChildren().remove(yLine);
            }

            // Check if image is touching x-axis
            if (isNearAxis(yCenter, yHalfScene)) {
                iv.setTranslateY(yHalfScene - (iv.getFitHeight() / 2.0));
                if (!root.getChildren().contains(xLine)) {
                    root.getChildren().add(xLine);
                }
            } else { // Remove the line if no longer on center with respect to Y
                root.getChildren().remove(xLine);
            }
        });
        iv.setOnMouseReleased(e ->{
            root.getChildren().remove(xLine);
            root.getChildren().remove(yLine);
        });
    }

    public ImageView getImageView() {
        return iv;
    }

    public boolean isNearAxis(double centerCoord, double sceneCoord) {
        return centerCoord <= sceneCoord + 15 && centerCoord >= sceneCoord - 15;
    }
}
