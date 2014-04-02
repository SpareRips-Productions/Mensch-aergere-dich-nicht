package de.spareripsproduction.madn.client.graphics;

import de.spareripsproduction.madn.client.logic.Player;

import java.awt.*;

/**
 * Created by marian on 12/03/14.
 */
public class HomeEntryField extends Field {
    public Field[] fields;

    public HomeEntryField(String spriteRef, int x, int y, int offsetX, int offsetY) {
        super(spriteRef, x, y);
        this.fields = new Field[4];
        for (int i = 1; i < 5; i++) {
            this.fields[i - 1] = new NormalField(offsetX + i * 50, offsetY + i * 50);
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {

    }
}
