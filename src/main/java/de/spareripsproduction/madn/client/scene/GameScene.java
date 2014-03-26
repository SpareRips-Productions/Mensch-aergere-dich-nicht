package de.spareripsproduction.madn.client.scene;

import de.spareripsproduction.madn.client.logic.Player;

import java.awt.*;
import java.util.*;
import de.spareripsproduction.madn.client.logic.Dice;

/**
 * Created by marian on 12/03/14.
 */
public class GameScene extends Scene {

    public java.util.List<Player> playerList = new ArrayList<Player>();
    public Dice dice = new Dice();

    public void load() {
        playerList.add(new Player());
        playerList.add(new Player());
        playerList.add(new Player());
        playerList.add(new Player());
    }
    public void unload() {

    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {

    }
}
