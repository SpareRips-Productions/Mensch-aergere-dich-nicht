package de.spareripsproduction.tinyengine.gui;

import de.spareripsproduction.tinyengine.graphics.RenderInterface;
import de.spareripsproduction.tinyengine.logic.UpdateInterface;

import java.awt.*;

/**
 * @author Thomas Hampe <thomas@hampe.co>
 * @version 1.0
 * @since 2014-03-21
 */
public class TEView implements RenderInterface, UpdateInterface {

    private int x, y;

    private int width, height;

    public TEView(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public TEView(int x, int y) {
        this(x, y, 0, 0);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void render(Graphics2D context) {

    }

    public void update() {

    }

    public void verticalAlignCenter(int x1, int x2) {
        this.setX(x1 + (x2 - x1) / 2 - this.getWidth() / 2);
    }

    public void verticalAlignRight(int x) {
        this.setX(x - this.getWidth());
    }

    public void horizontalAlignCenter(int y1, int y2) {
        this.setY(y1 + (y2 - y1) / 2 - this.getHeight() / 2);
    }

    protected boolean insideView(Point p) {
        return (p.getX() >= this.getX() && p.getX() <= this.getX() + this.getWidth())
                && (p.getY() >= this.getY() && p.getY() <= this.getY() + this.getHeight());
    }
}
