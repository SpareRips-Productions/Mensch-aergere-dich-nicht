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

    private boolean inBetween(int i, int min, int max) {
        return i >= min && i <= max;
    }

    public Board(int x, int y) {
        super("sprites/board.png", x, y);
        this.playerList = new ArrayList<Player>();
        this.playerList.add(new Player(Settings.Player1Name, Color.red, new Point(0, 0)));
        this.playerList.add(new Player(Settings.Player2Name, Color.green, new Point(500, 0)));
        this.playerList.add(new Player(Settings.Player3Name, Color.blue, new Point(500, 500)));
        this.playerList.add(new Player(Settings.Player4Name, Color.yellow, new Point(0, 500)));

        this.fields = new Field[40];
        pffffff(this.fields);

        this.fields[0] = new SpawnField(GameFigure.COLOR_RED, this.fields[0].getIntX(), this.fields[0].getIntY());
        this.fields[10] = new SpawnField(GameFigure.COLOR_GREEN, this.fields[10].getIntX(), this.fields[10].getIntY());
        this.fields[20] = new SpawnField(GameFigure.COLOR_BLUE, this.fields[20].getIntX(), this.fields[20].getIntY());
        this.fields[30] = new SpawnField(GameFigure.COLOR_YELLOW, this.fields[30].getIntX(), this.fields[30].getIntY());
        this.fields[39] = new HomeEntryField(GameFigure.COLOR_RED, this.fields[39].getIntX(), this.fields[39].getIntY(), 1, 0);
        this.fields[9] = new HomeEntryField(GameFigure.COLOR_GREEN, this.fields[9].getIntX(), this.fields[9].getIntY(), 0, 1);
        this.fields[19] = new HomeEntryField(GameFigure.COLOR_BLUE,this.fields[19].getIntX(), this.fields[19].getIntY(), -1, 0);
        this.fields[29] = new HomeEntryField(GameFigure.COLOR_YELLOW,this.fields[29].getIntX(), this.fields[29].getIntY(), 0, -1);
    }

    private void pffffff(Field[] fields) {
        fields[0] = new NormalField(toX(0), toY(4));
        fields[1] = new NormalField(toX(1), toY(4));
        fields[2] = new NormalField(toX(2), toY(4));
        fields[3] = new NormalField(toX(3), toY(4));
        fields[4] = new NormalField(toX(4), toY(4));
        fields[5] = new NormalField(toX(4), toY(3));
        fields[6] = new NormalField(toX(4), toY(2));
        fields[7] = new NormalField(toX(4), toY(1));
        fields[8] = new NormalField(toX(4), toY(0));
        fields[9] = new NormalField(toX(5), toY(0));
        fields[10] = new NormalField(toX(6), toY(0));
        fields[11] = new NormalField(toX(6), toY(1));
        fields[12] = new NormalField(toX(6), toY(2));
        fields[13] = new NormalField(toX(6), toY(3));
        fields[14] = new NormalField(toX(6), toY(4));
        fields[15] = new NormalField(toX(7), toY(4));
        fields[16] = new NormalField(toX(8), toY(4));
        fields[17] = new NormalField(toX(9), toY(4));
        fields[18] = new NormalField(toX(10), toY(4));
        fields[19] = new NormalField(toX(10), toY(5));
        fields[20] = new NormalField(toX(10), toY(6));
        fields[21] = new NormalField(toX(9), toY(6));
        fields[22] = new NormalField(toX(8), toY(6));
        fields[23] = new NormalField(toX(7), toY(6));
        fields[24] = new NormalField(toX(6), toY(6));
        fields[25] = new NormalField(toX(6), toY(7));
        fields[26] = new NormalField(toX(6), toY(8));
        fields[27] = new NormalField(toX(6), toY(9));
        fields[28] = new NormalField(toX(6), toY(10));
        fields[29] = new NormalField(toX(5), toY(10));
        fields[30] = new NormalField(toX(4), toY(10));
        fields[31] = new NormalField(toX(4), toY(9));
        fields[32] = new NormalField(toX(4), toY(8));
        fields[33] = new NormalField(toX(4), toY(7));
        fields[34] = new NormalField(toX(4), toY(6));
        fields[35] = new NormalField(toX(3), toY(6));
        fields[36] = new NormalField(toX(2), toY(6));
        fields[37] = new NormalField(toX(1), toY(6));
        fields[38] = new NormalField(toX(0), toY(6));
        fields[39] = new NormalField(toX(0), toY(5));
    }

    private int toX(int x) {
        return 80 + x * 45;
    }
    private int toY(int y) {
        return 45 + y * 45;
    }

    @Override
    public void update() {
        for (Player p : this.playerList) {
            p.update();
        }
    }

    @Override
    public void render(Graphics g) {
    }

    @Override
    public void render(Graphics2D g) {
        super.render(g);
        for (Player p : this.playerList) {
            p.render(g);
        }
        for (Field f : this.fields) {
            f.render(g);
        }
    }
}
