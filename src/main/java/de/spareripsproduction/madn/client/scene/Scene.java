package de.spareripsproduction.madn.client.scene;

import de.spareripsproduction.madn.client.graphics.RenderAndUpdateable;

/**
 * The base scene
 *
 * Created by marian on 12/03/14.
 */
public abstract class Scene implements RenderAndUpdateable {

    /**
     * Game scene, actual game play happens here
     */
    public static final String SCENE_GAME = "game";

    /**
     * Menu scene, the Menu
     */
    public static final String SCENE_MENU = "menu";

    /**
     * Score scene, after the game ended
     */
    public static final String SCENE_SCORE = "score";

    /**
     * Intro scene, first scene
     */
    public static final String SCENE_INTRO = "intro";

    /**
     * Settings scene, configure the player count and player names
     */
    public static final String SCENE_SETTINGS = "settings";
    public static final String SCENE_CREDITS = "credits";

    /**
     * Loads this scene, is called when the scene is to be showed
     */
    public abstract void load();

    /**
     * Unloads this scene, is called when the scene disappears
     */
    public abstract void unload();
}
