package controller;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;

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
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AppController {
	
	@FXML
	Pane cardPane;
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
	
	Color bgColor = Color.BLACK;
	double[] bgImageCoords = null;
	Color idColor = Color.DODGERBLUE;
	double[] idImageCoords = null;
	
	
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
	
	private BufferedImage bufferImage;
	
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
            this.setBgFillCoords(image);
            bg_image.setViewport(new Rectangle2D(this.bgImageCoords[0],this.bgImageCoords[1],this.bgImageCoords[2],this.bgImageCoords[3]));
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
            this.setIdFillCoords(image);
            id_image.setViewport(new Rectangle2D(this.idImageCoords[0],this.idImageCoords[1],this.idImageCoords[2],this.idImageCoords[3]));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
	}
	public void setIdFillCoords(Image image) {
		double unitSize = image.getHeight() / id_image.getFitHeight();
        double[] newIdImageCoords = {(image.getWidth() - (id_image.getFitWidth() * unitSize)) /2.0 , 0 , id_image.getFitWidth() * unitSize, image.getHeight()};
        idImageCoords = newIdImageCoords;
	}
	public void setBgFillCoords(Image image) {
		double unitSize = image.getWidth() / bg_image.getFitWidth();
        double[] newBgImageCoords = {0, (image.getHeight() - (bg_image.getFitHeight() * unitSize)) /2.0  , image.getWidth(), bg_image.getFitHeight() * unitSize};
        bgImageCoords = newBgImageCoords;
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
			bgColor = bg_colorPicker.getValue();
			bg_line.setStroke(bgColor);
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
		public void onNameMouseEnter() {
			label_0.setTextFill(javafx.scene.paint.Color.CHOCOLATE);
		}
		public void onNameMouseExit() {
			label_0.setTextFill(javafx.scene.paint.Color.BLACK);
		}
		public void onAddressMouseEnter() {
			label_1.setTextFill(javafx.scene.paint.Color.GOLDENROD);
		}
		public void onAddressMouseExit() {
			label_1.setTextFill(javafx.scene.paint.Color.BLACK);
		}
		public void onPhoneMouseEnter() {
			label_2.setTextFill(javafx.scene.paint.Color.GOLDENROD);
		}
		public void onPhoneMouseExit() {
			label_2.setTextFill(javafx.scene.paint.Color.BLACK);
		}
		public void onEmailMouseEnter() {
			label_3.setTextFill(javafx.scene.paint.Color.GOLDENROD);
		}
		public void onEmailMouseExit() {
			label_3.setTextFill(javafx.scene.paint.Color.BLACK);
		}
		public void onWebsiteMouseEnter() {
			label_4.setTextFill(javafx.scene.paint.Color.GOLDENROD);
		}
		public void onWebsiteMouseExit() {
			label_4.setTextFill(javafx.scene.paint.Color.BLACK);
		}
		
		public void onExport() {
			this.bufferImage = new BufferedImage((int)cardPane.getPrefWidth(), (int)cardPane.getPrefHeight(), BufferedImage.TYPE_INT_RGB);
			Graphics2D g2 = bufferImage.createGraphics();
			
			//draw full white rect
			g2.setColor(new java.awt.Color(255,255,255));
			g2.fillRect(0, 0, (int)cardPane.getPrefWidth(), (int)cardPane.getPrefHeight());
			
			//draw bgBorder
			g2.setColor(new java.awt.Color((int)(bgColor.getRed() * 255.0),(int)(bgColor.getGreen()* 255.0),(int)(bgColor.getBlue()* 255.0)));
			g2.fillRect(0, 0, (int)bg_line.getWidth(), (int)bg_line.getHeight());
			
			//draw bg image
			if (this.bgImageCoords == null) {
				Image image = bg_image.getImage();
	            this.setBgFillCoords(image);
			}
			g2.drawImage(SwingFXUtils.fromFXImage(this.bg_image.getImage(), null), 
					(int)bg_image.getLayoutX(), 
					(int)bg_image.getLayoutY(), 
					(int)bg_image.getFitWidth(), 
					(int)bg_image.getFitHeight(), 
					(int)this.bgImageCoords[0], 
					(int)this.bgImageCoords[1], 
					(int)(bg_image.getImage().getWidth() - this.bgImageCoords[0]), 
					(int)(bg_image.getImage().getHeight() - this.bgImageCoords[1]), 
					null);
			
			//draw profileBorder
			g2.setColor(new java.awt.Color((int)(idColor.getRed() * 255.0),(int)(idColor.getGreen()* 255.0),(int)(idColor.getBlue()* 255.0)));
			g2.fillRect((int)this.id_border.getLayoutX(), (int)this.id_border.getLayoutY(), (int)this.id_border.getWidth(), (int)this.id_border.getHeight());
			
			//draw id image
			if (this.idImageCoords == null) {
				Image image = id_image.getImage();
	            this.setIdFillCoords(image);
			}
			g2.drawImage(SwingFXUtils.fromFXImage(this.id_image.getImage(), null), 
					(int)id_image.getLayoutX(), 
					(int)id_image.getLayoutY(), 
					(int)(id_image.getLayoutX() + id_image.getFitWidth()), 
					(int)(id_image.getLayoutY() + id_image.getFitHeight()), 
					(int)this.idImageCoords[0], 
					(int)this.idImageCoords[1], 
					(int)(id_image.getImage().getWidth() - this.idImageCoords[0]), 
					(int)(id_image.getImage().getHeight() - this.idImageCoords[1]), 
					null);
			
			//draw text
			g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			g2.setColor(new java.awt.Color(0,0,0));
			g2.setFont(new Font("SansSerif", Font.PLAIN, 24));
			drawCenteredString(g2, label_0, g2.getFont());
			g2.setFont(new Font("SansSerif", Font.PLAIN, 20));
			drawCenteredString(g2, label_1, g2.getFont());
			drawCenteredString(g2, label_2, g2.getFont());
			drawCenteredString(g2, label_3, g2.getFont());
			drawCenteredString(g2, label_4, g2.getFont());
			
			FileChooser fileChooser = new FileChooser();
	        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
	        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
	        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG); 
	        File file = fileChooser.showSaveDialog(null); 
	        try {
	            //ImageIO.write(bufferImage, "jpg", file);
	        	String extension = getFileExtension(file);
	        	System.out.println(extension);
	        	
	        	// Higher Quality JPEG Saving
	        	if (extension.contentEquals("JPG")){
	        		ImageWriter iw = ImageIO.getImageWritersByFormatName("jpg").next();
		        	iw.setOutput(new FileImageOutputStream(file));

		        	// Set the compression quality to 0.9f.
		        	ImageWriteParam iwParam = iw.getDefaultWriteParam();
		        	iwParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		        	iwParam.setCompressionQuality(.999f);
		        	
		        	iw.write(null, new IIOImage(bufferImage, null, null), iwParam);
	        	}
	        	else {
	        		ImageWriter iw = ImageIO.getImageWritersByFormatName("png").next();
	        		iw.setOutput(new FileImageOutputStream(file));
		        	iw.write(new IIOImage(bufferImage, null, null));
	        	}
	        	
	         } catch (IOException e) {
	            e.printStackTrace();
	         }
		}
		private String getFileExtension(File file) {
	        String fileName = file.getName();
	        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
	        return fileName.substring(fileName.lastIndexOf(".")+1);
	        else return "";
	    }
		public void drawCenteredString(Graphics2D g2, Label label, Font font) {
		    FontMetrics metrics = g2.getFontMetrics(font);
		    int x = (int)(label.getLayoutX() + (label.getWidth() - metrics.stringWidth(label.getText())) / 2);
		    int y = (int)(label.getLayoutY() + (label.getHeight() - metrics.getHeight() / 2) + metrics.getAscent());
		    g2.drawString(label.getText(), x, y);
		}
}
