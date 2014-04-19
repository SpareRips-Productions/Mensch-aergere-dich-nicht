package de.spareripsproduction.madn.client.graphics;

import java.awt.*;

/**
 * Created by marian on 12/03/14.
 */
public interface RenderAndUpdateable {

    /**
     * This Method will called during the Update Cycle, <b>before</b> the rendering starts
     */
    public void update();

    /**
     * This function gets called every circle and draws the object
     *
     * @param context on which we draw
     */
    public void render(Graphics2D g);
}
