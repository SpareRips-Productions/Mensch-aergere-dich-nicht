package de.spareripsproduction.madn.client;

import de.spareripsproduction.madn.client.scene.*;
import de.spareripsproduction.tinyengine.graphics.RenderInterface;
import de.spareripsproduction.tinyengine.logic.Fps;
import de.spareripsproduction.tinyengine.logic.UpdateInterface;

import java.awt.*;
import java.util.HashMap;

/**
 * This Class is responsible for showing the correct Scene (Game, Settings, Credits, etc)
 *
 * Created by marian on 12/03/14.
 *
 *
 */
public class Game
        extends de.spareripsproduction.tinyengine.Game
        implements RenderInterface, UpdateInterface

{
    /**
     * DEV environment shows FPS
     */
    public static final String ENV_DEV = "DEV";

    /**
     * PROD does not show FPS
     */
    public static final String ENV_PROD = "PROD";

    /**
     * for testing purposes
     */
    public static final String ENV_TEST = "TEST";

    private String environment = ENV_DEV;

    private Fps fps;

    private HashMap<String, Scene> scenes;

    private Scene activeScene;

    private static Game instance;

    /**
     * Default Constructor
     */
    public Game() {
        super("Mensch ärgere dich nicht", 800, 600);


        this.fps = new Fps();
        this.setRenderer(this);
        this.setUpdater(this);

        this.initScenes();
        this.loadScene(Scene.SCENE_INTRO);

    }

    private Game(String environment) {
        this();
        this.environment = environment;
    }

    /**
     * Singleton of the Game
     *
     * @return Singleton
     */
    public static Game getInstance() {
        if (Game.instance == null) {
            Game.instance = new Game();
        }
        return Game.instance;
    }

    /**
     * Singleton of the Game with environment variable
     *
     * @return Singleton
     */
    public static Game getInstance(String environment) {
        if (Game.instance == null) {
            Game.instance = new Game(environment);
        }
        return Game.instance;
    }

    /**
     * {@inheritDoc}
     */
    public void render(Graphics2D context) {
        this.activeScene.render(context);
        if (this.environment.equals(Game.ENV_DEV) || this.environment.equals(Game.ENV_TEST)) {
            this.fps.render(context);
        }

    }

    /**
     * {@inheritDoc}
     */
    public void update() {
        this.activeScene.update();
    }

    /**
     * Loads a specific scene, and unloads the old one
     *
     * @param sceneName Name of the Scene
     * @return true if Scene was found false if not
     */
    public boolean loadScene(String sceneName) {
        if (this.scenes.containsKey(sceneName)) {
            // check for the first scene
            if (this.activeScene != null) {
                this.activeScene.unload();
            }
            this.activeScene = this.scenes.get(sceneName);
            this.activeScene.load();
            this.activeScene.update();
            return true;
        }
        return false;
    }

    private void initScenes() {
        //init scenes
        this.scenes = new HashMap<String, Scene>();
        this.scenes.put(Scene.SCENE_GAME, new GameScene());
        this.scenes.put(Scene.SCENE_INTRO, new IntroScene());
        this.scenes.put(Scene.SCENE_MENU, new MenuScene());
        this.scenes.put(Scene.SCENE_SCORE, new ScoreScene());
        this.scenes.put(Scene.SCENE_SETTINGS, new SettingsScene());
        this.scenes.put(Scene.SCENE_CREDITS, new CreditsScene());
    }

    /**
     *
     * @return current environment
     */
    public String getEnvironment() {
        return environment;
    }
}
