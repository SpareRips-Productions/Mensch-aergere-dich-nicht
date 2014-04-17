package de.spareripsproduction.tinyengine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

/**
 * Responsible for the Game Window
 *
 * @author Thomas Hampe <thomas@hampe.co>
 * @version 1.0
 * @since 2014-03-08
 */
public class GameWindow extends Canvas {

    /**
     * BufferStrategy for Graphics Context
     */
    private BufferStrategy strategy;

    /**
     * Holds the canvas, on which we draw
     */
    private JFrame frame;

    /**
     * Singleton instance
     */
    private static GameWindow instance;

    /**
     * Singleton method with no params, should be used for getting the singleton
     *
     * @return Window singleton
     */
    public static GameWindow getInstance() {
        return getInstance("GameWindow", 800, 600);
    }

    /**
     * Single method should be used for creating a new Window
     *
     * @param title  of the Window
     * @param width  of the Window in px
     * @param height of the Window in px
     * @return Window singleton
     */
    public static GameWindow getInstance(String title, int width, int height) {
        if (GameWindow.instance == null) {
            GameWindow.instance = new GameWindow(title, width, height);
        }
        return GameWindow.instance;
    }

    /**
     * Constructor for the Game Window, should not be used, use getInstance() instead
     *
     * @param title  of the Window
     * @param width  of the Window in px
     * @param height of the Window in px
     */
    public GameWindow(String title, int width, int height) {

        this.frame = new JFrame(title);
        JPanel panel = (JPanel) this.frame.getContentPane();
        panel.setPreferredSize(new Dimension(width, height));
        setBounds(0, 0, width, height);
        panel.add(this);

        //ignore refresh
        this.setIgnoreRepaint(true);

        this.frame.pack();
        this.frame.setResizable(false);
        this.frame.setVisible(true);
        this.frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.frame.setLocationRelativeTo(null);

        this.requestFocus();

        /*
         *   create the buffering strategy which will allow AWT
         *   to manage our accelerated graphics
         */
        this.createBufferStrategy(2);
        this.strategy = this.getBufferStrategy();
    }

    /**
     * Returns the context on which we draw
     *
     * @return the Graphics Context
     */
    public Graphics2D getGraphicsContext() {
        return (Graphics2D) this.strategy.getDrawGraphics();
    }

    /**
     * Repaints the window
     */
    public void repaint() {
        this.getGraphicsContext().dispose();
        this.strategy.show();
    }

    /**
     * Sets the Title of the window
     *
     * @param title new Title
     */
    public void setTitle(String title) {
        this.frame.setTitle(title);
    }

    /**
     * @return the frame of the Window
     */
    public JFrame getFrame() {
        return this.frame;
    }
}
