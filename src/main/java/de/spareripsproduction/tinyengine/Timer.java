package de.spareripsproduction.tinyengine;

/**
 * The Standard Timer Class
 *
 * @author Thomas Hampe <thomas@hampe.co>
 * @version 1.0
 * @since 2014-03-10
 */
public class Timer {

    /**
     * Get current Time in milliseconds
     *
     * @return current Time in milliseconds
     */
    public static long getTime() {
        return System.currentTimeMillis();
    }

    /**
     * Pauses execution
     *
     * @param ms milliseconds to pause
     */
    public static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
