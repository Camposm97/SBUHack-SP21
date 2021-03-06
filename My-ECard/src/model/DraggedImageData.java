package model;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import workbench.ImageViewBox;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.Arrays;

public class DraggedImageData implements Serializable {
    private byte[] imageData;
    private Point position;

    public DraggedImageData(ImageViewBox imageViewBox) {
        try {
            final String FILE_TYPE = imageViewBox.getImageType().toString().toLowerCase();
            BufferedImage bi = SwingFXUtils.fromFXImage(imageViewBox.getImageView().getImage(), null);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bi, FILE_TYPE, baos);
            this.imageData = baos.toByteArray();
            this.position = new Point(imageViewBox.getImageView().getTranslateX(), imageViewBox.getImageView().getTranslateY());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DraggedImageData(byte[] imageData, Point position) {
        this.imageData = imageData;
        this.position = position;
    }

    public byte[] getBytes() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public void display(DraggedCardData cardData, Group group) {
        ImageViewBox imageViewBox = new ImageViewBox(cardData, this, group);
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return "DraggedImageData{" +
                "imageData=" + imageData +
                ", position=" + position +
                '}';
    }
}
