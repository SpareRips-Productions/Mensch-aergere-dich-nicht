package de.spareripsproduction.madn.client.scene;

import de.spareripsproduction.madn.client.graphics.Board;

import java.awt.*;

/**
 * Created by marian on 12/03/14.
 */
public class GameScene extends Scene {

    protected Board board;

    public void load() {
        this.board = new Board();
    }
    public void unload() {
        this.board = null;
    }

    @Override
    public void update() {
        this.board.update();
    }

    @Override
    public void render(Graphics g) {
        this.board.render(g);
    }
}
