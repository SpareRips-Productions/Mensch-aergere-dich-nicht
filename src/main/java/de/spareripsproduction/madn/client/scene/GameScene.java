package de.spareripsproduction.madn.client.scene;

import de.spareripsproduction.madn.client.graphics.Board;
import de.spareripsproduction.madn.client.logic.Player;

import java.awt.*;
import java.util.*;
import java.util.List;

import de.spareripsproduction.madn.client.logic.Dice;

/**
 * Created by marian on 12/03/14.
 */
public class GameScene extends Scene {

    public List<Player> playerList = new ArrayList<Player>();
    public Dice dice = new Dice();

    private Board board;

    public void load() {
        //playerList.add(new Player());
        //playerList.add(new Player());
        //playerList.add(new Player());
        //playerList.add(new Player());
        this.board = new Board(5, 5);
    }
    public void unload() {

    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics2D g) {
        this.board.render(g);
    }
}
