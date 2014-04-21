package de.spareripsproduction.tinyengine;

import de.spareripsproduction.tinyengine.graphics.RenderInterface;
import de.spareripsproduction.tinyengine.logic.UpdateInterface;

import java.awt.*;

/**
 * Main Class for the Game, handles logic updates und rendering
 *
 * @author Thomas Hampe <thomas@hampe.co>
 * @version 1.0
 * @since 2014-03-10
 */
public class Game {

    /**
     * handles the logic
     */
    private UpdateInterface updater;

    /**
     * handles the drawing
     */
    private RenderInterface renderer;

    /**
     * The Window
     */
    private GameWindow window;

    /**
     * last time in ms when GameLoop was run
     */
    private long lastLoop;

    /**
     * in ms, waits after every repaint
     */
    private int delay;

    /**
     * Default constructor
     *
     * @param title  of the Window
     * @param width  of the Window in px
     * @param height of the Window in px
     */
    public Game(String title, int width, int height) {
        this.window = GameWindow.getInstance(title, width, height);
        this.lastLoop = 0;
        this.delay = 10;
    }

    /**
     * Constructor with update und render objects
     *
     * @param title    of the Window
     * @param width    of the Window in px
     * @param height   of the Window in px
     * @param updater  Updates the Logic
     * @param renderer Renders the window
     */
    public Game(String title, int width, int height, UpdateInterface updater, RenderInterface renderer) {
        this(title, width, height);
        this.setUpdater(updater);
        this.setRenderer(renderer);
    }

    /**
     * the game loop
     */
    public void run() {
        // load some font(s)

        while (true) {
            this.lastLoop = Timer.getTime();
            //update
            if (this.updater != null) {
                this.updater.update();
            }
            //render
            int height = this.getWindow().getHeight();
            int width = this.getWindow().getWidth();
            Graphics2D context = this.getWindow().getGraphicsContext();
            context.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

            context.setRenderingHint(
                    RenderingHints.KEY_TEXT_ANTIALIASING,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            context.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            context.setColor(Color.black);
            context.fillRect(0, 0, width, height);

            if (this.renderer != null) {
                this.renderer.render(context);
            }

            this.getWindow().repaint();
            long delta = this.lastLoop + this.delay - Timer.getTime();
            if (delta > 0) {
                Timer.sleep(delta);
            }
        }
    }

    /**
     * Sets the render
     *
     * @param renderer is responsible for rendering
     */
    public void setRenderer(RenderInterface renderer) {
        this.renderer = renderer;
    }

    /**
     * Sets the updater
     *
     * @param updater is responsible for updating
     */
    public void setUpdater(UpdateInterface updater) {
        this.updater = updater;
    }

    /**
     * the game window
     *
     * @return the Window
     */
    public GameWindow getWindow() {
        return window;
    }


}

