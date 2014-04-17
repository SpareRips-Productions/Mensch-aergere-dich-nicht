package de.spareripsproduction.tinyengine;


/**
 * Core class should no longer be used, will be removed
 *
 * @author Thomas Hampe <thomas@hampe.co>
 * @version 1.0
 * @since 2014-03-08
 * @deprecated
 */
public class Core {

    /**
     * log a message
     *
     * @param s Message to log
     * @deprecated
     */
    public static void log(String s) {
        System.out.println(s);
    }

    /**
     * Exits the program
     *
     * @param code ExitCode
     * @deprecated
     */
    public static void exit(int code) {
        System.exit(code);
    }
}
