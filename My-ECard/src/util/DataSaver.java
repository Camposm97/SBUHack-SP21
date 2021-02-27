package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import javafx.stage.FileChooser;
import model.CardData;

public class DataSaver {
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
}
