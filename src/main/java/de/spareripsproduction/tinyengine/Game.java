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

    private long lastLoop;

    // wait after every repaint
    private int delay;

    public Game(String title, int width, int height) {
        GameWindow.singleton(title, width, height);
        lastLoop = 0;
        delay = 10;
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

        //FontManager.loadFont("fonts/PressStart2P-Regular.ttf", "PressStart2P-Regular");
        while(true) {
            lastLoop = Timer.getTime();
            //update
            if(updater != null) {
                updater.update();
            }
            //render
            int height = GameWindow.singleton().getHeight();
            int width = GameWindow.singleton().getWidth();
            Graphics2D context = GameWindow.singleton().getGraphicsContext();
            context.setColor(Color.black);
            context.fillRect(0, 0, width, height);

            if(renderer != null) {
                renderer.render(context);
            }

            GameWindow.singleton().repaint();
            long delta = lastLoop+delay-Timer.getTime();
            if(delta > 0) {
                Timer.sleep(delta);
            }
        }
    }

}

