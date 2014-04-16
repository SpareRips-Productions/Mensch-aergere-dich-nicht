package de.spareripsproduction.tinyengine.entity;

import de.spareripsproduction.tinyengine.graphics.RenderInterface;
import de.spareripsproduction.tinyengine.graphics.Sprite;
import de.spareripsproduction.tinyengine.graphics.SpriteStore;
import de.spareripsproduction.tinyengine.logic.UpdateInterface;

import java.awt.*;

/**
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
        return (int)this.x;
    }

    public int getIntY() {
        return (int)this.y;
    }

    public int getIntHeight() {
        return (int)this.height;
    }

    public int getIntWidth() {
        return (int)this.width;
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

    public void setSize(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public void move(float dX, float dY) {
        this.setLocation(this.getX() + dX, this.getY() + dY);
    }

    public Rectangle getHitBox() {
        return new Rectangle((int) this.getX(), (int) this.getY(), (int) this.getWidth(), (int) this.getHeight());
    }

    public boolean collidesWith(Entity e) {
        return this.getHitBox().intersects(e.getHitBox());
    }


    public void render(Graphics2D context) {
        this.sprite.render(context, (int) this.getX(), (int) this.getY());
    }

    public void update() {

    }


}
