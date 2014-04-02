
package de.spareripsproduction.madn.client.graphics;

import de.spareripsproduction.madn.client.logic.Player;
import de.spareripsproduction.tinyengine.entity.Entity;
import de.spareripsproduction.tinyengine.input.Mouse;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by marian on 12/03/14.
 */
public class GameFigure extends Entity implements RenderAndUpdateable {

    public static final String COLOR_RED = "red";
    public static final String COLOR_YELLOW = "yellow";
    public static final String COLOR_BLUE = "blue";
    public static final String COLOR_GREEN = "green";

    public Player owner;

    private boolean clickAble;

    private boolean clicked;

    private boolean highlighted;

    public GameFigure(String SpriteRef, int x, int y, Player owner) {
        super(SpriteRef, x, y);
        this.owner = owner;
    }

    @Override
    public void update() {
        if (this.clickAble) {
            // check if clicked inside in mouse
            if (Mouse.isClicked(MouseEvent.BUTTON1)) {
                Point p = Mouse.position(MouseEvent.BUTTON1);
                this.clicked = new Rectangle(this.getIntX(), this.getIntY(), this.getIntWidth(), this.getIntHeight()).contains(p);
            } else {
                this.clicked = false;
            }
            // check if mouse inside button
            this.highlighted = new Rectangle(this.getIntX(), this.getIntY(), this.getIntWidth(), this.getIntHeight()).contains(Mouse.location());
        }
    }

    @Override
    public void render(Graphics2D g) {

    }

    public boolean isClicked() {
        return this.clicked;
    }

    public boolean isHighlighted() {
        return this.highlighted;
    }

    public boolean isClickAble() { return this.clickAble; }

    public void setClickAble(boolean value) { this.clickAble = value; }
}
