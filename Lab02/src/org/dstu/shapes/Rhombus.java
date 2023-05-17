package org.dstu.shapes;
import java.awt.*;

public class Rhombus extends Shape {
    protected int height;
    protected int width;

    public Rhombus() {
    }

    public Rhombus(int x, int y, int height, int width, Color color) {
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

        int x1 = x + width / 2;
        int y1 = y;
        int x2 = x + width;
        int y2 = y + height / 2;
        int x3 = x + width / 2;
        int y3 = y + height;
        int x4 = x;
        int y4 = y + height / 2;

        int[] xPoints = { x1, x2, x3, x4 };
        int[] yPoints = { y1, y2, y3, y4 };

        g.drawPolygon(xPoints, yPoints, 4);
        g.fillPolygon(xPoints, yPoints, 4);
    }
}
