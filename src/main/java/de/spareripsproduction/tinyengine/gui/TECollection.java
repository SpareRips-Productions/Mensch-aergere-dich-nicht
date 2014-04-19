package de.spareripsproduction.tinyengine.gui;

import java.awt.*;
import java.util.ArrayList;

/**
 * Collection of Views
 *
 * @author Thomas Hampe <thomas@hampe.co>
 * @version 1.0
 * @since 2014-03-22
 */
public class TECollection extends TEView {

    protected ArrayList<TEView> collection;

    protected int margin;

    /**
     * Default constructor
     *
     * @param x x-coordinate
     * @param y y-coordinate
     * @param margin margin between the views
     */
    public TECollection(int x, int y, int margin) {
        super(x, y);
        this.margin = margin;
        this.collection = new ArrayList<TEView>();
    }

    /**
     * Adds a TEView to the collection
     *
     * @param view View to be added
     */
    public void addView(TEView view) {
        this.collection.add(view);
    }

    /**
     * Removes a view form the collection
     *
     * @param view View to be removed
     */
    public void removeView(TEView view) {
        this.collection.remove(view);
    }

    /**
     * Empty the collection
     *
     */
    public void clearCollection() {
        this.collection.clear();
    }

    /**
     * Get a view by index
     *
     * @param index of the view in the collection
     * @return the View at the index
     */
    public TEView get(int index) {
        return this.collection.get(index);
    }

    public ArrayList<TEView> getCollection() {
        return this.collection;
    }

    /**
     * {@inheritDoc}
     */
    public void render(Graphics2D context) {
        for (TEView view : this.collection) {
            view.render(context);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void update() {
        for (TEView view : this.collection) {
            view.update();
        }
    }
}
