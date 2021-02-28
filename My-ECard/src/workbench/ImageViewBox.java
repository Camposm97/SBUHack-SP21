package workbench;

import com.sun.istack.internal.NotNull;
import javafx.scene.Group;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import model.DraggedCardData;
import model.DraggedImageData;
import model.ImageType;

import javax.smartcardio.Card;

public class ImageViewBox {
    private double orgSceneX, orgSceneY, orgTranslateX, orgTranslateY;
    private ImageView iv;
    private ImageType imageType;
    private Line xLine, yLine;
    private Group root;
    private Pane parent;
    private DraggedCardData cardData;
    private DraggedImageData draggedImageData;

    public ImageViewBox(@NotNull Image image, @NotNull ImageType imageType, @NotNull Group root, @NotNull DraggedCardData cardData) {
        iv = new ImageView(image);
        iv.setFitWidth(image.getWidth());
        iv.setFitHeight(image.getHeight());
        this.imageType = imageType;
        this.root = root;
        this.parent = (Pane) root.getParent();
        this.cardData = cardData;
        xLine = new Line(0, parent.getHeight() / 2, parent.getWidth(), parent.getHeight() / 2);
        xLine.setStroke(Color.RED);
        yLine = new Line(parent.getWidth() / 2, 0, parent.getWidth() / 2, parent.getHeight());
        yLine.setStroke(Color.RED);
        this.draggedImageData = new DraggedImageData(this);
        this.cardData.addImage(draggedImageData);
        root.getChildren().add(iv);
        addListeners();
    }

    public ImageType getImageType() {
        return imageType;
    }

    public DraggedImageData getDraggedImageData() {
        return draggedImageData;
    }

    private void addListeners() {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem item1 = new MenuItem("Delete");
        contextMenu.getItems().addAll(item1);
        iv.setOnContextMenuRequested(e -> {
            contextMenu.show(iv, e.getScreenX(), e.getScreenY());
        });
        item1.setOnAction(e -> {
            root.getChildren().remove(iv);
            cardData.removeImage(draggedImageData);
        });
        iv.setOnMousePressed(e -> {
            if (e.isPrimaryButtonDown()) {
                orgSceneX = e.getSceneX();
                orgSceneY = e.getSceneY();
                orgTranslateX = ((ImageView)(e.getSource())).getTranslateX();
                orgTranslateY = ((ImageView)(e.getSource())).getTranslateY();
            }
        });
        iv.setOnMouseDragged(e -> {
            double offsetX = e.getSceneX() - orgSceneX;
            double offsetY = e.getSceneY() - orgSceneY;
            double newTranslateX = orgTranslateX + offsetX;
            double newTranslateY = orgTranslateY + offsetY;

            iv.setTranslateX(newTranslateX);
            iv.setTranslateY(newTranslateY);
            double xHalfScene = parent.getWidth() / 2.0;
            double yHalfScene = parent.getHeight() / 2.0;
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
