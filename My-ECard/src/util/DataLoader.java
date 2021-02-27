package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import model.CardData;

public class DataLoader {
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
