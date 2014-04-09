
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

    public static final String COLOR_RED = "sprites/figureRed.png";
    public static final String COLOR_YELLOW = "sprites/figureYellow.png";
    public static final String COLOR_BLUE = "sprites/figureBlue.png";
    public static final String COLOR_GREEN = "sprites/figureGreen.png";

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

    public boolean isClicked() {
        return this.clicked;
    }

    public boolean isHighlighted() {
        return this.highlighted;
    }

    public boolean isClickAble() { return this.clickAble; }

    public void setClickAble(boolean value) { this.clickAble = value; }
}
