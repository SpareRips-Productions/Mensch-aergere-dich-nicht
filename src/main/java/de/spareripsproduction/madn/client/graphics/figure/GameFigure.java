
package de.spareripsproduction.madn.client.graphics.figure;

import de.spareripsproduction.madn.client.graphics.Board;
import de.spareripsproduction.madn.client.graphics.BoardEntity;
import de.spareripsproduction.madn.client.graphics.RenderAndUpdateable;
import de.spareripsproduction.madn.client.logic.Player;
import de.spareripsproduction.tinyengine.graphics.Sprite;
import de.spareripsproduction.tinyengine.graphics.SpriteStore;

import java.awt.*;

/**
 * Created by marian on 12/03/14.
 */
public abstract class GameFigure extends BoardEntity implements RenderAndUpdateable {

    public static final String SPRITE_RED = "sprites/figureRed";
    public static final String SPRITE_YELLOW = "sprites/figureYellow";
    public static final String SPRITE_BLUE = "sprites/figureBlue";
    public static final String SPRITE_GREEN = "sprites/figureGreen";

    public static final int IN_HOUSE_ID = -1;

    protected int index;

    protected Sprite hoverSprite;

    protected Player owner;

    /**
     * @param spriteRef sprite of the figure
     * @param id        position on the field
     * @param index     figure index 0 to 3
     */
    public GameFigure(String spriteRef, int id, int index) {
        super(spriteRef + ".png", id);
        this.index = index;
        this.hoverSprite = SpriteStore.getInstance().get(spriteRef + "Hover.png");
    }

    @Override
    public float getY() {
        float y = super.getY();
        y -= this.getHeight() - FIELD_SIZE;
        return y;
    }

    public void kick() {
        super.setId(IN_HOUSE_ID);
    }

    public boolean move(int delta, boolean dryRun) {
        if (delta == 0) {
            return false;
        }
        boolean result = true;
        if (delta > 6 || delta < 0) {
            System.out.println("Cheat attempt");
            System.exit(0);
        }

        //move out
        if (getId() < 0 && delta == 6) {
            if (!dryRun) setId(getStartId());
            return true;
            //move on board
        } else if (getId() >= 0 && getId() < 40) {
            int tmp = (getId() + delta) % 40;
            // go home
            if (tmp >= getStartId() && ((getId() < getStartId()) || (getStartId() == 0 && (getId() + delta) >= 40) /* hacky solution for red */)) {
                if (tmp - getStartId() < 4) {
                    tmp = getHomeStartId() + tmp - getStartId();
                    if (isFieldOccupied(tmp)) {
                        return false;
                    }
                    if (!dryRun) setId(tmp);
                    return true;
                }
            } else {
                if (isFieldOccupied(tmp)) {
                    return false;
                }
                if (!dryRun) setId(tmp);
                return true;
            }
            //move in home
        } else if (getId() >= 40 && getId() + delta < getHomeStartId() + 4) {
            int tmp = getId() + delta;
            if (isFieldOccupied(tmp)) {
                return false;
            }
            if (!dryRun) setId(tmp);
        }

        return false;
    }

    public boolean move(int delta) {
        return move(delta, false);
    }

    public boolean isFieldOccupied(int id) {
        for (GameFigure gameFigure : this.getOwner().getGameFigures()) {
            if (gameFigure.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public boolean canMove(int delta) {
        return isActive() && move(delta, true);
    }

    protected abstract int getStartId();

    protected abstract int getHomeStartId();

    protected abstract int getPlayerType();

    @Override
    public void render(Graphics2D g) {
        if (isHover() && canMove(Board.getInstance().getDice().getLastNumber())) {
            hoverSprite.render(g, this.getIntX(), this.getIntY());
        } else {
            super.render(g);
        }
    }

    @Override
    protected void setId(int id) {

        //kick figures on the same place
        if (id > 0) {
            for (GameFigure gameFigure : Board.getInstance().getGameFigures()) {
                if (gameFigure.getId() == id) {
                    gameFigure.kick();
                }
            }
        }

        super.setId(id);
    }

    protected Player getOwner() {
        if (owner == null) {
            for (Player player : Board.getInstance().getPlayers()) {
                if (player.getType() == getPlayerType()) {
                    owner = player;
                    break;
                }
            }
        }
        return owner;
    }

    protected boolean isActive() {
        return this.getOwner().isActive();
    }
}
