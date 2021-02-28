package model;

import java.io.Serializable;

public class DraggedImageData implements Serializable {
    private String name;
    private byte[] imageData;
    private Point position;

    public DraggedImageData(String name, byte[] imageData, Point position) {
        this.name = name;
        this.imageData = imageData;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImageData() {
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
}
