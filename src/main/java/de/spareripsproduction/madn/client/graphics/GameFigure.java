package de.spareripsproduction.madn.client.graphics;

import de.spareripsproduction.madn.client.logic.Player;
import java.awt.*;

/**
 * Created by marian on 12/03/14.
 */
public class GameFigure implements RenderAndUpdateable {
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
