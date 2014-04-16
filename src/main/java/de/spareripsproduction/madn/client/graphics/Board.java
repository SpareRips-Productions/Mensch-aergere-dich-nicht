package de.spareripsproduction.madn.client.graphics;

import de.spareripsproduction.madn.client.graphics.field.Field;
import de.spareripsproduction.madn.client.graphics.field.HomeEntryField;
import de.spareripsproduction.madn.client.graphics.field.NormalField;
import de.spareripsproduction.madn.client.graphics.field.SpawnField;
import de.spareripsproduction.madn.client.graphics.figure.*;
import de.spareripsproduction.tinyengine.*;
import de.spareripsproduction.tinyengine.Timer;
import de.spareripsproduction.tinyengine.entity.Entity;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;
import de.spareripsproduction.madn.client.logic.*;
import de.spareripsproduction.tinyengine.input.Keyboard;

/**
 * Created by marian on 12/03/14.
 */
public class Board extends Entity implements RenderAndUpdateable {

    private ArrayList<Field> fields;

    private ArrayList<Player> players;

    private ArrayList<GameFigure> gameFigures;

    private Dice dice;

    private static Board instance;

    private long last = Timer.getTime();

    public static Board getInstance() {
        if(instance == null) {
            instance = new Board(0,0);
        }
        return instance;
    }

    private boolean inBetween(int i, int min, int max) {
        return i >= min && i <= max;
    }

    public Board(int x, int y) {
        super("sprites/board.png", x, y);
        // center the board
        this.setLocation(GameWindow.getInstance().getWidth() / 2 - this.getWidth() / 2, GameWindow.getInstance().getHeight() / 2 - this.getHeight() / 2);

        this.dice = new Dice(0,0);
        this.dice.setX(GameWindow.getInstance().getWidth() / 2 - this.dice.getWidth() / 2);
        this.dice.setY(GameWindow.getInstance().getHeight() / 2 - this.dice.getHeight() / 2);



        this.fields = new ArrayList<Field>();
        for(int i = 0; i < 40; i++ ) {
            if(i%10 == 0) {
                switch (i/10) {
                    case 0:
                        this.fields.add(new SpawnField(SpawnField.SPRITE_RED, i));
                        break;
                    case 1:
                        this.fields.add(new SpawnField(SpawnField.SPRITE_BLUE, i));
                        break;
                    case 2:
                        this.fields.add(new SpawnField(SpawnField.SPRITE_GREEN, i));
                        break;
                    case 3:
                        this.fields.add(new SpawnField(SpawnField.SPRITE_YELLOW, i));
                        break;
                }
            }else {
                this.fields.add(new NormalField(i));
            }
        }
        // home fields
        String[] spirtes = {Field.SPRITE_RED, Field.SPRITE_BLUE, Field.SPRITE_GREEN, Field.SPRITE_YELLOW};
        for(int i = 0; i < 4; i++) {
           String spriteRef = spirtes[i];
           for(int j = i*10+40;j < i*10+44; j++){
                this.fields.add(new HomeEntryField(spriteRef, j));
           }
        }


        this.gameFigures = new ArrayList<GameFigure>();

        for(int i = 0; i < 4; i++) {
            this.gameFigures.add(new RedFigure(i));
            this.gameFigures.add(new BlueFigure(i));
            this.gameFigures.add(new GreenFigure(i));
            this.gameFigures.add(new YellowFigure(i));
        }

        this.players = new ArrayList<Player>();
        for(int i = 0; i < 4; i++) {
            this.players.add(new Player(i));
        }

    }

    @Override
    public void update() {

        getActivePlayer().makeMove();

        dice.update();
        for (Player player : this.players) {
            player.update();
        }
        for (Field field : this.fields) {
            field.update();
        }
        for (GameFigure gameFigure : this.gameFigures) {
            gameFigure.update();
        }

    }

    @Override
    public void render(Graphics2D g) {
        super.render(g);

        for (Player player : this.players) {
            player.render(g);
        }
        for (Field field : this.fields) {
            field.render(g);
        }
        this.dice.render(g);

        for (GameFigure gameFigure : this.gameFigures) {
            gameFigure.render(g);
        }

    }

    public ArrayList<GameFigure> getGameFigures() {
        return gameFigures;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Player getActivePlayer() {
        for(Player player : this.getPlayers()) {
            if(player.isActive()) {
                return player;
            }
        }
        Player activePlayer = getPlayers().get(0);
        activePlayer.activate();
        return activePlayer;
    }

    public Dice getDice() {
        return dice;
    }
}
