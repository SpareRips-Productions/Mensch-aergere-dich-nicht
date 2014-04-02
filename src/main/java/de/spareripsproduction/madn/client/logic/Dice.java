package de.spareripsproduction.madn.client.logic;

import de.spareripsproduction.madn.client.graphics.RenderAndUpdateable;
import de.spareripsproduction.tinyengine.graphics.RenderInterface;

import java.awt.*;
import java.util.Random;

/**
 * Created by marian on 12/03/14.
 */
public class Dice implements RenderAndUpdateable {


    private Random random;

    private int lastNumber;

    private int x,y;

    private int width,height;

    public Dice(int x, int y) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.random = new Random();
    }

    public int getNextNumber() {
        this.lastNumber = this.random.nextInt(6) + 1;
        return lastNumber;
    }


    @Override
    public void update() {

    }

    @Override
    public void render(Graphics2D g) {
        Color color = g.getColor();
        Stroke stroke = g.getStroke();

        g.setColor(Color.black);
        g.setStroke(new BasicStroke(5));
        g.drawRect(
                this.getX(),
                this.getY(),
                this.getWidth(),
                this.getHeight()
        );

        g.setColor(color);
        g.setStroke(stroke);
    }

    public int getLastNumber() {
        return lastNumber;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
