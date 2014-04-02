package de.spareripsproduction.madn.client.graphics;

import java.awt.*;

/**
 * Created by marian on 12/03/14.
 */
public class SpawnField extends Field {



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
