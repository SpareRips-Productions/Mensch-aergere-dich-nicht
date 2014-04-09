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

    public Dice dice;

    private Board board;

    public void load() {
        //playerList.add(new Player());
        //playerList.add(new Player());
        //playerList.add(new Player());
        //playerList.add(new Player());
        this.dice = new Dice(0,0);
        this.board = new Board(5, 5);
        this.dice.setX((int) this.board.getWidth()/2 - this.dice.getWidth()/2);
        this.dice.setY((int) this.board.getHeight()/2 - this.dice.getHeight()/2);
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
        this.dice.render(g);
    }

    public Board getBoard() { return this.board; }
}
