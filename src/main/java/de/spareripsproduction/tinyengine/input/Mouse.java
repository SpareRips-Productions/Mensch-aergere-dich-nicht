package de.spareripsproduction.tinyengine.input;

import java.awt.*;
import java.awt.event.AWTEventListener;
import java.awt.event.MouseEvent;

/**
 * @author Thomas Hampe <thomas@hampe.co>
 * @version 1.0
 * @since 2014-03-22
 */
public class Mouse implements AWTEventListener {

    private static Mouse instance;

    private Point buttons[];

    private Point mouseLocation;

    public static Mouse getInstance() {
        if (Mouse.instance == null) {
            Mouse.instance = new Mouse();
        }
        return Mouse.instance;
    }

    public static Point position(int button) {
        return Mouse.getInstance().getButtonPosition(button);
    }

    public static boolean isClicked(int button) {
        return Mouse.getInstance().isButtonPressed(button);
    }

    public static Point location() {
        return Mouse.getInstance().getLocation();
    }

    public Mouse() {
        this.buttons = new Point[4];
        this.mouseLocation = new Point(0, 0);
        Toolkit.getDefaultToolkit().addAWTEventListener(this, AWTEvent.MOUSE_EVENT_MASK);
        Toolkit.getDefaultToolkit().addAWTEventListener(this, AWTEvent.MOUSE_MOTION_EVENT_MASK);
    }

    private Point getButtonPosition(int button) {
        return (button > 0 && button < 4) ? this.buttons[button] : null;
    }

    private boolean isButtonPressed(int button) {
        return this.getButtonPosition(button) != null;
    }

    private void buttonPressed(MouseEvent e) {
        if (e.isConsumed()) {
            return;
        }
        this.buttons[e.getButton()] = new Point(e.getX(), e.getY());
    }

    private Point getLocation() {
        return this.mouseLocation;
    }

    /**
     * Notification of a key release
     *
     * @param e The event details
     */
    public void buttonReleased(MouseEvent e) {
        if (e.isConsumed()) {
            return;
        }

        MouseEvent nextPress = (MouseEvent) Toolkit.getDefaultToolkit().getSystemEventQueue().peekEvent(MouseEvent.MOUSE_PRESSED);

        if ((nextPress == null) || (nextPress.getWhen() != e.getWhen())) {
            this.buttons[e.getButton()] = null;
        }

    }

    public void moved(MouseEvent e) {
        this.mouseLocation = new Point(e.getX(), e.getY());
    }

    /**
     * Notification that an event has occured in the AWT event
     * system
     *
     * @param e The event details
     */
    public void eventDispatched(AWTEvent e) {
        if (e.getID() == MouseEvent.MOUSE_PRESSED) {
            this.buttonPressed((MouseEvent) e);
        }
        if (e.getID() == MouseEvent.MOUSE_RELEASED) {
            this.buttonReleased((MouseEvent) e);
        }
        if (e.getID() == MouseEvent.MOUSE_MOVED) {
            this.moved((MouseEvent) e);
        }

    }
}
