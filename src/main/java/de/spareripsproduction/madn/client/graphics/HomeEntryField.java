package de.spareripsproduction.madn.client.graphics;

import de.spareripsproduction.madn.client.logic.Player;

import java.awt.*;

/**
 * Created by marian on 12/03/14.
 */
public class HomeEntryField extends SpawnField {
    public Field[] fields;

    public HomeEntryField(String spriteRef, int x, int y, int offsetX, int offsetY) {
        super("sprites/normalField.png", x, y);
        this.fields = new Field[4];
        for (int i = 1; i < 5; i++) {
            this.fields[i - 1] = new NormalField(spriteRef, (offsetX * i * 45)+x, (offsetY * i * 45) + y);
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics2D g) {
        super.render(g);
        for(Field f: this.fields) {
            f.render(g);
        }
    }
}
