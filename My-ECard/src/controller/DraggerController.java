package controller;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.DraggedCardData;
import model.ImageType;
import model.TextBox;
import util.DataUtil;
import util.FXUtil;
import workbench.ImageViewBox;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class DraggerController {
    public static DraggedCardData currentCard;
    @FXML
    AnchorPane theAnchor;
    @FXML
    Group group;

    public DraggerController() {
        Platform.runLater(() -> {
            currentCard = new DraggedCardData();
        });
    }

    public void displayPreBuild(ActionEvent event) {
        Parent root = FXUtil.loadLayout("ui/App.fxml");
        FXUtil.changeScene(group.getScene(), root);
    }

    public void displayDragBuild(ActionEvent event) {
        Parent root = FXUtil.loadLayout("ui/Dragger.fxml");
        FXUtil.changeScene(group.getScene(), root);
    }

    public void openFile(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(FXUtil.getSaveExtFilter());
        File file = fc.showOpenDialog(new Stage());
        if (file != null) {
            currentCard = (DraggedCardData) DataUtil.loadObject(file.getPath());
            group.getChildren().clear();
            if (currentCard != null) {
                currentCard.display(currentCard, group);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Failed to open file!");
                alert.setContentText("Tried to open from " + file);
                alert.showAndWait();
                currentCard = new DraggedCardData();
                group.getChildren().clear();
            }
        }
    }

    public void closeFile(ActionEvent event) {
        displayDragBuild(event);
    }

//    public void save(ActionEvent event) {
//
//    }

    public void saveAs(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(FXUtil.getSaveExtFilter());
        File file = fc.showSaveDialog(new Stage());
        if (file != null) {
            boolean saved = DataUtil.save(currentCard, file.getPath());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            if (saved) {
                alert.setHeaderText("Save Successful");
                alert.setContentText("Saved to " + file);
            } else {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setHeaderText("Save Failed");
                alert.setContentText("Tried to save to " + file);
            }
            alert.showAndWait();
        }
    }

    public void export(ActionEvent event) {
        WritableImage writableImage = theAnchor.snapshot(new SnapshotParameters(), null);
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG", "*.png"));
        File file = fc.showSaveDialog(new Stage());
        if (file != null) {
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(writableImage, null), "png", file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void exitApp(ActionEvent event) {
        Platform.exit();
    }

    public void addText(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add TextBox");
        dialog.setHeaderText("What do you want to say?");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(e -> {
            TextBox textBox = new TextBox(e, group, currentCard);
        });
    }

    public void addImage(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(FXUtil.getImageExtFilters());
        File file = fc.showOpenDialog(new Stage());
        if (file != null) {
            String type = fc.getSelectedExtensionFilter().getExtensions().get(0);
            ImageType imageType = null;
            switch (type) {
                case "*.png":
                    imageType = ImageType.PNG;
                    break;
                case "*.jpg":
                    imageType = ImageType.JPG;
                    break;
            }
            Image image = FXUtil.loadImage(file.getPath());
            ImageViewBox imageViewBox = new ImageViewBox(image, imageType, group, currentCard);
        }
    }
}
