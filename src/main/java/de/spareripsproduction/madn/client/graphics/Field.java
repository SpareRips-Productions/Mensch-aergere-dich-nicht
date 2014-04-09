package de.spareripsproduction.madn.client.graphics;

import de.spareripsproduction.tinyengine.entity.Entity;

import java.awt.*;

/**
 * Created by marian on 12/03/14.
 */
public abstract class Field extends Entity implements RenderAndUpdateable {
    public GameFigure gameFigure;

    public Field(String spriteRef, int x, int y) {
        super(spriteRef, x, y);
    }

    public void update() {
        if(gameFigure != null) {
            gameFigure.update();
            gameFigure.setLocation(this.x, this.y - 50);
        }
        super.update();
    }

    public void render(Graphics2D g) {
        super.render(g);

        if(gameFigure != null) {
            gameFigure.render(g);
        }
    }
}
