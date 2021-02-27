package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AppController {
	
	@FXML
	ImageView bg_image;
	@FXML
	ImageView id_image;
	@FXML
	Rectangle bg_border;
	@FXML
	Rectangle bg_line;
	@FXML
	Rectangle id_border;
	@FXML
	ColorPicker bg_colorPicker;
	@FXML
	ColorPicker id_colorPicker;
	Color idColor = Color.DODGERBLUE;
	
	@FXML
	TextField tfName;
	@FXML
	TextField tfAddress;
	@FXML
	TextField tfPhone;
	@FXML
	TextField tfEmail;
	@FXML
	TextField tfWebsite;
	
	@FXML
	Button btn_0;
	@FXML
	Button btn_1;
	@FXML
	Button btn_2;
	@FXML
	Button btn_3;
	@FXML
	Button btn_4;
	
	@FXML
	Label label_0;
	@FXML
	Label label_1;
	@FXML
	Label label_2;
	@FXML
	Label label_3;
	@FXML
	Label label_4;
	
	public void importAsset(ActionEvent event) {
		FileChooser fc = new FileChooser();
		File file = fc.showOpenDialog(new Stage());
		System.out.println(file);
	}
	
	public void loadBackgroundImage() {
		FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG); 
        File file = fileChooser.showOpenDialog(null);           
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            bg_image.setImage(image);
            double unitSize = image.getWidth() / bg_image.getFitWidth();
            bg_image.setViewport(new Rectangle2D(0, (image.getHeight() - (bg_image.getFitHeight() * unitSize)) /2.0  , image.getWidth(), bg_image.getFitHeight() * unitSize));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
	}
	public void loadIdImage() {
		FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        File file = fileChooser.showOpenDialog(null);         
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            id_image.setImage(image);
            double unitSize = image.getHeight() / id_image.getFitHeight();
            id_image.setViewport(new Rectangle2D((image.getWidth() - (id_image.getFitWidth() * unitSize)) /2.0 , 0 , id_image.getFitWidth() * unitSize, image.getHeight()));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
	}

	// I will do this :)
		public void onNameEntered(ActionEvent event) {
			onSubmitName();
			onClickAddress();
			tfAddress.requestFocus();
		}
		
		public void onAddressEntered(ActionEvent event) {
			onSubmitAddress();
			onClickPhone();
			tfPhone.requestFocus();
		}
		
		public void onPhoneEntered(ActionEvent event) {
			onSubmitPhone();
			onClickEmail();
			tfEmail.requestFocus();
		}
		
		public void onEmailEntered(ActionEvent event) {
			onSubmitEmail();
			onClickWebsite();
			tfWebsite.requestFocus();
		}
		
		public void onWebsiteEntered(ActionEvent event) {
			onSubmitWebsite();
		}

		public void onClickName() {
			tfName.setVisible(true);
			btn_0.setVisible(true);
			label_0.setVisible(false);
			tfName.requestFocus();
		}

		public void onSubmitName() {
			label_0.setVisible(true);
			label_0.setText(tfName.getText());
			tfName.setVisible(false);
			btn_0.setVisible(false);
		}

		public void onClickAddress() {
			tfAddress.setVisible(true);
			btn_1.setVisible(true);
			label_1.setVisible(false);
		}

		public void onSubmitAddress() {
			label_1.setVisible(true);
			label_1.setText(tfAddress.getText());
			tfAddress.setVisible(false);
			btn_1.setVisible(false);
		}

		public void onClickPhone() {
			tfPhone.setVisible(true);
			btn_2.setVisible(true);
			label_2.setVisible(false);
		}

		public void onSubmitPhone() {
			label_2.setVisible(true);
			label_2.setText(tfPhone.getText());
			tfPhone.setVisible(false);
			btn_2.setVisible(false);
		}

		public void onClickEmail() {
			tfEmail.setVisible(true);
			btn_3.setVisible(true);
			label_3.setVisible(false);
		}

		public void onSubmitEmail() {
			label_3.setVisible(true);
			label_3.setText(tfEmail.getText());
			tfEmail.setVisible(false);
			btn_3.setVisible(false);
		}

		public void onClickWebsite() {
			tfWebsite.setVisible(true);
			btn_4.setVisible(true);
			label_4.setVisible(false);
		}

		public void onSubmitWebsite() {
			label_4.setVisible(true);
			label_4.setText(tfWebsite.getText());
			tfWebsite.setVisible(false);
			btn_4.setVisible(false);
		}
		
		public void onBgBorderClick() {
			if (bg_colorPicker.isVisible()) {
				bg_colorPicker.setVisible(false);
			}
			else {
				bg_colorPicker.setVisible(true);
			}
		}
		public void onIdBorderClick() {
			if (id_colorPicker.isVisible()) {
				id_colorPicker.setVisible(false);
			}
			else {
				id_colorPicker.setVisible(true);
			}
		}
		public void setBgBorderColor() {
			bg_line.setStroke(bg_colorPicker.getValue());
			bg_colorPicker.setVisible(false);
		}
		public void setIdBorderColor() {
			idColor = id_colorPicker.getValue();
			id_border.setFill(idColor);
			id_colorPicker.setVisible(false);
		}
		public void onBgMouseEnter() {
			bg_border.setStroke(javafx.scene.paint.Color.GOLDENROD);
			bg_border.setVisible(true);
		}
		public void onIdMouseEnter() {
			id_border.setFill(javafx.scene.paint.Color.GOLDENROD);
		}
		public void onBgMouseExit() {
			bg_border.setVisible(false);
		}
		public void onIdMouseExit() {
			id_border.setFill(idColor);
		}

}
