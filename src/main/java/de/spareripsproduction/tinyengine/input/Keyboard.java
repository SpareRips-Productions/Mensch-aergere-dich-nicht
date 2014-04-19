package de.spareripsproduction.tinyengine.input;

import java.awt.*;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;

/**
 * Keeps track of the Keyboard Input
 * <p>
 * Provides central access to <i>Keyboard Input</i>, without additional binding to the <code>Keyboard Events</code>
 * </p>
 *
 *
 * @author Thomas Hampe <thomas@hampe.co>
 * @version 1.0
 * @since 2014-03-10
 */
public class Keyboard implements AWTEventListener {

    private boolean[] keys;

    private static Keyboard instance;

    private Keyboard() {
        this.keys = new boolean[1024];
        Toolkit.getDefaultToolkit().addAWTEventListener(this, AWTEvent.KEY_EVENT_MASK);
    }

    protected static Keyboard getInstance() {
        if (Keyboard.instance == null) {
            Keyboard.instance = new Keyboard();
        }
        return Keyboard.instance;
    }

    /**
     * Checks if a certain key is pressed
     *
     * @param key <code>KeyEvent</code> Code
     * @return <code>true</code> if pressed, <code>false</code> if not
     */
    public static boolean isPressed(int key) {

        return Keyboard.getInstance().isKeyPressed(key);
    }

    protected boolean isKeyPressed(int key) {
        return (key < 1024 && key >= 0) && keys[key];
    }

    /******** Respond to key events ********/

    /**
     * Notification of a key press
     *
     * @param e The event details
     */
    protected void keyPressed(KeyEvent e) {
        if (e.isConsumed()) {
            return;
        }
        this.keys[e.getKeyCode()] = true;
    }

    /**
     * Notification of a key release
     *
     * @param e The event details
     */
    protected void keyReleased(KeyEvent e) {
        if (e.isConsumed()) {
            return;
        }

        KeyEvent nextPress = (KeyEvent) Toolkit.getDefaultToolkit().getSystemEventQueue().peekEvent(KeyEvent.KEY_PRESSED);

        if ((nextPress == null) || (nextPress.getWhen() != e.getWhen())) {
            this.keys[e.getKeyCode()] = false;
        }

    }

    /**
     * Notification of an AWT event
     *
     * @param e The event details
     */
    public void eventDispatched(AWTEvent e) {
        if (e.getID() == KeyEvent.KEY_PRESSED) {
            this.keyPressed((KeyEvent) e);
        }
        if (e.getID() == KeyEvent.KEY_RELEASED) {
            this.keyReleased((KeyEvent) e);
        }
    }


}
