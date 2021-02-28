package model;

import com.sun.istack.internal.NotNull;
import javafx.scene.Group;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import util.FXUtil;

public class TextBox {
    private double orgSceneX, orgSceneY, orgTranslateX, orgTranslateY;
    private Label label;
    private Line xLine, yLine;
    private Group root;
    private Pane parent;
    private DraggedCardData cardData;
    private TextData textData;

    public TextBox(String string, @NotNull Group root, @NotNull DraggedCardData cardData) {
        label = new Label(string);
        this.root = root;
        this.parent = (Pane) root.getParent();
        this.cardData = cardData;
        xLine = new Line(0, parent.getHeight() / 2, parent.getWidth(), parent.getHeight() / 2);
        xLine.setStroke(Color.RED);
        yLine = new Line(parent.getWidth() / 2, 0, parent.getWidth() / 2, parent.getHeight());
        yLine.setStroke(Color.RED);
        this.textData = new TextData(this);
        cardData.addText(textData);
        root.getChildren().add(label);
        addListeners();
    }

    private void addListeners() {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem item1 = new MenuItem("Change Font");
        MenuItem item2 = new MenuItem("Change Color");
        MenuItem item3 = new MenuItem("Delete");
        item3.setOnAction(e -> {
            root.getChildren().remove(label);
            cardData.removeText(textData);
        });
        item2.setOnAction(e -> {
            FXUtil.showColorDialog(label);
        });
        item1.setOnAction(e -> {
            FXUtil.showFontDialog(label);
        });
        contextMenu.getItems().addAll(item1, item2, item3);
        label.setContextMenu(contextMenu);
        label.setOnMousePressed(e -> {
            if (e.isPrimaryButtonDown()) {
                orgSceneX = e.getSceneX();
                orgSceneY = e.getSceneY();
                orgTranslateX = ((Label) (e.getSource())).getTranslateX();
                orgTranslateY = ((Label) (e.getSource())).getTranslateY();
            }
        });
        label.setOnMouseDragged(e -> {
            double offsetX = e.getSceneX() - orgSceneX;
            double offsetY = e.getSceneY() - orgSceneY;
            double newTranslateX = orgTranslateX + offsetX;
            double newTranslateY = orgTranslateY + offsetY;

            label.setTranslateX(newTranslateX);
            label.setTranslateY(newTranslateY);
            double xHalfScene = parent.getWidth() / 2.0;
            double yHalfScene = parent.getHeight() / 2.0;
            double xCenter = label.getTranslateX() + label.getWidth() / 2.0;
            double yCenter = label.getTranslateY() + label.getHeight() / 2.0;
            // Check if image is touching y-axis
            if (isNearAxis(xCenter, xHalfScene)) {
                label.setTranslateX(xHalfScene - (label.getWidth() / 2.0));
                if (!root.getChildren().contains(yLine)) {
                    root.getChildren().add(yLine);
                }
            } else {
                root.getChildren().remove(yLine);
            }

            // Check if image is touching x-axis
            if (isNearAxis(yCenter, yHalfScene)) {
                label.setTranslateY(yHalfScene - (label.getHeight() / 2.0));
                if (!root.getChildren().contains(xLine)) {
                    root.getChildren().add(xLine);
                }
            } else { // Remove the line if no longer on center with respect to Y
                root.getChildren().remove(xLine);
            }
        });
        label.setOnMouseReleased(e -> {
            root.getChildren().remove(xLine);
            root.getChildren().remove(yLine);
        });
    }

    public Label getLabel() {
        return label;
    }

    public boolean isNearAxis(double centerCoord, double sceneCoord) {
        return centerCoord <= sceneCoord + 15 && centerCoord >= sceneCoord - 15;
    }
}
