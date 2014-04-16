package de.spareripsproduction.madn.client.graphics;

import java.awt.*;

/**
 * Created by marian on 12/03/14.
 */
public class NormalField extends Field {
    public NormalField(int x, int y) {
        this("sprites/normalField.png", x, y);
    }

    public NormalField(String spriteRef, int x, int y) {
        super(spriteRef, x, y);
    }

}
