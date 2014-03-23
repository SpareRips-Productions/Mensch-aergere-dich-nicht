package de.spareripsproduction.tinyengine.logic;

import de.spareripsproduction.tinyengine.Timer;
import de.spareripsproduction.tinyengine.graphics.RenderInterface;
import de.spareripsproduction.tinyengine.gui.TELabel;

import java.awt.*;
import java.awt.datatransfer.StringSelection;

/**
 * @author Thomas Hampe <thomas@hampe.co>
 * @version 1.0
 * @since 2014-03-11
 */
public class Fps implements RenderInterface{

    private long lastUpdated;

    private int framesCount;

    private int fps;

    private TELabel fpsLabel;

    public Fps() {
        this.lastUpdated = Timer.getTime();
        this.framesCount = 0;
        this.fps = 0;
        this.fpsLabel = new TELabel("100FPS", 1, 15, new Font("PressStart2P-Regular", Font.PLAIN, 12));
    }

    public void render(Graphics2D context) {
        updateFps();
        //remember old states
        Color color = context.getColor();
        context.setColor(Color.green);
        this.fpsLabel.render(context);

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
        this.fpsLabel.setText(String.format("%dFPS", this.getFps()));
    }

}
