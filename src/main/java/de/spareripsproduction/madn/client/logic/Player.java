package de.spareripsproduction.madn.client.logic;

import de.spareripsproduction.madn.client.graphics.Board;
import de.spareripsproduction.madn.client.graphics.BoardEntity;
import de.spareripsproduction.madn.client.graphics.RenderAndUpdateable;
import de.spareripsproduction.madn.client.graphics.figure.*;
import de.spareripsproduction.tinyengine.FontManager;
import de.spareripsproduction.tinyengine.Timer;
import de.spareripsproduction.tinyengine.gui.TELabel;

import java.awt.*;
import java.util.ArrayList;

/**
 * Player Class
 *
 * Created by marian on 12/03/14.
 */
public class Player implements RenderAndUpdateable {

    public static final int RED_PLAYER = 0;
    public static final int BLUE_PLAYER = 1;
    public static final int GREEN_PLAYER = 2;
    public static final int YELLOW_PLAYER = 3;

    protected int type;

    protected boolean active = false;

    protected int rollCount = 3;

    protected String name;

    private ArrayList<GameFigure> gameFigures;

    protected static long last = Timer.getTime();

    protected TELabel nameLabel;

    /**
     *
     * @param type PlayerType:
     *             Player.RED_PLAYER,
     *             Player.BLUE_PLAYER,
     *             Player.GREEN_PLAYER,
     *             Player.YELLOW_PLAYER
     */
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


        this.nameLabel = new TELabel(this.name, 0, 0, FontManager.getFont(FontManager.FONT_COMIC_NEUE, 20));
    }

    /**
     * called when player is active, process player input to make next move
     */
    public void makeMove() {
        if (isActive()) {
            if (!canMove()) {
                if (Timer.getTime() - last > 500) {
                    if (getDice().isLocked()) {
                        this.getDice().unlock();
                        if (rollCount == 0) {
                            nextPlayer();
                        }
                        if (this.getDice().getLastNumber() != 0) {
                            rollCount--;
                            if (rollCount == 0) {
                                getDice().lock();
                            }
                        }


                    }
                    last = Timer.getTime();
                }


            } else {
                for (GameFigure gameFigure : getGameFigures()) {
                    if (gameFigure.isClicked() && gameFigure.canMove(getDice().getLastNumber())) {
                        gameFigure.move(getDice().getLastNumber());

                        rollCount--;
                        if (canRollDiceAgain()) {
                            getDice().unlock();
                            rollCount = 1;
                        }
                        getDice().reset();
                        if (rollCount == 0) {

                            nextPlayer();
                        }
                        break;
                    }
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void update() {
        Board board = Board.getInstance();
        int x = board.getIntX() + (board.getIntWidth() - 11 * BoardEntity.FIELD_SIZE) / 2;
        int y = board.getIntY() + (board.getIntHeight() - 11 * BoardEntity.FIELD_SIZE) / 2;
        switch (getType()) {
            case RED_PLAYER:
                y += BoardEntity.FIELD_SIZE;
                break;
            case BLUE_PLAYER:
                x += BoardEntity.FIELD_SIZE * 7;
                y += BoardEntity.FIELD_SIZE;
                break;
            case GREEN_PLAYER:
                x += BoardEntity.FIELD_SIZE * 7;
                y += BoardEntity.FIELD_SIZE * 9;
                break;
            case YELLOW_PLAYER:
                y += BoardEntity.FIELD_SIZE * 9;
                break;
        }

        nameLabel.verticalAlignCenter(x, x + 4 * BoardEntity.FIELD_SIZE);
        nameLabel.setY(y);

        nameLabel.update();
        if (isActive()) {
            nameLabel.setText(String.format("%s (%d)", this.name, this.rollCount));
        } else {
            nameLabel.setText(this.name);
        }


    }

    protected boolean canRollDiceThreeTimes() {
        boolean result = true;
        for (GameFigure gameFigure : getGameFigures()) {
            result = result && gameFigure.getId() == GameFigure.IN_HOUSE_ID;
        }

        return result;
    }

    protected boolean canRollDiceAgain() {
        return getDice().getLastNumber() == 6;
    }

    protected boolean canMove() {
        for (GameFigure gameFigure : getGameFigures()) {
            if (gameFigure.canMove(getDice().getLastNumber())) {
                return true;
            }
        }
        return false;
    }

    protected Dice getDice() {
        return Board.getInstance().getDice();
    }

    /**
     * Activates player, so he can roll the dice and move
     */
    public void activate() {
        this.rollCount = (canRollDiceThreeTimes()) ? 3 : 1;
        System.out.println("Player: " + name + "(" + rollCount + ") is now active");
        this.active = true;
    }

    /**
     * Activates next player
     */
    public void nextPlayer() {
        this.active = false;
        Board.getInstance().getDice().reset();
        ArrayList<Player> players = Board.getInstance().getPlayers();
        int playerIndex = players.indexOf(this);
        players.get((playerIndex + 1) % players.size()).activate();
    }

    /**
     * {@inheritDoc}
     */
    public void render(Graphics2D g) {
        Color color = g.getColor();
        if (isActive()) {
            g.setColor(Color.magenta);
        }
        nameLabel.render(g);

        g.setColor(color);
    }

    /**
     *
     * @return a list of gameFigures from this player
     */
    public ArrayList<GameFigure> getGameFigures() {
        if (gameFigures == null) {
            gameFigures = new ArrayList<GameFigure>();
            for (GameFigure gameFigure : Board.getInstance().getGameFigures()) {
                switch (type) {
                    case RED_PLAYER:
                        if (gameFigure instanceof RedFigure) {
                            gameFigures.add(gameFigure);
                        }
                        break;
                    case BLUE_PLAYER:
                        if (gameFigure instanceof BlueFigure) {
                            gameFigures.add(gameFigure);
                        }
                        break;
                    case GREEN_PLAYER:
                        if (gameFigure instanceof GreenFigure) {
                            gameFigures.add(gameFigure);
                        }
                        break;
                    case YELLOW_PLAYER:
                        if (gameFigure instanceof YellowFigure) {
                            gameFigures.add(gameFigure);
                        }
                        break;
                }
            }
        }
        return gameFigures;
    }

    /**
     *
     * @return true if active, false if not
     */
    public boolean isActive() {
        return active;
    }

    /**
     *
     * @return Player.RED_PLAYER or
     *         Player.BLUE_PLAYER or
     *         Player.GREEN_PLAYER or
     *         Player.YELLOW_PLAYER
     */
    public int getType() {
        return type;
    }
}
