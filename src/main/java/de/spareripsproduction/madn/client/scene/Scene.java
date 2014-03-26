package de.spareripsproduction.madn.client.scene;

import de.spareripsproduction.madn.client.graphics.RenderAndUpdateable;

/**
 * Created by marian on 12/03/14.
 */
public abstract class Scene implements RenderAndUpdateable {

    public static final String SCENE_GAME   = "game";
    public static final String SCENE_LOBBY  = "lobby";
    public static final String SCENE_MENU   = "menu";
    public static final String SCENE_SCORE  = "score";
    public static final String SCENE_INTRO  = "intro";
    public static final String SCENE_SETTINGS = "settings";

    public abstract void load();
    public abstract void unload();
}
