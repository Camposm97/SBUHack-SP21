package controller;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;

import javafx.application.Platform;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import util.DataUtil;
import util.FXUtil;
import javafx.stage.Stage;
import model.CardData;
import model.ImageData;
import model.ImageType;

public class AppController {

	CardData currentCard;
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
	
	@FXML
	HBox bgOptionsContainer;
	@FXML
	HBox idOptionsContainer;
	
	public AppController() {
		currentCard = new CardData();
	}
	
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
			setBgCrop(image);
			updateBgCrop();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(bufferedImage, this.getFileExtension(file), baos);
			byte[] byteArr = baos.toByteArray();
			currentCard.setBackgroundImage(new ImageData(ImageType.Background, byteArr));
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
			this.setIdCrop(image);
			this.updateIdCrop();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(bufferedImage, this.getFileExtension(file), baos);
			byte[] byteArr = baos.toByteArray();
			currentCard.setIdImage(new ImageData(ImageType.ID, byteArr));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void loadFields() {
		label_0.setText(currentCard.getName());
		label_1.setText(currentCard.getAddress());
		label_2.setText(currentCard.getPhone());
		label_3.setText(currentCard.getEmail());
		label_4.setText(currentCard.getWebsite());
	}

	public void setIdCrop(Image image) {
		double unitSize = image.getHeight() / id_image.getFitHeight();
		double[] newIdImageCoords = { (image.getWidth() - (id_image.getFitWidth() * unitSize)) / 2.0, 0,
				id_image.getFitWidth() * unitSize, image.getHeight() };
		currentCard.setIdImageCoords(newIdImageCoords);
	}

	public void setBgCrop(Image image) {
		double unitSize = image.getWidth() / bg_image.getFitWidth();
		double[] newBgImageCoords = { 0, (image.getHeight() - (bg_image.getFitHeight() * unitSize)) / 2.0,
				image.getWidth(), bg_image.getFitHeight() * unitSize };
		currentCard.setBgImageCoords(newBgImageCoords);
	}
	
	public void openFile(ActionEvent event) {
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().add(FXUtil.getDefaultExtFilter());
		File file = fc.showOpenDialog(new Stage());
		if (file != null) {
			currentCard = DataUtil.load(file.getPath());
			if (currentCard != null) {
				loadFields();
				byte[] idImageBytes = currentCard.getIdImage().getBytes();
				InputStream is1 = new ByteArrayInputStream(idImageBytes);
				Image idImage = new Image(is1);
				id_image.setImage(idImage);
				this.setIdCrop(idImage);
				this.updateIdCrop();
				byte[] backgroundImageBytes = currentCard.getBackgroundImage().getBytes();
				InputStream is2 = new ByteArrayInputStream(backgroundImageBytes);
				Image backgroundImage = new Image(is2);
				bg_image.setImage(backgroundImage);
				this.setBgCrop(backgroundImage);
				double[] bgImageCoords = currentCard.getBgImageCoords();
				bg_image.setViewport(new Rectangle2D(bgImageCoords[0], bgImageCoords[1], bgImageCoords[2],
						bgImageCoords[3]));
			}
		}
	}
	
	public void closeFile(ActionEvent event) {
		FXUtil.showWIP(); // TODO
	}
	
	public void saveAs(ActionEvent event) {
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().add(FXUtil.getDefaultExtFilter());
		File file = fc.showSaveDialog(new Stage());
		if (file != null) {
			DataUtil.save(currentCard, file.getPath());
		}
	}
	
	public void save(ActionEvent event) {
		// TODO Needs a path to save to without FileChooser
		FXUtil.showWIP();
	}
	
	public void exitApp(ActionEvent event) {
		Platform.exit();
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
		currentCard.setName(tfName.getText());
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
		currentCard.setAddress(tfAddress.getText());
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
		currentCard.setPhone(tfPhone.getText());
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
		currentCard.setEmail(tfEmail.getText());
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
		currentCard.setWebsite(tfWebsite.getText());
		tfWebsite.setVisible(false);
		btn_4.setVisible(false);
	}

	public void onBgBorderClick() {
		if (bg_colorPicker.isVisible()) {
			bg_colorPicker.setVisible(false);
		} else {
			bg_colorPicker.setVisible(true);
		}
	}

	public void onIdBorderClick() {
		if (id_colorPicker.isVisible()) {
			id_colorPicker.setVisible(false);
		} else {
			id_colorPicker.setVisible(true);
		}
	}

	public void setBgBorderColor() {
		Color bgColor = bg_colorPicker.getValue();
		bg_line.setStroke(bgColor);
		double[] color = {bgColor.getRed(), bgColor.getGreen(), bgColor.getBlue(), bgColor.getOpacity()};
		currentCard.setBgColor(color);
		bg_colorPicker.setVisible(false);
	}

