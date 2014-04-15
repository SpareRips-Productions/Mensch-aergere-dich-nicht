package de.spareripsproduction.madn.client.logic;

import de.spareripsproduction.madn.client.graphics.*;
import de.spareripsproduction.madn.client.graphics.figure.*;
import de.spareripsproduction.tinyengine.Timer;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by marian on 12/03/14.
 */
public class Player implements RenderAndUpdateable {

    public static final int RED_PLAYER      = 0;
    public static final int BLUE_PLAYER     = 1;
    public static final int GREEN_PLAYER    = 2;
    public static final int YELLOW_PLAYER   = 3;

    protected int type;

    protected boolean active = false;

    protected int rollCount = 3;

    protected String name;

    private ArrayList<GameFigure> gameFigures;

    protected static long last = Timer.getTime();

    public Player(int type) {
        this.type = type;
        switch (type) {
            case RED_PLAYER:
                name = Settings.Player1Name;
                break;
            case BLUE_PLAYER:
                name = Settings.Player2Name;
                break;
            case GREEN_PLAYER:
                name = Settings.Player3Name;
                break;
            case YELLOW_PLAYER:
                name = Settings.Player4Name;
                break;
        }
    }



    public void makeMove() {
        if(isActive()) {
            if(!canMove() ) {
                if(Timer.getTime() - last > 500) {
                    if(getDice().isLocked()) {
                        this.getDice().unlock();
                        if(rollCount == 0) {
                            nextPlayer();
                        }
                        if(this.getDice().getLastNumber() != 0) {
                            rollCount--;
                        }


                    }
                    last = Timer.getTime();
                }


            }else {
                for(GameFigure gameFigure : getGameFigures()) {
                    if(gameFigure.isClicked()) {
                        gameFigure.move(getDice().getLastNumber());

                        rollCount--;
                        if(canRollDiceAgain()) {
                            getDice().unlock();
                            rollCount = 1;
                        }
                        getDice().reset();
                        if(rollCount == 0) {

                            nextPlayer();
                        }
                        break;
                    }
                }
            }
        }
    }

    public void update() {


    }

    protected boolean canRollDiceThreeTimes() {
        boolean result = true;
        for(GameFigure gameFigure : getGameFigures()) {
            result = result && gameFigure.getId() == GameFigure.IN_HOUSE_ID;
        }

        return result;
    }

    protected boolean canRollDiceAgain() {
        return getDice().getLastNumber() == 6;
    }

    protected boolean canMove() {
        for(GameFigure gameFigure : getGameFigures()) {
            if(gameFigure.canMove(getDice().getLastNumber())) {
                return true;
            }
        }
        return false;
    }

    protected Dice getDice() {
        return Board.getInstance().getDice();
    }

    public void activate() {
        this.rollCount = (canRollDiceThreeTimes()) ? 3 : 1;
        System.out.println("Player: "+name+"("+rollCount+") is now active");
        this.active = true;
    }

    public void nextPlayer() {
        this.active = false;
        Board.getInstance().getDice().reset();
        ArrayList<Player> players = Board.getInstance().getPlayers();
        int playerIndex = players.indexOf(this);
        players.get((playerIndex+1)%players.size()).activate();
    }

    public void render(Graphics2D g) {

    }

    public ArrayList<GameFigure> getGameFigures() {
        if(gameFigures == null) {
            gameFigures = new ArrayList<GameFigure>();
            for(GameFigure gameFigure : Board.getInstance().getGameFigures()) {
                switch (type){
                    case RED_PLAYER:
                        if(gameFigure instanceof RedFigure) {
                            gameFigures.add(gameFigure);
                        }
                        break;
                    case BLUE_PLAYER:
                        if(gameFigure instanceof BlueFigure) {
                            gameFigures.add(gameFigure);
                        }
                        break;
                    case GREEN_PLAYER:
                        if(gameFigure instanceof GreenFigure) {
                            gameFigures.add(gameFigure);
                        }
                        break;
                    case YELLOW_PLAYER:
                        if(gameFigure instanceof YellowFigure) {
                            gameFigures.add(gameFigure);
                        }
                        break;
                }
            }
        }
        return gameFigures;
    }

    public boolean isActive() {
        return active;
    }

    public int getType() {
        return type;
    }
}
