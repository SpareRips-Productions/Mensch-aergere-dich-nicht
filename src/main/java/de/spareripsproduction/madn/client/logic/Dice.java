package de.spareripsproduction.madn.client.logic;

import de.spareripsproduction.madn.client.graphics.Board;
import de.spareripsproduction.madn.client.graphics.RenderAndUpdateable;
import de.spareripsproduction.tinyengine.graphics.RenderInterface;
import de.spareripsproduction.tinyengine.input.Mouse;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Random;

/**
 * Created by marian on 12/03/14.
 */
public class Dice implements RenderAndUpdateable {


    private Random random;

    private int lastNumber;

    private int x,y;

    private int width,height;

    protected boolean hover, clicked, locked;

    public Dice(int x, int y) {
        this.x = x;
        this.y = y;
        this.height = 40;
        this.width = 40;
        this.random = new Random();
        this.lastNumber = 0;
        this.reset();
        this.locked = false;
    }

    public int getNextNumber() {
        this.lastNumber = this.random.nextInt(6) + 1;
        return lastNumber;
    }

    public void reset() {
        this.lastNumber = 0;
    }


    @Override
    public void update() {

        this.hover = !this.locked && this.insideView(Mouse.location());

        if (Mouse.isClicked(MouseEvent.BUTTON1) && isHover() && !this.locked) {
            this.clicked = true;
            this.locked = true;
            getNextNumber();
        } else {
            this.clicked = false;
        }

    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void render(Graphics2D g) {
        Color color = g.getColor();
        Stroke stroke = g.getStroke();

        if(isHover()) {
            g.setColor(Color.magenta);

        }else {
            switch (Board.getInstance().getActivePlayer().getType()) {
                case Player.RED_PLAYER:
                    g.setColor(Color.RED);
                    break;
                case Player.BLUE_PLAYER:
                    g.setColor(Color.blue);
                    break;
                case Player.GREEN_PLAYER:
                    g.setColor(Color.green);
                    break;
                case Player.YELLOW_PLAYER:
                    g.setColor(Color.yellow);
                    break;

            }
        }



        g.setStroke(new BasicStroke(2));
        g.drawRect(
                this.getX()-1,
                this.getY()-1,
                this.getWidth()-2,
                this.getHeight()-2
        );



        g.setColor(Color.black);

        switch (this.lastNumber) {
            case 1:
                this.renderOne(g);
                break;
            case 2:
                this.renderTwo(g);
                break;
            case 3:
                this.renderThree(g);
                break;
            case 4:
                this.renderFour(g);
                break;
            case 5:
                this.renderFive(g);
                break;
            case 6:
                this.renderSix(g);
                break;
        }

        g.setColor(color);
        g.setStroke(stroke);
    }

    protected void renderOne(Graphics2D g) {
        int radius = 5;
        this.renderCircle(
                g,
                this.getX()-1+(this.getWidth()-2)/2-radius,
                this.getY()-1+(this.getHeight()-2)/2-radius,
                radius
        );
    }

    protected void renderTwo(Graphics2D g) {
        this.renderCircle(g, this.getX()+1-2+5, this.getY()+1-2+5, 5);
        this.renderCircle(g, this.getX()+this.getWidth()-2-15, this.getY()+this.getWidth()-2-15, 5);
    }

    protected void renderThree(Graphics2D g) {
        this.renderOne(g);
        this.renderTwo(g);
    }

    protected void renderFour(Graphics2D g) {
        this.renderTwo(g);
        this.renderCircle(g, this.getX()+1-2+5, this.getY()+this.getWidth()-2-15, 5);
        this.renderCircle(g, this.getX()+this.getWidth()-2-15, this.getY()+1-2+5, 5);

    }

    protected void renderFive(Graphics2D g) {
        this.renderFour(g);
        this.renderOne(g);
    }

    protected void renderSix(Graphics2D g) {
        int radius = 5;
        this.renderFour(g);
        this.renderCircle(
                g,
                this.getX()-1+(this.getWidth()-2)/2-radius,
                this.getY()+this.getWidth()-2-15,
                radius
        );
        this.renderCircle(
                g,
                this.getX()-1+(this.getWidth()-2)/2-radius,
                this.getY()+1-2+5,
                radius
        );
    }

    protected void renderCircle(Graphics2D g, int x, int y, int radius) {
        g.fillOval(x, y, radius*2, radius*2);
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

    protected boolean isHover() {
        return hover;
    }

    protected boolean isClicked() {
        return clicked;
    }

    protected boolean insideView(Point p) {
        return (p.getX() >= this.getX() && p.getX() <= this.getX() + this.getWidth())
                && (p.getY() >= this.getY() && p.getY() <= this.getY() + this.getHeight());
    }

    public void unlock() {
        this.locked = false;
    }

    public boolean isLocked() {
        return locked;
    }

    public void lock() {
        this.locked = true;
    }
}
