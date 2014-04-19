package de.spareripsproduction.madn.client;

/**
 * Created by marian on 08/02/14.
 */
public class Main {

    /**
     * Starting point of the game
     *
     * @param args Application Start Args
     */
    public static void main(String args[]) {
        Game madn = Game.getInstance();
        madn.run();
    }
}
