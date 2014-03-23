package de.spareripsproduction.tinyengine;

import de.spareripsproduction.tinyengine.graphics.RenderInterface;
import de.spareripsproduction.tinyengine.logic.UpdateInterface;

import java.awt.*;

/**
 * @author Thomas Hampe <thomas@hampe.co>
 * @version 1.0
 * @since 2014-03-10
 */
public class Game {

    private UpdateInterface updater;

    private RenderInterface renderer;

    public GameWindow getWindow() {
        return window;
    }

    private GameWindow window;

    private long lastLoop;

    // wait after every repaint
    private int delay;

    public Game(String title, int width, int height) {
        this.window = GameWindow.getInstance(title, width, height);
        lastLoop = 0;
        delay = 10;
        FontManager.loadFont("fonts/PressStart2P-Regular.ttf", "PressStart2P-Regular");
    }

    public Game(String title, int width, int height, UpdateInterface updater, RenderInterface renderer) {
        this(title, width, height);
        setUpdater(updater);
        setRenderer(renderer);
    }

    public void setRenderer(RenderInterface renderer) {
        this.renderer = renderer;
    }

    public void setUpdater(UpdateInterface updater) {
        this.updater = updater;
    }

    public void run() {
        // load some font(s)

        while(true) {
            lastLoop = Timer.getTime();
            //update
            if(updater != null) {
                updater.update();
            }
            //render
            int height  = this.getWindow().getHeight();
            int width   = this.getWindow().getWidth();
            Graphics2D context = this.getWindow().getGraphicsContext();
            context.setColor(Color.black);
            context.fillRect(0, 0, width, height);

            if(renderer != null) {
                renderer.render(context);
            }

            this.getWindow().repaint();
            long delta = lastLoop+delay-Timer.getTime();
            if(delta > 0) {
                Timer.sleep(delta);
            }
        }
    }

}

