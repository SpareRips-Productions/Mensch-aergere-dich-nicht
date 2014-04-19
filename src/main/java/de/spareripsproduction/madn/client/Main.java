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
        String environment = Game.ENV_PROD;

        for(int i = 0; i < args.length; i++){
            String arg = args[i];
            if(arg.equals("--env") && args.length > i+1) {
                environment = args[i+1];
                break;
            }
        }

        Game madn = Game.getInstance(environment);
        madn.run();
    }
}
