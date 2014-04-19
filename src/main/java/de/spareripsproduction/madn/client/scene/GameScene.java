package de.spareripsproduction.madn.client.scene;

import de.spareripsproduction.madn.client.graphics.Board;
import de.spareripsproduction.tinyengine.entity.Entity;

import java.awt.*;

/**
 * Created by marian on 12/03/14.
 */
public class GameScene extends Scene {

    private Board board;

    private Entity backgroundImage;

    /**
     * {@inheritDoc}
     */
    public void load() {
        this.board = Board.getInstance();
        this.backgroundImage = new Entity("sprites/title.png", 0, 0);

    }

    /**
     * {@inheritDoc}
     */
    public void unload() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        this.backgroundImage.update();
        this.board.update();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(Graphics2D g) {
        this.backgroundImage.render(g);
        this.board.render(g);
    }

    /**
     *
     * @return the board on this scene
     */
    public Board getBoard() {
        return this.board;
    }
}
