package de.spareripsproduction.madn.client.graphics.field;

import de.spareripsproduction.madn.client.graphics.BoardEntity;
import de.spareripsproduction.madn.client.graphics.RenderAndUpdateable;

/**
 * Created by marian on 12/03/14.
 */
public abstract class Field extends BoardEntity implements RenderAndUpdateable {

    /**
     * Reference to the sprite image of the <b>normal</b> <code>Field</code>
     */
    public static final String SPRITE_NORMAL = "sprites/normalField.png";

    /**
     * Reference to the sprite image of the <b>red</b> <code>Field</code>
     */
    public static final String SPRITE_RED = "sprites/SpawnFieldRed.png";

    /**
     * Reference to the sprite image of the <b>yellow</b> <code>Field</code>
     */
    public static final String SPRITE_YELLOW = "sprites/SpawnFieldYellow.png";

    /**
     * Reference to the sprite image of the <b>blze</b> <code>Field</code>
     */
    public static final String SPRITE_BLUE = "sprites/SpawnFieldBlue.png";

    /**
     * Reference to the sprite image of the <b>green</b> <code>Field</code>
     */
    public static final String SPRITE_GREEN = "sprites/SpawnFieldGreen.png";

    public Field(String spriteRef, int id) {
        super(spriteRef, id);
    }

}
