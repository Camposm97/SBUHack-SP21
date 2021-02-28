package model;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.io.Serializable;

public class TextData implements Serializable {
    private String text;
    private String fontFamily;
    private double fontSize;
    private String hexColor;
    private Point position;

    public TextData(TextBox textBox) {
        this.text = textBox.getLabel().getText();
        this.fontFamily = textBox.getLabel().getFont().getName();
        this.fontSize = textBox.getLabel().getFont().getSize();
        this.hexColor = "#" + Integer.toHexString(textBox.getLabel().getTextFill().hashCode());
        this.position = new Point(textBox.getLabel().getTranslateX(), textBox.getLabel().getTranslateY());
    }

//    public TextData(String text, String fontFamily, double fontSize, Point position) {
//        this.text = text;
//        this.fontFamily = fontFamily;
//        this.fontSize = fontSize;
//        this.position = position;
//    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFontFamily() {
        return fontFamily;
    }

    public void setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
    }

    public double getFontSize() {
        return fontSize;
    }

    public void setFontSize(double fontSize) {
        this.fontSize = fontSize;
    }

    public String getHexColor() {
        return hexColor;
    }

    public void setHexColor(Label label) {
        this.hexColor = "#" + Integer.toHexString(label.getTextFill().hashCode());
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public void display(Group group) {
        TextBox textBox = new TextBox(this, group);
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return "TextData{" +
                "text='" + text + '\'' +
                ", fontFamily='" + fontFamily + '\'' +
                ", fontSize=" + fontSize +
                ", position=" + position +
                '}';
    }
}
