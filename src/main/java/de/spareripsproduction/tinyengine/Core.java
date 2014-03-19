package de.spareripsproduction.tinyengine;


/**
 * @author Thomas Hampe <thomas@hampe.co>
 * @version 1.0
 * @since 2014-03-08
 */
public class Core {

    public static void run(String title, int width, int height) {
        GameWindow.singleton(title, width, height);
    }

    public static void log(String s) {
        System.out.println(s);
    }

    public static void exit(int code) {
        System.exit(code);
    }
}
