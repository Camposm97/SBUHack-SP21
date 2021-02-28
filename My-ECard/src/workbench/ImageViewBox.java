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
import model.DraggedCardData;
import model.DraggedImageData;
import model.ImageType;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class ImageViewBox {
    private double orgSceneX, orgSceneY, orgTranslateX, orgTranslateY;
    private ImageView iv;
    private ImageType imageType;
    private Line xLine, yLine;
    private Group group;
    private Pane parent;
    private DraggedCardData cardData;
    private DraggedImageData imageData;

    public ImageViewBox(DraggedCardData cardData, DraggedImageData imageData, Group group) {
        this.cardData = cardData;
        this.imageData = imageData;
        this.group = group;
        this.parent = (Pane) group.getParent();
        InputStream is = new ByteArrayInputStream(imageData.getBytes());
        Image image = new Image(is);
        this.iv = new ImageView(image);
        this.iv.setTranslateX(this.imageData.getPosition().getX());
        this.iv.setTranslateY(this.imageData.getPosition().getY());
        loadConDetails();
    }

    public ImageViewBox(@NotNull Image image, @NotNull ImageType imageType, @NotNull Group group, @NotNull DraggedCardData cardData) {
        iv = new ImageView(image);
        iv.setFitWidth(image.getWidth());
        iv.setFitHeight(image.getHeight());
        this.imageType = imageType;
        this.group = group;
        this.parent = (Pane) group.getParent();
        this.cardData = cardData;
        this.imageData = new DraggedImageData(this);
        this.cardData.addImage(imageData);
        loadConDetails();
    }

    public void loadConDetails() {
        group.getChildren().add(iv);
        xLine = new Line(0, parent.getHeight() / 2, parent.getWidth(), parent.getHeight() / 2);
        xLine.setStroke(Color.RED);
        yLine = new Line(parent.getWidth() / 2, 0, parent.getWidth() / 2, parent.getHeight());
        yLine.setStroke(Color.RED);
        addListeners();
    }

    public ImageType getImageType() {
        return imageType;
    }

    public DraggedImageData getImageData() {
        return imageData;
    }

    private void addListeners() {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem item1 = new MenuItem("Delete");
        MenuItem item4 = new MenuItem("Bring to Front");
        MenuItem item5 = new MenuItem("Push to Back");
        item5.setOnAction(e -> {
            iv.toBack();
        });
        item4.setOnAction(e -> {
            iv.toFront();
        });
        contextMenu.getItems().addAll(item1, item4, item5);
        iv.setOnContextMenuRequested(e -> {
            contextMenu.show(iv, e.getScreenX(), e.getScreenY());
        });
        item1.setOnAction(e -> {
            group.getChildren().remove(iv);
            cardData.removeImage(imageData);
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
            imageData.getPosition().setX(newTranslateX);
            imageData.getPosition().setY(newTranslateY);
            double xHalfScene = parent.getWidth() / 2.0;
            double yHalfScene = parent.getHeight() / 2.0;
            double xCenter = iv.getTranslateX() + iv.getFitWidth() / 2.0;
            double yCenter = iv.getTranslateY() + iv.getFitHeight() / 2.0;
            // Check if image is touching y-axis
            if (isNearAxis(xCenter, xHalfScene)) {
                iv.setTranslateX(xHalfScene - (iv.getFitWidth() / 2.0));
                if (!group.getChildren().contains(yLine)) {
                    group.getChildren().add(yLine);
                }
            } else {
                group.getChildren().remove(yLine);
            }

            // Check if image is touching x-axis
            if (isNearAxis(yCenter, yHalfScene)) {
                iv.setTranslateY(yHalfScene - (iv.getFitHeight() / 2.0));
                if (!group.getChildren().contains(xLine)) {
                    group.getChildren().add(xLine);
                }
            } else { // Remove the line if no longer on center with respect to Y
                group.getChildren().remove(xLine);
            }
        });
        iv.setOnMouseReleased(e ->{
            group.getChildren().remove(xLine);
            group.getChildren().remove(yLine);
        });
    }

    public ImageView getImageView() {
        return iv;
    }

    public boolean isNearAxis(double centerCoord, double sceneCoord) {
        return centerCoord <= sceneCoord + 15 && centerCoord >= sceneCoord - 15;
    }
}
