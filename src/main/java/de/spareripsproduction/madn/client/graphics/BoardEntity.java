package de.spareripsproduction.madn.client.graphics;

import de.spareripsproduction.tinyengine.entity.Entity;
import de.spareripsproduction.tinyengine.input.Mouse;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * @author Thomas Hampe <thomas@hampe.co>
 * @version 1.0
 * @since 2014-04-13
 */
public class BoardEntity extends Entity {

    /**
     * Field Size in px
     */
    public static final int FIELD_SIZE = 48;

    protected int id;

    protected Point boardPosition;

    protected boolean hover, clicked;

    /**
     *
     * @param spriteRef path to a sprite image
     * @param id id-position of the board
     */
    public BoardEntity(String spriteRef, int id) {
        super(spriteRef, 0, 0);
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getX() {
        Point p = getBoardPosition();
        int width = (Board.getInstance().getIntWidth() - 11 * FIELD_SIZE) / 2;
        return Board.getInstance().getIntX() + p.x * FIELD_SIZE + width;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getY() {
        Point p = getBoardPosition();
        int width = (Board.getInstance().getIntHeight() - 11 * FIELD_SIZE) / 2;
        return Board.getInstance().getIntY() + p.y * FIELD_SIZE + width;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        super.update();
        this.hover = this.insideView(Mouse.location());
        if (Mouse.isClicked(MouseEvent.BUTTON1) && isHover()) {
            this.clicked = true;
        } else {
            this.clicked = false;
        }

    }

    /**
     *
     * @return the position of this entity, in a matrix of fields
     */
    public Point getBoardPosition() {
        if (boardPosition == null) {
            boardPosition = updateBoardPosition();
        }
        return boardPosition;
    }

    /**
     * Every accessible Field on the board has a unique id
     *
     * @param id id-position
     */
    protected void setId(int id) {
        this.id = id;
        this.boardPosition = updateBoardPosition();
    }

    /**
     * Every accessible Field on the board has a unique id
     *
     * @return id-position of the this entity
     */
    public int getId() {
        return id;
    }

    protected Point updateBoardPosition() {
        int corner = id / 10;
        int positionInCorner = id - (10 * corner);
        int x = 0, y = 0;
        switch (corner) {
            case 0:
                if (positionInCorner <= 4) {
                    x = positionInCorner;
                    y = 4;
                } else if (positionInCorner <= 8) {
                    x = 4;
                    y = 4 - positionInCorner + 4;
                } else {
                    x = 5;
                    y = 0;
                }
                break;
            case 1:
                if (positionInCorner <= 4) {
                    x = 6;
                    y = positionInCorner;
                } else if (positionInCorner <= 8) {
                    x = 6 + positionInCorner - 4;
                    y = 4;
                } else {
                    x = 10;
                    y = 5;
                }
                break;
            case 2:
                if (positionInCorner <= 4) {
                    x = 10 - positionInCorner;
                    y = 6;
                } else if (positionInCorner <= 8) {
                    x = 6;
                    y = 6 + positionInCorner - 4;
                } else {
                    x = 5;
                    y = 10;
                }

                break;
            case 3:
                if (positionInCorner <= 4) {
                    x = 4;
                    y = 10 - positionInCorner;
                } else if (positionInCorner <= 8) {
                    x = 4 - positionInCorner + 4;
                    y = 6;
                } else {
                    x = 0;
                    y = 5;
                }
                break;
            case 4:
                //home fields blue
                x = positionInCorner + 1;
                y = 5;
                break;

            case 5:
                //home fields red
                x = 5;
                y = positionInCorner + 1;
                break;
            case 6:
                //home fields green
                x = 9 - positionInCorner;
                y = 5;

                break;
            case 7:
                //home fields yellow
                x = 5;
                y = 9 - positionInCorner;
                break;
        }

        return new Point(x, y);
    }

    /**
     *
     * @return true if the entity is hovered by the mouse
     */
    protected boolean isHover() {
        return hover;
    }

    /**
     *
     * @return true if this entity is clicked
     */
    public boolean isClicked() {
        return clicked;
    }

    protected boolean insideView(Point p) {
        return (p.getX() >= this.getX() && p.getX() <= this.getX() + this.getWidth())
                && (p.getY() >= this.getY() && p.getY() <= this.getY() + this.getHeight());
    }


}
