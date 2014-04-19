package de.spareripsproduction.madn.client.graphics.figure;

import de.spareripsproduction.madn.client.logic.Player;

import java.awt.*;

/**
 * @author Thomas Hampe <thomas@hampe.co>
 * @version 1.0
 * @since 2014-04-13
 */
public class RedFigure extends GameFigure {

    protected int startId = 0;
    protected int homeStartId = 40;


    public RedFigure(int index) {
        super(SPRITE_RED, -1, index);
    }

    @Override
    protected Point updateBoardPosition() {
        Point p = new Point(0, 0);
        if (id < 0) {
            p.y = 0;
            p.x = index;
        } else {
            p = super.updateBoardPosition();
        }
        return p;
    }

    protected int getStartId() {
        return this.startId;
    }

    public int getHomeStartId() {
        return homeStartId;
    }

    @Override
    protected int getPlayerType() {
        return Player.RED_PLAYER;
    }

}
