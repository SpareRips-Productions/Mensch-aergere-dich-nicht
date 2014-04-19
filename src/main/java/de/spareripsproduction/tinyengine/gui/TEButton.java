package de.spareripsproduction.tinyengine.gui;

import de.spareripsproduction.tinyengine.input.Mouse;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * A Button
 *
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

    /**
     * Construct with default colors
     *
     * @param label Label
     * @param x x-coordinate
     * @param y y-coordinate
     * @param width in pixel
     * @param height in pixel
     * @param borderSize border thickness in pixel
     * @param labelFont Font for the label
     */
    public TEButton(String label, int x, int y, int width, int height, int borderSize, Font labelFont) {
        this(label, x, y, width, height, borderSize, labelFont, Color.black, Color.green, Color.red);

    }

    /**
     * Default Constructor
     *
     * @param label Label
     * @param x x-coordinate
     * @param y y-coordinate
     * @param width in pixel
     * @param height in pixel
     * @param borderSize border thickness in pixel
     * @param labelFont Font for the label
     * @param color Color of the button
     * @param clickedColor border color when clicked
     * @param highlightColor color when button is hovered
     */
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

    /**
     * {@inheritDoc}
     */
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
        this.label.horizontalAlignCenter(this.getY() - 8, this.getY() + this.getHeight());
        this.label.render(context);

        context.setColor(color);
        context.setStroke(stroke);
    }

    /**
     * {@inheritDoc}
     */
    public void update() {
        super.update();

        // check if clicked inside in mouse
        if (Mouse.isClicked(MouseEvent.BUTTON1)) {
            Point p = Mouse.location();
            this.clicked = this.insideView(p);
        } else {
            this.clicked = false;
        }
        // check if mouse inside button
        this.highlighted = this.insideView(Mouse.location());
    }

    /**
     * Checks if button is clicked
     *
     * @return true if clicked
     */
    public boolean isClicked() {
        return this.clicked;
    }

    /**
     * Checks if button is highlighted
     *
     * @return true if highlighted
     */
    public boolean isHighlighted() {
        return this.highlighted;
    }

    /**
     *
     * @return Color of this button
     */
    public Color getColor() {
        return this.color;
    }

    /**
     *
     * @return color of this button in clicked state
     */
    public Color getClickedColor() {
        return this.clickedColor;
    }
}
