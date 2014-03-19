package de.spareripsproduction.tinyengine.logic;

import de.spareripsproduction.tinyengine.Timer;
import de.spareripsproduction.tinyengine.graphics.RenderInterface;

import java.awt.*;

/**
 * @author Thomas Hampe <thomas@hampe.co>
 * @version 1.0
 * @since 2014-03-11
 */
public class Fps implements RenderInterface{

    private static Fps instance;

    private long lastUpdated;

    private int framesCount;

    private int fps;

    private Fps() {
        this.lastUpdated = Timer.getTime();
        this.framesCount = 0;
        this.fps = 0;
    }

    public static Fps singleton() {
        if(Fps.instance == null){
            Fps.instance = new Fps();
        }
        return Fps.instance;
    }

    public void render(Graphics2D context) {
        updateFps();
        //remember old states
        Color color = context.getColor();
        context.setColor(Color.green);
        context.drawString(getFps()+"FPS", 1, 15);

        //reset
        context.setColor(color);
    }

    public int getFps() {
        return fps != 0 ? fps : 100;
    }

    private void updateFps() {
        long now = Timer.getTime();
        framesCount++;
        if(now - lastUpdated >= 1000) {
            fps = framesCount;
            framesCount = 0;
            lastUpdated = now;
        }
    }

}
