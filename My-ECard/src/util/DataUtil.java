package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javafx.stage.FileChooser;
import model.CardData;

public class DataUtil {
	public static void saveAs(CardData cardData) {
		FileChooser fc = new FileChooser();
	}
	
	public static void save(CardData cardData, String src) {
		File file = new File(src);
		try {
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(cardData);
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static CardData load(String src) {
		File file = new File(src);
		try {
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			CardData cardData = (CardData) ois.readObject();
			ois.close();
			System.out.println("Successfully loaded from " + file);
			return cardData;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed to load from " + file);
			return null;
		}
	}
}
