package controller;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AppController {
	@FXML
	TextField tfName;
	
	public void importAsset(ActionEvent event) {
		FileChooser fc = new FileChooser();
		File file = fc.showOpenDialog(new Stage());
		System.out.println(file);
	}
}
