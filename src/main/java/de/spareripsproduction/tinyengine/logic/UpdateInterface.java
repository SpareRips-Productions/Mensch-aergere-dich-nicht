package de.spareripsproduction.tinyengine.logic;

/**
 * The UpdateInterface enables Objects, to get called during the update-Cycle of the Game
 *
 * @author Thomas Hampe <thomas@hampe.co>
 * @version 1.0
 * @since 2014-03-10
 */
public interface UpdateInterface {

    /**
     * This Method will called during the Update Cycle, <b>before</b> the rendering starts
     */
    public void update();
}
