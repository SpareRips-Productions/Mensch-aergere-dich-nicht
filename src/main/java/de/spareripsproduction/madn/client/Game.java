package de.spareripsproduction.madn.client;

import de.spareripsproduction.madn.client.scene.*;
import de.spareripsproduction.tinyengine.FontManager;
import de.spareripsproduction.tinyengine.graphics.RenderInterface;
import de.spareripsproduction.tinyengine.logic.Fps;
import de.spareripsproduction.tinyengine.logic.GitVersion;
import de.spareripsproduction.tinyengine.logic.UpdateInterface;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by marian on 12/03/14.
 */
public class Game
        extends de.spareripsproduction.tinyengine.Game
    implements RenderInterface, UpdateInterface

{
    public static final String ENV_DEV = "dev";
    public static final String ENV_PROD = "prod";

    private GitVersion version;

    private String environment = ENV_DEV;

    private Fps fps;

    private HashMap<String,Scene> scenes;

    private Scene activeScene;

    private static Game instance;

    public Game() {
        super("Mensch ärgere dich nicht", 800, 600);
        FontManager.loadFont("fonts/Arizonia-Regular.ttf", "Arizonia-Regular");
        FontManager.loadFont("fonts/Arizonia-Regular.ttf", "Arizonia-Regular");


        this.version = new GitVersion();
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

    public static Game getInstance() {
        if(Game.instance == null) {
            Game.instance = new Game();
        }
        return Game.instance;
    }

    public static Game getInstance(String environment) {
        if(Game.instance == null) {
            Game.instance = new Game(environment);
        }
        return Game.instance;
    }

    public void render(Graphics2D context) {
        this.activeScene.render(context);
        if(this.environment.equals(Game.ENV_DEV)) {
            this.fps.render(context);
            this.version.render(context);
        }

    }

    public void update() {
        this.activeScene.update();
    }

    public boolean loadScene(String sceneName) {
        if(this.scenes.containsKey(sceneName)) {
            // check for the first scene
            if(this.activeScene != null) {
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
        this.scenes.put(Scene.SCENE_LOBBY, new LobbyScene());
        this.scenes.put(Scene.SCENE_MENU, new MenuScene());
        this.scenes.put(Scene.SCENE_SCORE, new ScoreScene());
        this.scenes.put(Scene.SCENE_SETTINGS, new SettingsScene());
    }
}
