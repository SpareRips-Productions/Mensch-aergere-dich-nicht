package de.spareripsproduction.tinyengine.logic;

import de.spareripsproduction.tinyengine.FontManager;
import de.spareripsproduction.tinyengine.Timer;
import de.spareripsproduction.tinyengine.graphics.RenderInterface;
import de.spareripsproduction.tinyengine.gui.TELabel;

import java.awt.*;

/**
 * Objects from this Class, can measure the current <b>FPS</b> of the Game
 *
 * @author Thomas Hampe <thomas@hampe.co>
 * @version 1.0
 * @since 2014-03-11
 */
public class Fps implements RenderInterface {

    private long lastUpdated;

    private int framesCount;

    private int fps;

    private TELabel fpsLabel;

    /**
     * Constructs the FPS-Meter
     */
    public Fps() {
        this.lastUpdated = Timer.getTime();
        this.framesCount = 0;
        this.fps = 0;
        this.fpsLabel = new TELabel("100FPS", 1, 1, FontManager.getFont(FontManager.FONT_PRESS_START_2P, 12));
    }

    /**
     * {@inheritDoc}
     */
    public void render(Graphics2D context) {
        this.updateFps();
        //remember old states
        Color color = context.getColor();
        context.setColor(Color.green);

        this.fpsLabel.render(context);

        //reset
        context.setColor(color);
    }

    /**
     * Calculates the current <i>Frame per Seconds</i> of the Game
     *
     * @return Frames Per Second
     */
    public int getFps() {
        return this.fps != 0 ? this.fps : 100;
    }


    private void updateFps() {
        long now = Timer.getTime();
        this.framesCount++;
        if (now - lastUpdated >= 1000) {
            this.fps = this.framesCount;
            this.framesCount = 0;
            this.lastUpdated = now;
        }
        this.fpsLabel.setText(String.format("%dFPS", this.getFps()));
    }

}
