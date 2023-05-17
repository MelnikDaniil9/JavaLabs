package org.dstu;

import org.dstu.components.GraphicsPanel;
import org.dstu.shapes.Rhombus;
import org.dstu.shapes.Shape;
import org.dstu.shapes.Square;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import static org.dstu.shapes.Shape.createGradient;

public class MainApp {
    public static void main(String[] args) {
        int x = 150;
        int y = 150;
        final int[] height = {50};
        final int[] width = {25};
        final boolean[] flag = {true};
        int maxSize = height[0] * 2;
        int minSize = height[0] / 2;

        List<Shape> shapes = new ArrayList<>();
        shapes.add(new Square(x, y, height[0], width[0], Color.CYAN));
        shapes.add(new Rhombus(x, y, height[0], width[0], Color.RED));

        GraphicsPanel canvas = new GraphicsPanel(shapes);

        Runnable shapeController = () -> {
            while (true) {
                shapes.forEach(shape -> {
                    shape.moveRel((int) (Math.random() * 5), (int) (Math.random() * 5));

                    if (shape instanceof Square) {
                        Color startColor = Color.BLACK;
                        Color endColor = Color.WHITE;
                        float fraction = (float) Math.random();
                        Color newColor = createGradient(startColor, endColor, fraction);
                        shape.setColor(newColor);
                    }

                    if (shape instanceof Rhombus) {
                        Color startColor = Color.GREEN;
                        Color endColor = Color.PINK;
                        float fraction = (float) Math.random();
                        Color newColor = createGradient(startColor, endColor, fraction);
                        shape.setColor(newColor);
                    }
                });

                Square square = (Square) shapes.get(0);
                Rhombus rhombus = (Rhombus) shapes.get(1);
                rhombus.setX(square.getX());
                rhombus.setY(square.getY());

                if (height[0] == maxSize) {
                    flag[0] = false;
                } else if (height[0] == minSize) {
                    flag[0] = true;
                }

                if (!flag[0]) {
                    height[0] -= 1;
                    width[0] -= 1;
                } else {
                    height[0] += 1;
                    width[0] += 1;
                }

                square.setHeight(height[0]);
                square.setWidth(width[0]);
                rhombus.setHeight(height[0]);
                rhombus.setWidth(width[0]);

                canvas.repaint();
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.add(canvas);
        frame.setVisible(true);

        Thread animationThread = new Thread(shapeController);
        animationThread.start();
    }
}
