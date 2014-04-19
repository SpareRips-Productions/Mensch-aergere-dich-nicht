package de.spareripsproduction.madn.client.logic;

import de.spareripsproduction.madn.client.graphics.figure.GameFigure;
import de.spareripsproduction.tinyengine.Timer;

/**
 * @author Thomas Hampe <thomas@hampe.co>
 * @version 1.0
 * @since 2014-04-19
 */
public class AIPlayer extends Player
{
    private long last = Timer.getTime();

    public AIPlayer(int type) {
        super(type);
        this.name = "Computer "+this.type;
    }

    public void makeMove() {
        if (isActive()) {
            if(Timer.getTime()-last > 100) {
                if (!canMove()) {
                    if (getDice().isLocked()) {
                        this.getDice().unlock();
                        if (rollCount == 0) {
                            nextPlayer();
                        }else if (this.getDice().getLastNumber() != 0) {

                            rollCount--;
                            if (rollCount == 0) {
                                getDice().lock();
                            }
                        }


                    }else {
                        getDice().getNextNumber();
                        getDice().lock();
                    }


                } else {
                    for (GameFigure gameFigure : getGameFigures()) {
                        if (gameFigure.canMove(getDice().getLastNumber())) {
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
                last = Timer.getTime();
            }

        }

    }
}
