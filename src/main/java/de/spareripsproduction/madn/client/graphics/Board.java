package de.spareripsproduction.madn.client.graphics;

import de.spareripsproduction.tinyengine.entity.Entity;

import java.awt.*;
import java.util.*;
import de.spareripsproduction.madn.client.logic.*;

/**
 * Created by marian on 12/03/14.
 */
public class Board extends Entity implements RenderAndUpdateable {
    public Field[] fields;
    public java.util.List<Player> playerList;

    public Board(int x, int y) {
        super("sprites/board.png", x, y);
        this.playerList = new ArrayList<Player>();
        this.playerList.add(new Player(Settings.Player1Name, Color.red, new Point(0, 0)));
        this.playerList.add(new Player(Settings.Player2Name, Color.green, new Point(500, 0)));
        this.playerList.add(new Player(Settings.Player3Name, Color.blue, new Point(500, 500)));
        this.playerList.add(new Player(Settings.Player4Name, Color.yellow, new Point(0, 500)));

        this.fields = new Field[40];
        for (int i = 0; i < 40; i++) {
            int x1 = 0;
            int y1 = 0;
            this.fields[i] = new NormalField(x1, y1);
        }
        this.fields[0] = new SpawnField(0, 0, this.playerList.get(0));
        this.fields[10] = new SpawnField(0, 0, this.playerList.get(1));
        this.fields[20] = new SpawnField(0, 0, this.playerList.get(2));
        this.fields[30] = new SpawnField(0, 0, this.playerList.get(3));
        this.fields[39] = new HomeEntryField(0, 0, 1, 0, this.playerList.get(0));
        this.fields[9] = new HomeEntryField(0, 0, 0, 1, this.playerList.get(1));
        this.fields[19] = new HomeEntryField(0, 0, -1, 0, this.playerList.get(2));
        this.fields[29] = new HomeEntryField(0, 0, 0, -1, this.playerList.get(3));
    }

    private boolean inBetween(int i, int min, int max) {
        return i >= min && i <= max;
    }

    @Override
    public void update() {
        for (Player p : this.playerList) {
            p.update();
        }
    }

    @Override
    public void render(Graphics g) {
        for (Player p : this.playerList) {
            p.render(g);
        }
        for (Field f : this.fields) {
            f.render(g);
        }
    }
}
