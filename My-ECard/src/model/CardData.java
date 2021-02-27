package model;

import java.io.Serializable;

public class CardData implements Serializable {
	private static final long serialVersionUID = 1L;
	private ImageData idImage;
	private ImageData backgroundImage;
	private String name;
	private String message;
	private String email;
	private String address;
	private String phone;
	private String website;
	
	public CardData() {
		
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

	@Override
	public String toString() {
		return "CardData [name=" + name + ", message=" + message + ", email=" + email + ", address=" + address
				+ ", phone=" + phone + ", website=" + website + "]";
	}
}
