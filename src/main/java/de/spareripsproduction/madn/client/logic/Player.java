package de.spareripsproduction.madn.client.logic;

import de.spareripsproduction.madn.client.Game;
import de.spareripsproduction.madn.client.graphics.GameFigure;
import de.spareripsproduction.madn.client.graphics.RenderAndUpdateable;
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

    public Player(String name, Color color, Point gameFigureListDrawPoint) {
        this.name = name;
        this.gameFigureList.add(new GameFigure(this, color));
        this.gameFigureList.add(new GameFigure(this, color));
        this.gameFigureList.add(new GameFigure(this, color));
        this.gameFigureList.add(new GameFigure(this, color));
        this.gameFigureListDrawPoint = gameFigureListDrawPoint;
    }

    public void setPlayerActive() {
        this.btnDice = new TEButton("Wuerfel", 0, 0, 300, 50, 5, menuFont);
    }

    private void setNextPlayer() {
        GameScene scene = (GameScene)Game.getInstance().activeScene;
        int index = scene.playerList.indexOf(this);
        index = (index + 1) % 4;
        scene.playerList.get(index).setPlayerActive();
    }

    @Override
    public void update() {
        if (this.btnDice != null) {
            if (this.btnDice.isClicked()) {
                this.btnDice = null;
                GameScene scene = (GameScene)Game.getInstance().activeScene;
                int number = scene.dice.getNextNumber();
                //TODO
            }
        }
    }

    @Override
    public void render(Graphics g) {

    }
}
