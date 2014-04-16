package de.spareripsproduction.tinyengine.gui;

import de.spareripsproduction.tinyengine.input.Mouse;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * @author Thomas Hampe <thomas@hampe.co>
 * @version 1.0
 * @since 2014-03-21
 */
public class TEButton extends TEView {

    private int borderSize;

    protected TELabel label;

    private boolean clicked;

    private boolean highlighted;

    private Color clickedColor;

    private Color highlightColor;

    private Color color;

    public TEButton(String label, int x, int y, int width, int height, int borderSize, Font labelFont) {
        this(label, x, y, width, height, borderSize, labelFont, Color.black, Color.green, Color.red);

    }

    public TEButton(
            String label,
            int x, int y,
            int width,
            int height,
            int borderSize,
            Font labelFont,
            Color color,
            Color clickedColor,
            Color highlightColor
    ) {
        super(x, y, width, height);
        this.setBorderSize(borderSize);
        this.label = new TELabel(label, this.getX() + this.getBorderSize(), this.getY() + this.getBorderSize(), labelFont);
        this.clicked = false;
        this.clickedColor = clickedColor;
        this.highlightColor = highlightColor;
        this.color = color;
    }

    public int getBorderSize() {
        return this.borderSize;
    }

    public void setBorderSize(int borderSize) {
        this.borderSize = borderSize;
    }

    public void render(Graphics2D context) {
        Color color = context.getColor();
        Stroke stroke = context.getStroke();

        if (this.isClicked()) {
            context.setColor(this.clickedColor);
        } else if (this.isHighlighted()) {
            context.setColor(this.highlightColor);
        } else {
            context.setColor(this.color);
        }
        context.setStroke(new BasicStroke(this.getBorderSize()));
        context.drawRect(
                this.getX() + this.getBorderSize() / 2,
                this.getY() + this.getBorderSize() / 2,
                this.getWidth() - this.getBorderSize(),
                this.getHeight() - this.getBorderSize()
        );


        context.setColor(this.color);
        this.label.verticalAlignCenter(this.getX(), this.getX() + this.getWidth());
        this.label.horizontalAlignCenter(this.getY(),this.getY()+ this.getHeight());
        this.label.render(context);

        context.setColor(color);
        context.setStroke(stroke);
    }

    public void update() {
        super.update();

        // check if clicked inside in mouse
        if (Mouse.isClicked(MouseEvent.BUTTON1)) {
            Point p = Mouse.position(MouseEvent.BUTTON1);
            this.clicked = this.insideView(p);
        } else {
            this.clicked = false;
        }
        // check if mouse inside button
        this.highlighted = this.insideView(Mouse.location());
    }

    public boolean isClicked() {
        return this.clicked;
    }

    public boolean isHighlighted() {
        return this.highlighted;
    }

    public Color getColor() {
        return this.color;
    }

    public Color getHighlightColor() {
        return this.highlightColor;
    }

    public Color getClickedColor() {
        return this.clickedColor;
    }
}
