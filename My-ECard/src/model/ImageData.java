package model;

import java.io.Serializable;
import java.util.Arrays;

public class ImageData implements Serializable {
	private ImageType imageType;
	private byte[] bytes;
	
	public ImageData(ImageType imageType, byte[] bytes) {
		this.imageType = imageType;
		this.bytes = bytes;
	}
	
	public ImageType getImageType() {
		return imageType;
	}
	
	public byte[] getBytes() {
		return bytes;
	}

	@Override
	public String toString() {
		return "ImageData [imageType=" + imageType + ", bytes=" + Arrays.toString(bytes) + "]";
	}
}
