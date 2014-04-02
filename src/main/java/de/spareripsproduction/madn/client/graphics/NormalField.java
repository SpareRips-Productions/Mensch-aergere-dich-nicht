package de.spareripsproduction.madn.client.graphics;

import java.awt.*;

/**
 * Created by marian on 12/03/14.
 */
public class NormalField extends Field {
    public NormalField(int x, int y) {
        super("normalField.png", x, y);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {
        this.render((Graphics2D) g);
    }
}
