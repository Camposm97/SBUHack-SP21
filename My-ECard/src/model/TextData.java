package model;

import java.io.Serializable;

public class TextData implements Serializable {
    private String text;
    private String fontFamily;
    private double fontSize;
    private Point position;

    public TextData(TextBox textBox) {
        this.text = textBox.getLabel().getText();
        this.fontFamily = textBox.getLabel().getFont().getName();
        this.fontSize = textBox.getLabel().getFont().getSize();
        this.position = new Point(textBox.getLabel().getTranslateX(), textBox.getLabel().getTranslateY());
    }

    public TextData(String text, String fontFamily, double fontSize, Point position) {
        this.text = text;
        this.fontFamily = fontFamily;
        this.fontSize = fontSize;
        this.position = position;
    }

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

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }
}
