
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

    /**
     * Reference to the sprite image of the red <code>GameFigure</code>
     */
    public static final String SPRITE_RED = "sprites/figureRed";

    /**
     * Reference to the sprite image of the yellow <code>GameFigure</code>
     */
    public static final String SPRITE_YELLOW = "sprites/figureYellow";

    /**
     * Reference to the sprite image of the red <code>GameFigure</code>
     */
    public static final String SPRITE_BLUE = "sprites/figureBlue";

    /**
     * Reference to the sprite image of the green <code>GameFigure</code>
     */
    public static final String SPRITE_GREEN = "sprites/figureGreen";

    /**
     * id-position for GameFigures in house
     */
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
        y -= this.getHeight() - FIELD_SIZE + 5;
        return y;
    }

    /**
     * Gamefigure gets place into the beginning house
     */
    public void kick() {
        super.setId(IN_HOUSE_ID);
    }


    protected boolean move(int delta, boolean dryRun) {
        if (delta == 0) {
            return false;
        }
        if (delta > 6 || delta < 0) {
            System.out.println("Cheat attempt");
            System.exit(0);
        }

        //clean Start ID Field always
        if(getId() != getStartId() && isStartIdOccupied()) {
            GameFigure gameFigure = null;
            for(GameFigure gf : getOwner().getGameFigures()) {
                if(gf.getId() == getStartId()) {
                    gameFigure = gf;
                    break;
                }
            }
            if(gameFigure != null && gameFigure.canMove(delta)) {
                return false;
            }
        }

        //force move when ever possible (let only)
        if(delta == 6 && getId() != IN_HOUSE_ID && !isStartIdOccupied()) {
            for(GameFigure gf : getOwner().getGameFigures()) {
                if(gf.getId() == IN_HOUSE_ID) {
                    return false;
                }
            }
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
            return true;
        }

        return false;
    }

    /**
     *
     * @param delta number of fields to move (1-6)
     * @return true if move is successful, false if not
     */
    public boolean move(int delta) {
        return move(delta, false);
    }

    /**
     * Checks if Field is occupied by <code>GameFigure</code> from the same Owner
     * @param id id-position
     * @return true if the field is occupied, false if not
     */
    public boolean isFieldOccupied(int id) {
        for (GameFigure gameFigure : this.getOwner().getGameFigures()) {
            if (gameFigure.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public boolean isStartIdOccupied() {
        return isFieldOccupied(getStartId());
    }

    /**
     *
     * @param delta number of fields to move (1-6)
     * @return true if <code>GameFigure</code> can make the move of <code>delta</code> steps, false if not
     */
    public boolean canMove(int delta) {
        return isActive() && move(delta, true);
    }

    protected abstract int getStartId();

    public abstract int getHomeStartId();

    protected abstract int getPlayerType();

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(Graphics2D g) {
        if (isHover() && canMove(Board.getInstance().getDice().getLastNumber())) {
            hoverSprite.render(g, this.getIntX(), this.getIntY());
        } else {
            super.render(g);
        }
    }

    /**
     * {@inheritDoc}
     */
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
