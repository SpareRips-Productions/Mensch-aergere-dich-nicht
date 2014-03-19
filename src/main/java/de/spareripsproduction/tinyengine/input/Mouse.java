package de.spareripsproduction.tinyengine.input;

import java.awt.*;

/**
 * @author Thomas Hampe <thomas@hampe.co>
 * @version 1.0
 * @since 2014-03-12
 */
public class Mouse {

    public static Point getLocation() {
        return MouseInfo.getPointerInfo().getLocation();
    }

    public static int getX() {
        return (int) getLocation().getX();
    }

    public static int getY() {
        return (int) getLocation().getY();
    }

    public static void drawLocation(Graphics context) {
        String position = String.format("Mouse: %d, %d", Mouse.getX(), Mouse.getY());
        int bottomY = de.spareripsproduction.tinyengine.Window.singleton().getHeight();

        Color color = context.getColor();
        context.setColor(Color.green);
        context.drawString(position, 1, bottomY-3);

        context.setColor(color);
    }

}
