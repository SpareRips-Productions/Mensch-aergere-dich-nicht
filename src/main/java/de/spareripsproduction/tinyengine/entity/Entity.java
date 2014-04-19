package de.spareripsproduction.tinyengine.entity;

import de.spareripsproduction.tinyengine.graphics.RenderInterface;
import de.spareripsproduction.tinyengine.graphics.Sprite;
import de.spareripsproduction.tinyengine.graphics.SpriteStore;
import de.spareripsproduction.tinyengine.logic.UpdateInterface;

import java.awt.*;

/**
 * Entity Object
 *
 * @author Thomas Hampe <thomas@hampe.co>
 * @version 1.0
 * @since 2014-03-08
 */
public class Entity implements RenderInterface, UpdateInterface {

    protected float x, y;
    protected float width, height;

    protected Sprite sprite;

    public Entity(String spriteRef) {
        this(spriteRef, 0, 0);
    }

    public Entity(String spriteRef, float x, float y) {
        this(
                spriteRef,
                x,
                y,
                (float) SpriteStore.getInstance().get(spriteRef).getWidth(),
                (float) SpriteStore.getInstance().get(spriteRef).getHeight()
        );
    }

    public Entity(String spriteRef, float x, float y, float width, float height) {
        this.sprite = SpriteStore.getInstance().get(spriteRef);
        this.setLocation(x, y);
        this.setSize(width, height);
    }

    public int getIntX() {
        return (int) this.getX();
    }

    public int getIntY() {
        return (int) this.getY();
    }

    public int getIntHeight() {
        return (int) this.height;
    }

    public int getIntWidth() {
        return (int) this.width;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public float getHeight() {
        return this.height;
    }

    public float getWidth() {
        return this.width;
    }

    public void setLocation(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Sets the Size
     *
     * @param width in pixel
     * @param height in pixel
     */
    public void setSize(float width, float height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Moves
     *
     * @param dX delta length
     * @param dY delta height
     */
    public void move(float dX, float dY) {
        this.setLocation(this.getX() + dX, this.getY() + dY);
    }

    protected Rectangle getHitBox() {
        return new Rectangle((int) this.getX(), (int) this.getY(), (int) this.getWidth(), (int) this.getHeight());
    }

    /**
     * Collision detection
     *
     * @param e other entity
     * @return true if entities collide, false if not
     */
    public boolean collidesWith(Entity e) {
        return this.getHitBox().intersects(e.getHitBox());
    }

    /**
     * {@inheritDoc}
     */
    public void render(Graphics2D context) {
        this.sprite.render(context, (int) this.getX(), (int) this.getY());
    }

    /**
     * {@inheritDoc}
     */
    public void update() {

    }


}
