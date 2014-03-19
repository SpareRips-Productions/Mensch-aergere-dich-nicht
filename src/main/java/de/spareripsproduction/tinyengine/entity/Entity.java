package de.spareripsproduction.tinyengine.entity;

import de.spareripsproduction.tinyengine.graphics.Sprite;
import de.spareripsproduction.tinyengine.graphics.SpriteStore;

import java.awt.*;

/**
 * @author Thomas Hampe <thomas@hampe.co>
 * @version 1.0
 * @since 2014-03-08
 */
public class Entity {

    protected float x,y;
    protected float width,height;

    protected Sprite sprite;

    public Entity(String spriteRef) {
        this(spriteRef, 0, 0);
    }

    public Entity(String spriteRef, float x, float y) {
        this(
                spriteRef,
                x,
                y,
                (float) SpriteStore.singleton().get(spriteRef).getWidth(),
                (float) SpriteStore.singleton().get(spriteRef).getHeight()
        );
    }

    public Entity(String spriteRef, float x, float y, float width, float height){
        this.sprite = SpriteStore.singleton().get(spriteRef);
        this.setLocation(x,y);
        this.setSize(width,height);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
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
        this.setLocation(this.getX()+dX,this.getY()+dY);
    }

    public Rectangle getHitBox() {
        return new Rectangle((int)x,(int)y,(int)width,(int)height);
    }

    public boolean collidesWith(Entity e) {
        return getHitBox().intersects(e.getHitBox());
    }


    public void draw(Graphics context) {
        sprite.draw(context, (int) this.getX(), (int) this.getY());
    }

    public void update(){}

}