	public void setIdBorderColor() {
		Color idColor = id_colorPicker.getValue();
		id_border.setFill(idColor);
		double[] color = {idColor.getRed(), idColor.getGreen(), idColor.getBlue(), idColor.getOpacity()};
		currentCard.setIdColor(color);
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
		id_border.setFill(new Color(currentCard.getIdColor()[0],currentCard.getIdColor()[1],currentCard.getIdColor()[2],currentCard.getIdColor()[3]));
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
		this.bufferImage = new BufferedImage((int) cardPane.getPrefWidth(), (int) cardPane.getPrefHeight(),
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = bufferImage.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		// draw full white rect
		g2.setColor(new java.awt.Color(255, 255, 255));
		g2.fillRect(0, 0, (int) cardPane.getPrefWidth(), (int) cardPane.getPrefHeight());

		// draw bgBorder
		double[] bgColor= currentCard.getBgColor();
		g2.setColor(new java.awt.Color((int) (bgColor[0] * 255.0), (int) (bgColor[1] * 255.0),
				(int) (bgColor[2] * 255.0)));
		g2.fillRect(0, 0, (int) bg_line.getWidth(), (int) bg_line.getHeight());

		// draw bg image
	
		double[] bgImageCoords = currentCard.getBgImageCoords();
		if (bgImageCoords == null) {
			this.setBgCrop(this.bg_image.getImage());
			bgImageCoords = currentCard.getBgImageCoords();
		}
		g2.drawImage(SwingFXUtils.fromFXImage(this.bg_image.getImage(), null), (int) bg_image.getLayoutX(),
				(int) bg_image.getLayoutY(), (int) bg_image.getFitWidth(), (int) bg_image.getFitHeight(),
				(int) bgImageCoords[0], (int) bgImageCoords[1],
				(int) (bg_image.getImage().getWidth() - bgImageCoords[0]),
				(int) (bg_image.getImage().getHeight() - bgImageCoords[1]), null);

		// draw profileBorder
		double[] idColor= currentCard.getIdColor();
		g2.setColor(new java.awt.Color((int) (idColor[0] * 255.0), (int) (idColor[1]* 255.0),
				(int) (idColor[2] * 255.0)));
		g2.fillRect((int) this.id_border.getLayoutX(), (int) this.id_border.getLayoutY(),
				(int) this.id_border.getWidth(), (int) this.id_border.getHeight());

		// draw id image
		double[] idImageCoords = currentCard.getIdImageCoords();
		if (idImageCoords == null) {
			this.setIdCrop(this.id_image.getImage());
			idImageCoords = currentCard.getIdImageCoords();
		}
		g2.drawImage(SwingFXUtils.fromFXImage(this.id_image.getImage(), null), (int) id_image.getLayoutX(),
				(int) id_image.getLayoutY(), (int) (id_image.getLayoutX() + id_image.getFitWidth()),
				(int) (id_image.getLayoutY() + id_image.getFitHeight()), (int) idImageCoords[0],
				(int) idImageCoords[1], (int) (id_image.getImage().getWidth() - idImageCoords[0]),
				(int) (id_image.getImage().getHeight() - idImageCoords[1]), null);

		// draw text
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setColor(new java.awt.Color(0, 0, 0));
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
		FileChooser.ExtensionFilter extFilterPDF = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.PDF");
		fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG, extFilterPDF);
		File file = fileChooser.showSaveDialog(null);
		try {
			// ImageIO.write(bufferImage, "jpg", file);
			String extension = getFileExtension(file);
			System.out.println(extension);

			// Higher Quality JPEG Saving
			if (extension.contentEquals("JPG")) {
				ImageWriter iw = ImageIO.getImageWritersByFormatName("jpg").next();
				iw.setOutput(new FileImageOutputStream(file));

				// Set the compression quality to 0.9f.
				ImageWriteParam iwParam = iw.getDefaultWriteParam();
				iwParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
				iwParam.setCompressionQuality(.9f);

				iw.write(null, new IIOImage(bufferImage, null, null), iwParam);
			} else if (extension.contentEquals("PDF")) {
				ImageWriter iw = ImageIO.getImageWritersByFormatName("pdf").next();
				iw.setOutput(new FileImageOutputStream(file));
				iw.write(new IIOImage(bufferImage, null, null));
			} else {
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
		if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
			return fileName.substring(fileName.lastIndexOf(".") + 1);
		else
			return "";
	}

	public void drawCenteredString(Graphics2D g2, Label label, Font font) {
		FontMetrics metrics = g2.getFontMetrics(font);
		int x = (int) (label.getLayoutX() + (label.getWidth() - metrics.stringWidth(label.getText())) / 2);
		int y = (int) (label.getLayoutY() + (label.getHeight() - metrics.getHeight() / 2) + metrics.getAscent());
		g2.drawString(label.getText(), x, y);
	}
	public void onBgOptionsClick() {
		if (this.bgOptionsContainer.isVisible()) {
			this.bgOptionsContainer.setVisible(false);
		}
		else {
			this.bgOptionsContainer.setVisible(true);
		}
	}
	public void onIdOptionsClick() {
		if (this.idOptionsContainer.isVisible()) {
			this.idOptionsContainer.setVisible(false);
		}
		else {
			this.idOptionsContainer.setVisible(true);
		}
	}
	public void updateBgCrop() {
		double[] bgImageCoords = currentCard.getBgImageCoords();
		bg_image.setViewport(new Rectangle2D(bgImageCoords[0], bgImageCoords[1], bgImageCoords[2],bgImageCoords[3]));
	}
	public void updateIdCrop() {
		double[] idImageCoords = currentCard.getIdImageCoords();
		id_image.setViewport(new Rectangle2D(idImageCoords[0], idImageCoords[1], idImageCoords[2],idImageCoords[3]));
	}
}
