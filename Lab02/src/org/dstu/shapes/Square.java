package org.dstu.shapes;
import java.awt.*;

public class Square extends Shape {
    protected int height;
    protected int width;


    public Square() {
    }

    public Square(int x, int y, int height, int width, Color color) {
        super(x, y, color);
        this.height = height;
        this.width = width;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.drawRect(x, y, width, height);
        g.fillRect(x, y, width, height);
    }
}
