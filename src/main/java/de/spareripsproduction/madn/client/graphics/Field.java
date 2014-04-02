package de.spareripsproduction.madn.client.graphics;

import java.awt.*;

/**
 * Created by marian on 12/03/14.
 */
public abstract class Field implements RenderAndUpdateable {
    public Point location;
    public GameFigure gameFigure;

    public Field(int x, int y) {
        this.location = new Point(x, y);
    }
}
