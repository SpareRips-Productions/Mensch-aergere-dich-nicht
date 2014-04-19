package de.spareripsproduction.tinyengine.graphics;

import java.awt.*;

/**
 * Enables objects to render things on the screen
 *
 * @author Thomas Hampe <thomas@hampe.co>
 * @version 1.0
 * @since 2014-03-10
 */
public interface RenderInterface {

    /**
     * This function gets called every circle and draws the object
     *
     * @param context on which we draw
     */
    public void render(Graphics2D context);
}
