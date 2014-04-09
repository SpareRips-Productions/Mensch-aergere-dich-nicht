package de.spareripsproduction.madn.client.logic;

import de.spareripsproduction.madn.client.Game;
import de.spareripsproduction.madn.client.graphics.*;
import de.spareripsproduction.madn.client.scene.GameScene;
import de.spareripsproduction.tinyengine.gui.*;
import de.spareripsproduction.tinyengine.logic.UpdateInterface;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marian on 12/03/14.
 */
public class Player implements RenderAndUpdateable {
    private TEButton btnDice;
    private List<GameFigure> gameFigureList = new ArrayList<GameFigure>();
    private Point gameFigureListDrawPoint;
    private Font menuFont = new Font("PressStart2P-Regular", Font.PLAIN, 20);
    private String name;
    private SpawnField spawnField;
    private int lastDiceRoll;

    public Player(String name, String SpriteRef, Point gameFigureListDrawPoint, Field spawnField) {
        this.name = name;
        this.gameFigureListDrawPoint = gameFigureListDrawPoint;
        this.gameFigureList.add(new GameFigure(SpriteRef, this.gameFigureListDrawPoint.x, this.gameFigureListDrawPoint.y, this));
        this.gameFigureList.add(new GameFigure(SpriteRef, this.gameFigureListDrawPoint.x + 45, this.gameFigureListDrawPoint.y, this));
        this.gameFigureList.add(new GameFigure(SpriteRef, this.gameFigureListDrawPoint.x + 90, this.gameFigureListDrawPoint.y, this));
        this.gameFigureList.add(new GameFigure(SpriteRef, this.gameFigureListDrawPoint.x + 135, this.gameFigureListDrawPoint.y, this));
        this.spawnField = (SpawnField)spawnField;
    }

    public void setPlayerActive() {
        this.btnDice = new TEButton("Wuerfel", 0, 0, 300, 50, 5, menuFont);
    }

    private void setNextPlayer() {
        GameScene scene = (GameScene)Game.getInstance().activeScene;
        int index = scene.playerList.indexOf(this);
        index = (index + 1) % scene.playerList.size();
        scene.playerList.get(index).setPlayerActive();
    }

    private boolean gameFigureAbleToMove(GameFigure gameFigure, int count) {
        GameScene scene = (GameScene)Game.getInstance().activeScene;
        Board board = scene.getBoard();
        int index = -1;
        for (int i = 0; i < board.fields.length; i++) {
            if (board.fields[i].gameFigure == gameFigure) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            return this.spawnField.gameFigure == null;
        }

        for (int i = 1; i <= count; i++) {
            if (board.fields[index + i] instanceof HomeEntryField) {
                int fieldToGo = count - i;

                if (fieldToGo > 4) {
                    return false;
                }

                if (((HomeEntryField)board.fields[index + i]).fields[fieldToGo].gameFigure != null) {
                    return false;
                }

                return true;
            }
        }

        return board.fields[(index + count) % board.fields.length].gameFigure == null ||
               board.fields[(index + count) % board.fields.length].gameFigure.owner != this;
    }

    private void gameFigureMove(GameFigure gameFigure, int count) {
        GameScene scene = (GameScene)Game.getInstance().activeScene;
        Board board = scene.getBoard();
        int index = -1;
        for (int i = 0; i < board.fields.length; i++) {
            if (board.fields[i].gameFigure == gameFigure) {
                index = i;
                break;
            }
        }

        board.fields[index].gameFigure = null;

        for (int i = 1; i <= count; i++) {
            if (board.fields[index + i] instanceof HomeEntryField) {
                int fieldToGo = count - i;

                ((HomeEntryField)board.fields[index + i]).fields[fieldToGo].gameFigure = gameFigure;
                return;
            }
        }

        GameFigure figure = board.fields[(index + count) % board.fields.length].gameFigure;
        if (figure != null) {
            int figuresInHood = 0;
            for (Field f : board.fields) {
                if (f.gameFigure != null && f.gameFigure.owner == figure.owner) {
                    figuresInHood++;
                }
            }

            figure.setLocation((int)figure.owner.gameFigureListDrawPoint.getX() + 45 * figuresInHood, (int)figure.owner.gameFigureListDrawPoint.getY());
        }

        board.fields[(index + count) % board.fields.length].gameFigure = gameFigure;
    }

    @Override
    public void update() {
        if (this.btnDice != null) {
            this.btnDice.update();
            if (this.btnDice.isClicked()) {
                this.btnDice = null;
                GameScene scene = (GameScene)Game.getInstance().activeScene;
                this.lastDiceRoll = scene.dice.getNextNumber();

                for (GameFigure g : this.gameFigureList) {
                    if (gameFigureAbleToMove(g, this.lastDiceRoll)) {
                        g.setClickAble(true);
                    }
                }
            }
        } else {
            for (GameFigure f : this.gameFigureList) {
                if (f.isClickAble() && f.isClicked()) {
                    gameFigureMove(f, this.lastDiceRoll);

                    for (GameFigure fg : this.gameFigureList) {
                        fg.setClickAble(false);
                    }

                    setNextPlayer();
                }
            }
        }
    }

    @Override
    public void render(Graphics2D g) {
        if (this.btnDice != null) {
            this.btnDice.render(g);
        }
    }
}
