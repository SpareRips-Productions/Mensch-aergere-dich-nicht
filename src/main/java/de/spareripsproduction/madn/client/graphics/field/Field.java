package de.spareripsproduction.madn.client.graphics.field;

import de.spareripsproduction.madn.client.graphics.BoardEntity;
import de.spareripsproduction.madn.client.graphics.RenderAndUpdateable;

/**
 * Created by marian on 12/03/14.
 */
public abstract class Field extends BoardEntity implements RenderAndUpdateable {

    public static final String SPRITE_RED = "sprites/SpawnFieldRed.png";
    public static final String SPRITE_YELLOW = "sprites/SpawnFieldYellow.png";
    public static final String SPRITE_BLUE = "sprites/SpawnFieldBlue.png";
    public static final String SPRITE_GREEN = "sprites/SpawnFieldGreen.png";

    public Field(String spriteRef, int id) {
        super(spriteRef, id);
    }

}
