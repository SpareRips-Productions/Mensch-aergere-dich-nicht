package de.spareripsproduction.madn.client.graphics;

import de.spareripsproduction.madn.client.logic.Player;
import java.awt.*;

/**
 * Created by marian on 12/03/14.
 */
public class GameFigure implements RenderAndUpdateable {

    public static final String COLOR_RED = "red";
    public static final String COLOR_YELLOW = "yellow";
    public static final String COLOR_BLUE = "blue";
    public static final String COLOR_GREEN = "green";


    public Player owner;
    public Color color;

    public GameFigure(Player owner, Color color) {
        this.owner = owner;
        this.color = color;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {

    }
}
