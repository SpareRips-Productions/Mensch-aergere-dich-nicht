
package de.spareripsproduction.madn.client.graphics;

import de.spareripsproduction.madn.client.logic.Player;
import de.spareripsproduction.tinyengine.entity.Entity;
import de.spareripsproduction.tinyengine.graphics.Sprite;
import de.spareripsproduction.tinyengine.graphics.SpriteStore;
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

    private Sprite hoverSprite;

    public GameFigure(String SpriteRef, int x, int y, Player owner) {
        super(SpriteRef, x, y);
        this.owner = owner;
        if(SpriteRef.equals(COLOR_RED)) {
            hoverSprite = SpriteStore.getInstance().get("sprites/figureRedHover.png");
        } else if(SpriteRef.equals(COLOR_BLUE)) {
            hoverSprite = SpriteStore.getInstance().get("sprites/figureBlueHover.png");
        } else if(SpriteRef.equals(COLOR_GREEN)) {
            hoverSprite = SpriteStore.getInstance().get("sprites/figureGreenHover.png");
        } else if(SpriteRef.equals(COLOR_YELLOW)) {
            hoverSprite = SpriteStore.getInstance().get("sprites/figureYellowHover.png");
        }
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

    public void render(Graphics2D g) {
        if(isHighlighted()) {
            hoverSprite.render(g, (int) this.getX(), (int) this.getY());
        } else {
            super.render(g);
        }

    }
}
