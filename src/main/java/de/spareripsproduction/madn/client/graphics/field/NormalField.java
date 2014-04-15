package de.spareripsproduction.madn.client.graphics.field;

/**
 * Created by marian on 12/03/14.
 */
public class NormalField extends Field {
    public NormalField(int id) {
        this("sprites/normalField.png", id);
    }

    public NormalField(String spriteRef, int id) {
        super(spriteRef, id);
    }

}
