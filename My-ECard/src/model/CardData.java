package model;

import java.awt.Font;
import java.io.Serializable;

import javafx.scene.paint.Color;

public class CardData implements Serializable {
	private static final long serialVersionUID = 9107403793011513398L;
	
	private ImageData idImage;
	private double[] bgColor;
	private double[] bgImageCoords;
	private ImageData backgroundImage;
	private double[] idColor;
	private double[] idImageCoords;
	private int idRotation;
	private String fontName;
	private int fontSize;
	private double[] fontColor;
	private double xTextOffset;
	private double yTextOffset;
	private String name;
	private String message;
	private String email;
	private String address;
	private String phone;
	private String website;
	
	public CardData() {
		this.idImage = null;
		this.backgroundImage = null;
		Color blue = Color.DODGERBLUE;
		double[] color = {blue.getRed(), blue.getGreen(), blue.getBlue(), blue.getOpacity()};
		this.idColor = color;
		double[] color2 = {0,0,0,1};
		this.bgColor = color2;
		this.name = "";
		this.message = "";
		this.email = "";
		this.address = "";
		this.phone = "";
		this.website = "";
		this.idRotation = 0;
		this.fontSize = 25;
		double[] color3 = {0,0,0,1};
		this.fontName = "SansSerif";
		this.fontColor = color3;
		this.xTextOffset = 0;
		this.yTextOffset = 0;
	}
	
	public CardData(ImageData idImage, ImageData backgroundImage, String name, String message, String email,
			String address, String phone, String website) {
		super();
		this.idImage = idImage;
		this.backgroundImage = backgroundImage;
		this.name = name;
		this.message = message;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.website = website;
	}

	public ImageData getIdImage() {
		return idImage;
	}

	public void setIdImage(ImageData idImage) {
		this.idImage = idImage;
	}

	public ImageData getBackgroundImage() {
		return backgroundImage;
	}

	public void setBackgroundImage(ImageData backgroundImage) {
		this.backgroundImage = backgroundImage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public double[] getBgColor() {
		return bgColor;
	}

	public void setBgColor(double[] bgColor) {
		this.bgColor = bgColor;
	}

	public double[] getBgImageCoords() {
		return bgImageCoords;
	}

	public void setBgImageCoords(double[] bgImageCoords) {
		this.bgImageCoords = bgImageCoords;
	}

	public double[] getIdColor() {
		return idColor;
	}

	public void setIdColor(double[] idColor) {
		this.idColor = idColor;
	}

	public double[] getIdImageCoords() {
		return idImageCoords;
	}

	public void setIdImageCoords(double[] idImageCoords) {
		this.idImageCoords = idImageCoords;
	}

	public int getIdRotation() {
		return idRotation;
	}

	public void setIdRotation(int idRotation) {
		this.idRotation = idRotation;
	}

	public String getFontName() {
		return fontName;
	}

	public void setFontName(String fontName) {
		this.fontName = fontName;
	}

	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	public double[] getFontColor() {
		return fontColor;
	}

	public void setFontColor(double[] fontColor) {
		this.fontColor = fontColor;
	}

	public double getxTextOffset() {
		return xTextOffset;
	}

	public void setxTextOffset(double xTextOffset) {
		this.xTextOffset = xTextOffset;
	}

	public double getyTextOffset() {
		return yTextOffset;
	}

	public void setyTextOffset(double yTextOffset) {
		this.yTextOffset = yTextOffset;
	}

	@Override
	public String toString() {
		return "CardData [name=" + name + ", message=" + message + ", email=" + email + ", address=" + address
				+ ", phone=" + phone + ", website=" + website + "]";
	}
}
