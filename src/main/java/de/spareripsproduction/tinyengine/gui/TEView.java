package de.spareripsproduction.tinyengine.gui;

import de.spareripsproduction.tinyengine.graphics.RenderInterface;
import de.spareripsproduction.tinyengine.logic.UpdateInterface;

import java.awt.*;

/**
 * Base Class for all views
 *
 * @author Thomas Hampe <thomas@hampe.co>
 * @version 1.0
 * @since 2014-03-21
 */
public class TEView implements RenderInterface, UpdateInterface {

    private int x, y;

    private int width, height;

    /**
     * Default Constructor
     *
     * @param x X-Coordinate
     * @param y Y-Coordinate
     * @param width in pixel
     * @param height in pixel
     */
    public TEView(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Constructor for views with no height an width
     *
     * @param x X-Coordinate
     * @param y Y-Coordinate
     */
    public TEView(int x, int y) {
        this(x, y, 0, 0);
    }

    /**
     * Current x-coordinate
     *
     * @return x-coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * current y-coordinate
     *
     * @return y-coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Current width
     *
     * @return width in pixel
     */
    public int getWidth() {
        return width;
    }

    /**
     * Current height
     *
     * @return height in pixel
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets the x-coordinate
     *
     * @param x x-coordinate
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Sets the y-coordinate
     *
     * @param y y-coordinate
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Sets the width
     *
     * @param width in pixel
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Sets the height
     *
     * @param height  in px
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * {@inheritDoc}
     */
    public void render(Graphics2D context) {

    }

    /**
     * {@inheritDoc}
     */
    public void update() {

    }


    /**
     * Aligns the View vertical between two coordinates
     *
     * @param x1 top coordinate
     * @param x2 bottom coordinate
     */
    public void verticalAlignCenter(int x1, int x2) {
        this.setX(x1 + (x2 - x1) / 2 - this.getWidth() / 2);
    }

    /**
     * Aligns the View horizontal right on
     *
     * @param x right coordinate
     */
    public void verticalAlignRight(int x) {
        this.setX(x - this.getWidth());
    }

    /**
     * Aligns the View horizontal between two coordinates
     *
     * @param y1 left coordinate
     * @param y2 right coordinate
     */
    public void horizontalAlignCenter(int y1, int y2) {
        this.setY(y1 + (y2 - y1) / 2 - this.getHeight() / 2);
    }

    /**
     * Checks if a point is inside this view
     *
     * @param p Point
     * @return obvious
     */
    protected boolean insideView(Point p) {
        return (p.getX() >= this.getX() && p.getX() <= this.getX() + this.getWidth())
                && (p.getY() >= this.getY() && p.getY() <= this.getY() + this.getHeight());
    }
}
