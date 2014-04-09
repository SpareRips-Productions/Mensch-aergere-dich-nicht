package de.spareripsproduction.madn.client.scene;

import de.spareripsproduction.madn.client.graphics.Board;
import de.spareripsproduction.madn.client.logic.Player;

import java.awt.*;
import java.util.*;
import java.util.List;

import de.spareripsproduction.madn.client.logic.Dice;
import de.spareripsproduction.tinyengine.GameWindow;

/**
 * Created by marian on 12/03/14.
 */
public class GameScene extends Scene {

    public Board board;

    public void load() {
        //playerList.add(new Player());
        //playerList.add(new Player());
        //playerList.add(new Player());
        //playerList.add(new Player());
        this.board = new Board(0, 0);
        this.board.playerList.get(0).setPlayerActive();
    }
    public void unload() {

    }

    @Override
    public void update() {
        this.board.update();
    }

    @Override
    public void render(Graphics2D g) {
        this.board.render(g);
    }

    public Board getBoard() { return this.board; }
}
