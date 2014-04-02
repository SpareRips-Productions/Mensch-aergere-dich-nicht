package de.spareripsproduction.madn.client.graphics;

import de.spareripsproduction.tinyengine.entity.Entity;

import java.awt.*;

/**
 * Created by marian on 12/03/14.
 */
public class Board extends Entity implements RenderAndUpdateable {
    public Field[] fields;

    public Board(int x, int y) {
        super("sprites/board.png", x, y);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {

    }
}
