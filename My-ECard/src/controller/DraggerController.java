package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.DraggedCardData;
import model.ImageType;
import model.TextBox;
import util.FXUtil;
import workbench.ImageViewBox;

import java.io.File;
import java.util.Optional;

public class DraggerController {
    public static DraggedCardData currentCard;
    @FXML
    Group group;

    public DraggerController() {
        Platform.runLater(() -> {
            currentCard = new DraggedCardData();
        });
    }

    public void newCard(ActionEvent event) {
        if (!group.getChildren().isEmpty()) {
            group.getChildren().clear();
        }
    }

    public void openFile(ActionEvent event) {

    }

    public void closeFile(ActionEvent event) {

    }

    public void save(ActionEvent event) {

    }

    public void saveAs(ActionEvent event) {

    }

    public void export(ActionEvent event) {
        // TODO
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
