package workbench;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.CardData;

public class SaveImageData extends Application {
	private Image image;
	private CardData cardData;
	
	public static void main(String[] args) throws Exception {
//		saveSomeData();
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		Object[] arr = loadSomeData();
		byte[] byteArr = (byte[]) arr[0];
		CardData cardData = (CardData) arr[1];
		System.out.println(cardData.toString());
		InputStream is = new ByteArrayInputStream(byteArr);
		Image image = new Image(is);
		ImageView iv = new ImageView(image);
		stage.setScene(new Scene(new StackPane(iv)));
		stage.show();
	}
	
	public static Object[] loadSomeData() throws Exception {
		File file = new File("out.dat");
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		byte[] byteArr = (byte[]) ois.readObject();
		CardData cardData = (CardData) ois.readObject();
		ois.close();
		return new Object[] {byteArr, cardData};
	}
	
	public static void saveSomeData() throws Exception {
		// Create CardData
		CardData cardData = loadMattGuidi();
		
		// Get Image's Bytes
		byte[] byteArr = loadImageBytes("resources/prof.jpg");
		
		File fileOut = new File("out.dat");
		FileOutputStream fos = new FileOutputStream(fileOut);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(byteArr);
		oos.writeObject(cardData);
		oos.close();
		System.out.println("Done");
	}
	
	/**
	 * Image needs to be JPG format
	 * @param src
	 * @throws IOException 
	 */
	public static byte[] loadImageBytes(String src) throws IOException {
		final String FILE_TYPE = "jpg";
		File file = new File(src);
		BufferedImage bi = ImageIO.read(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(bi, FILE_TYPE, baos);
		byte[] byteArr = baos.toByteArray();
		return byteArr;
	}
	
	@Deprecated
	public static CardData loadMattGuidi() {
		CardData cardData = new CardData();
		cardData.setAddress("123 Right Way Ave. One Town");
		cardData.setEmail("matt.guidi@stonybrook.edu");
		cardData.setMessage("What can I say? Light work.");
		cardData.setPhone("631-356-6789");
		cardData.setName("Matt Guidi");
		cardData.setWebsite("sayhimatt.com");
		return cardData;
	}
	
	@Deprecated
	public static void imageIoWrite() {
		try {
			File initialImage = new File("resources/prof.jpg");
			BufferedImage bImage = ImageIO.read(initialImage);
			File fileOut = new File("image.jpg");
			ImageIO.write(bImage, "jpg", fileOut);
//            FileOutputStream fos = new FileOutputStream(fileOut);
//            fos.write
//            fos.close();
		} catch (IOException e) {
			System.out.println("Exception occured :" + e.getMessage());
		}
		System.out.println("Images were written succesfully.");
	}
}
