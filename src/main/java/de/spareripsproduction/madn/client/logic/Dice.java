package de.spareripsproduction.madn.client.logic;

import java.util.Random;

/**
 * Created by marian on 12/03/14.
 */
public class Dice {

    private Random random;

    public Dice() {
        this.random = new Random();
    }

    public int getNextNumber() {
        return this.random.nextInt(6) + 1;
    }
}
