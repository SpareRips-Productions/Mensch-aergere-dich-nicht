package de.spareripsproduction.madn.client.graphics;

import de.spareripsproduction.madn.client.logic.Player;

import java.awt.*;

/**
 * Created by marian on 12/03/14.
 */
public class SpawnField extends Field {
    public Player owner;

    public SpawnField(int x, int y, Player owner) {
        super(x, y);
        this.owner = owner;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {

    }
}
