package de.spareripsproduction.madn.client.graphics.figure;

import de.spareripsproduction.madn.client.logic.Player;

import java.awt.*;

/**
 * @author Thomas Hampe <thomas@hampe.co>
 * @version 1.0
 * @since 2014-04-13
 */
public class BlueFigure extends GameFigure {

    protected int startId = 10;
    protected int homeStartId = 50;

    public BlueFigure(int index) {
        super(SPRITE_BLUE, -1, index);
    }

    @Override
    public Point updateBoardPosition() {
        Point p = new Point(0, 0);
        if (id < 0) {
            p.y = 0;
            p.x = 7 + index;
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
        return Player.BLUE_PLAYER;
    }

}
