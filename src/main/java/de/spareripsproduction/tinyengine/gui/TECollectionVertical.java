package de.spareripsproduction.tinyengine.gui;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author Thomas Hampe <thomas@hampe.co>
 * @version 1.0
 * @since 2014-03-22
 */
public class TECollectionVertical extends TECollection{

    public TECollectionVertical(int x, int y, int margin) {
        super(x, y, margin);
    }

    public void addView(TEView view) {
        view.setX(this.getX());
        if(this.getCollection().size() == 0) {
            view.setY(this.getY());
        } else {
            view.setY(this.getY() + this.getHeight() + this.margin);
        }

        super.addView(view);
    }

    public void removeView(TEView view) {
        super.removeView(view);
        this.setY(this.getX());
    }

    public void setX(int x) {
        super.setX(x);
        for(TEView view : this.collection) {
            view.setX(this.getX());
        }
    }

    public void setY(int y) {
        super.setY(y);
        int counter = 0;
        int height = 0;
        for(TEView view : this.collection) {
            if(counter == 0) {
                view.setY(this.getY()+ height);
                counter++;
            }else {
                view.setY(this.getY()+ height);
            }
            height += view.getHeight() + this.margin;
        }
    }

    public void verticalAlignCenter(int x1, int x2) {
        super.verticalAlignCenter(x1, x2);
        for(TEView view : this.collection) {
            view.verticalAlignCenter(x1, x2);
        }
    }

    public void horizontalAlignCenter(int y1,int y2) {
        super.horizontalAlignCenter(y1, y2);
        this.setY(this.getY());
    }

    public void verticalAlignRight(int x) {
        super.verticalAlignRight(x);
        for(TEView view : this.collection) {
            view.verticalAlignRight(x);
        }
    }

    public int getHeight() {
        int height = 0;
        for(TEView view : this.collection) {
            height += view.getHeight();
        }
        height += this.collection.size() == 0 ? 0 : (this.collection.size()-1)*this.margin;
        return height;
    }

    public int getWidth() {
        int width = 0;
        for(TEView view : this.collection) {
            if(view.getWidth() >= width) {
                width = view.getWidth();
            }
        }
        return width;
    }
}
