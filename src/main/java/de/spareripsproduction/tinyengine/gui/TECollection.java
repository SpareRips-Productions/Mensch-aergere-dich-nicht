package de.spareripsproduction.tinyengine.gui;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author Thomas Hampe <thomas@hampe.co>
 * @version 1.0
 * @since 2014-03-22
 */
public class TECollection extends TEView {

    protected ArrayList<TEView> collection;

    protected int margin;

    public TECollection(int x, int y, int margin) {
        super(x, y);
        this.margin = margin;
        this.collection = new ArrayList<TEView>();
    }

    public void addView(TEView view) {
        this.collection.add(view);
    }

    public void removeView(TEView view) {
        this.collection.remove(view);
    }

    public void clearCollection() {
        this.collection.clear();
    }

    public TEView get(int index) {
        return this.collection.get(index);
    }

    public ArrayList<TEView> getCollection() {
        return this.collection;
    }

    public void render(Graphics2D context) {
        for (TEView view : this.collection) {
            view.render(context);
        }
    }

    public void update() {
        for (TEView view : this.collection) {
            view.update();
        }
    }
}
