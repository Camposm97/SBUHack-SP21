package workbench;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SaveImageData {
	public static void main(String[] args) {
		imageIoWrite();
	}
	
	public static void imageIoWrite() {
        try {
            File initialImage = new File("resources/prof.jpg");
            BufferedImage bImage = ImageIO.read(initialImage);
            File fileOut = new File("image.dat");
            ImageIO.write(bImage, "jpg", new File("image.dat"));
            FileOutputStream fos = new FileOutputStream(fileOut);
//            fos.write
//            fos.close();
        } catch (IOException e) {
              System.out.println("Exception occured :" + e.getMessage());
        }
        System.out.println("Images were written succesfully.");
   }
}
