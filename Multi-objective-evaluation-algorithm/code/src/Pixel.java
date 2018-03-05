package src;

import java.awt.*;

public class Pixel {
    private int row;
    private int col;
    private int red;
    private int green;
    private int blue;

    public Pixel(int row, int col, Color color) {
        this.row = row;
        this.col = col;
        this.blue = color.getBlue();
        this.green = color.getGreen();
        this.red = color.getRed();
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    public int getRed() {
        return this.red;
    }

    public int getGreen() {
        return this.green;
    }

    public int getBlue() {
        return this.blue;
    }

}