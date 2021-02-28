package model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class DraggerCardData implements Serializable {
    private List<TextData> textDataList;
    private List<DraggedImageData> imageDataList;

    public DraggerCardData() {
        textDataList = new LinkedList<>();
        imageDataList = new LinkedList<>();
    }

    public List<TextData> getTextDataList() {
        return textDataList;
    }

    public List<DraggedImageData> getImageDataList() {
        return imageDataList;
    }
}
