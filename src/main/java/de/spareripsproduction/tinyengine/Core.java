package de.spareripsproduction.tinyengine;


/**
 * Core class should no longer be used, will be removed
 *
 * @deprecated
 * @author Thomas Hampe <thomas@hampe.co>
 * @version 1.0
 * @since 2014-03-08
 */
public class Core {

    /**
     * log a message
     *
     * @deprecated
     * @param s Message to log
     */
    public static void log(String s) {
        System.out.println(s);
    }

    /**
     * Exits the program
     *
     * @deprecated
     * @param code ExitCode
     */
    public static void exit(int code) {
        System.exit(code);
    }
}
