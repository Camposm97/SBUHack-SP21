package util;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.stream.Collectors;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.PickResult;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser.ExtensionFilter;

import static javafx.collections.FXCollections.observableList;

public class FXUtil {
	public static void changeScene(Scene scene, Parent root) {
		scene.setRoot(root);
	}

	public static Parent loadLayout(String path) {
		try {
			File file = new File(path);
			Parent parent = FXMLLoader.load(file.toURI().toURL());
			return parent;
		} catch (Exception e) {
			e.printStackTrace();
			return new Pane();
		}
	}

	public static Image loadImage(String src) {
		try {
			File file = new File(src);
			FileInputStream fis = new FileInputStream(file);
			Image image = new Image(fis);
			return image;
		} catch (Exception e) {
			return null;
		}
	}
	
	public static ExtensionFilter getSaveExtFilter() {
		return new ExtensionFilter("Data", "*.dat");
	}

	public static ExtensionFilter[] getImageExtFilters() {
		return new ExtensionFilter[] {new ExtensionFilter("PNG", "*.png"), new ExtensionFilter("JPG", "*.jpg")};
	}
	
	public static void showWIP() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setHeaderText("WIP");
		alert.setContentText("Not Implemented Yet");
		alert.showAndWait();
	}

	/**
	 * Purpose of this method is to modify a label's font (font family and size);
	 * @param label
	 * @return
	 */
	public static void showFontDialog(Label label) {
		Alert dialog = new Alert(AlertType.CONFIRMATION);
		dialog.setTitle("Select Font");
		dialog.setHeaderText("Select A Font");
		ComboBox<String> cbFontNames = new ComboBox<>();
		cbFontNames.getItems().addAll(Font.getFontNames());
		ComboBox<Double> cbFontSizes = new ComboBox<>();
		cbFontSizes.getItems().addAll(10.0,12.0,14.0,16.0,18.0,20.0,22.0,24.0,26.0,27.0,29.0,30.0,32.0,42.0,52.0,62.0, 72.0);

		cbFontNames.setOnAction(e -> {
			label.setFont(Font.font(cbFontNames.getValue(), cbFontSizes.getValue()));
		});
		cbFontSizes.setOnAction(e -> {
			label.setFont(Font.font(cbFontNames.getValue(), cbFontSizes.getValue()));
		});
		cbFontNames.setValue(label.getFont().getName());
		cbFontSizes.setValue(label.getFont().getSize());
		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.add(new Label("Font Family:"), 0, 0);
		gridPane.add(new Label("Font Size:"), 0, 1);
		gridPane.add(cbFontNames, 1, 0);
		gridPane.add(cbFontSizes, 1, 1);

		dialog.getDialogPane().setContent(gridPane);
		dialog.showAndWait();
	}

	/**
	 * Purpose of this method is to modify a label's color (uses ColorPicker)
	 * @param label
	 */
	public static void showColorDialog(Label label) {
		Alert dialog = new Alert(AlertType.CONFIRMATION);
		dialog.setTitle("Select Color");
		dialog.setHeaderText("Select A Color");
		ColorPicker picker = new ColorPicker();
		picker.setOnAction(e -> {
			Color color = picker.getValue();
			label.setTextFill(color);
		});
		dialog.getDialogPane().setContent(new StackPane(picker));
		dialog.showAndWait();
	}
}
