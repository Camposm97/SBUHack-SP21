package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AppController {
	
	@FXML
	ImageView bg_image;
	@FXML
	ImageView id_image;
	@FXML
	Button bg_btn;
	@FXML
	Button id_btn;
	
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
	
	// I will do this :)
	public void onNameEntered(ActionEvent event) {
		onSubmitName();
		onClickAddress();
		tfAddress.requestFocus();
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
        } catch (IOException ex) {
            ex.printStackTrace();
        }
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
}
