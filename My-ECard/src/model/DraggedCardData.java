package model;

import workbench.ImageViewBox;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class DraggedCardData implements Serializable {
    private List<TextData> textDataList;
    private List<DraggedImageData> imageDataList;

    public DraggedCardData() {
        textDataList = new LinkedList<>();
        imageDataList = new LinkedList<>();
    }

    public void addText(TextData textData) {
        System.out.println(textDataList.add(textData));
    }

    public void addImage(DraggedImageData imageData) {
        System.out.println(imageDataList.add(imageData));
    }

    public void removeText(TextData textData) {
        System.out.println(textDataList.remove(textData));
    }

    public void removeImage(DraggedImageData imageData){
        System.out.println(imageDataList.remove(imageData));
    }
}
