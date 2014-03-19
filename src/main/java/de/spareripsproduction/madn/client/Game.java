package de.spareripsproduction.madn.client;

import de.spareripsproduction.tinyengine.graphics.RenderInterface;
import de.spareripsproduction.tinyengine.input.Mouse;
import de.spareripsproduction.tinyengine.logic.Fps;
import de.spareripsproduction.tinyengine.logic.Version;

import java.awt.*;

/**
 * Created by marian on 12/03/14.
 */
public class Game
        extends de.spareripsproduction.tinyengine.Game
    implements RenderInterface
{
    public static final String ENV_DEV = "dev";
    public static final String ENV_PROD = "prod";

    private Version version;

    private String environment = ENV_DEV;

    private Fps fps;

    public Game() {
        super("Mensch ärgere dich nicht", 800, 600);
        this.version = new Version("0.0.1", 1, "The Wall");
        this.fps = Fps.singleton();
        this.setRenderer(this);
    }

    public Game(String environment) {
        this();
        this.environment = environment;
    }

    public void render(Graphics2D context) {
        if(this.environment.equals(Game.ENV_DEV)) {
            this.fps.render(context);
            this.version.render(context);
        }

    }
}
