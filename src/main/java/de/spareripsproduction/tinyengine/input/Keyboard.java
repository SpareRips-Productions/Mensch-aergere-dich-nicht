package de.spareripsproduction.tinyengine.input;

import java.awt.*;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;

/**
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
    public void keyPressed(KeyEvent e) {
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
    public void keyReleased(KeyEvent e) {
        if (e.isConsumed()) {
            return;
        }

        KeyEvent nextPress = (KeyEvent) Toolkit.getDefaultToolkit().getSystemEventQueue().peekEvent(KeyEvent.KEY_PRESSED);

        if ((nextPress == null) || (nextPress.getWhen() != e.getWhen())) {
            this.keys[e.getKeyCode()] = false;
        }

    }

    public void keyTyped(KeyEvent e) {
        //@Todo something useful add listener or so;
    }

    /**
     * Notification that an event has occured in the AWT event
     * system
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
        if (e.getID() == KeyEvent.KEY_TYPED) {
            this.keyTyped((KeyEvent) e);
        }
    }


}
