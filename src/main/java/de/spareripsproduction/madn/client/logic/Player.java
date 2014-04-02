package de.spareripsproduction.madn.client.logic;

import de.spareripsproduction.madn.client.Game;
import de.spareripsproduction.madn.client.graphics.GameFigure;
import de.spareripsproduction.madn.client.scene.GameScene;
import de.spareripsproduction.tinyengine.gui.*;
import de.spareripsproduction.tinyengine.logic.UpdateInterface;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marian on 12/03/14.
 */
public class Player implements UpdateInterface {
    private TEButton btnDice;
    private List<GameFigure> gameFigureList = new ArrayList<GameFigure>();
    private Font menuFont = new Font("PressStart2P-Regular", Font.PLAIN, 20);

    public Player() {
        gameFigureList.add(new GameFigure(this, Color.red));
        gameFigureList.add(new GameFigure(this, Color.red));
        gameFigureList.add(new GameFigure(this, Color.red));
        gameFigureList.add(new GameFigure(this, Color.red));
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
}
