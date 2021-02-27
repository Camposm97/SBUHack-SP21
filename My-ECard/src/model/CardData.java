package model;

import java.io.Serializable;

public class CardData implements Serializable {
	
	private String name;
	private String message;
	private String email;
	private String address;
	private String phone;
	private String website;
	
	private String filePath;
	
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
				+ ", phone=" + phone + ", website=" + website + ", filePath=" + filePath + "]";
	}
}
