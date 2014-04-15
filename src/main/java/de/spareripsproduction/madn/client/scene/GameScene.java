package de.spareripsproduction.madn.client.scene;

import de.spareripsproduction.madn.client.graphics.Board;
import de.spareripsproduction.madn.client.logic.Player;

import java.awt.*;
import java.util.*;
import java.util.List;

import de.spareripsproduction.madn.client.logic.Dice;
import de.spareripsproduction.tinyengine.GameWindow;
import de.spareripsproduction.tinyengine.entity.Entity;

/**
 * Created by marian on 12/03/14.
 */
public class GameScene extends Scene {

    private Board board;

    private Entity backgroundImage;

    public void load() {
        this.board = Board.getInstance();
        this.backgroundImage = new Entity("sprites/title.png", 0, 0);

    }
    public void unload() {

    }

    @Override
    public void update() {
        this.backgroundImage.update();
        this.board.update();
    }

    @Override
    public void render(Graphics2D g) {
        this.backgroundImage.render(g);
        this.board.render(g);
    }

    public Board getBoard() { return this.board; }
}
