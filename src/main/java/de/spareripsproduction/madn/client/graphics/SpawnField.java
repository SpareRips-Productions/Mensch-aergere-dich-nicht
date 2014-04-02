package de.spareripsproduction.madn.client.graphics;

import de.spareripsproduction.madn.client.logic.Player;

import java.awt.*;

/**
 * Created by marian on 12/03/14.
 */
public class SpawnField extends Field {

    public static final String SPRITE_RED = "sprites/SpawnFieldRed.png";
    public static final String SPRITE_YELLOW = "sprites/SpawnFieldYellow.png";
    public static final String SPRITE_BLUE = "sprites/SpawnFieldBlue.png";
    public static final String SPRITE_GREEN = "sprites/SpawnFieldGreen.png";

    public SpawnField(String spriteRef, int x, int y) {
        super(spriteRef, x, y);
    }

    public SpawnField initRedSpawnField(int x, int y) {
        return new SpawnField("", x, y);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {

    }
}
