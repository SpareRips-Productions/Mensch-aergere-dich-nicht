package de.spareripsproduction.tinyengine.gui;

import de.spareripsproduction.tinyengine.Timer;

import java.awt.*;

/**
 * @author Thomas Hampe <thomas@hampe.co>
 * @version 1.0
 * @since 2014-03-23
 */
public class TESwitch extends TEButton {

    private boolean state = false;

    private long lastClick;

    /**
     * constructor with default state off
     *
     * @param x in pixel
     * @param y in pixel
     * @param width in pixel
     * @param height in pixel
     * @param borderSize in pixel
     * @param font Font
     */
    public TESwitch(int x, int y, int width, int height, int borderSize, Font font) {
        this(x, y, width, height, borderSize, font, false);
    }

    /**
     * Default Constructor
     *
     * @param x in pixel
     * @param y in pixel
     * @param width in pixel
     * @param height in pixel
     * @param borderSize in pixel
     * @param font Font
     * @param state off = false, on = true
     */
    public TESwitch(int x, int y, int width, int height, int borderSize, Font font, boolean state) {
        super((state) ? "0" : "1", x, y, width, height, borderSize, font);
        this.state = state;
        this.lastClick = 0;
    }

    /**
     * {@inheritDoc}
     */
    public void render(Graphics2D context) {
        Color color = context.getColor();
        Stroke stroke = context.getStroke();

        if (this.isClicked()) {
            context.setColor(this.getClickedColor());
        } else {
            context.setColor(this.getColor());
        }
        context.setStroke(new BasicStroke(this.getBorderSize()));
        context.drawRect(
                this.getX() + this.getBorderSize() / 2,
                this.getY() + this.getBorderSize() / 2,
                this.getWidth() - this.getBorderSize(),
                this.getHeight() - this.getBorderSize()
        );

        if (this.state) {
            this.renderEnabledState(context);
        } else {
            this.renderDisabledState(context);
        }

        context.setColor(color);
        context.setStroke(stroke);


    }

    /**
     * {@inheritDoc}
     */
    public void update() {
        super.update();
        if (this.isClicked() && (Timer.getTime() - this.lastClick) > 200) {
            this.state = !this.state;
            this.lastClick = Timer.getTime();

        }

    }

    private void renderEnabledState(Graphics2D context) {
        context.setColor(this.getColor());
        context.fillRect(
                this.getX() + this.getWidth() / 2,
                this.getY() + this.getBorderSize(),
                this.getWidth() / 2 - this.getBorderSize(),
                this.getHeight() - 2 * this.getBorderSize()
        );
        context.setColor(this.getClickedColor());
        context.fillRect(
                this.getX() + this.getBorderSize(),
                this.getY() + this.getBorderSize(),
                this.getWidth() / 2 - this.getBorderSize(),
                this.getHeight() - 2 * this.getBorderSize()
        );

        context.setColor(this.getColor());
        this.label.setText("1");
        this.label.horizontalAlignCenter(this.getY(), this.getY() + this.getHeight());
        this.label.verticalAlignCenter(this.getX(), this.getX() + this.getWidth() / 2);
        this.label.render(context);

    }

    private void renderDisabledState(Graphics2D context) {
        context.setColor(this.getColor());
        context.fillRect(
                this.getX() + this.getBorderSize(),
                this.getY() + this.getBorderSize(),
                this.getWidth() / 2 - this.getBorderSize(),
                this.getHeight() - 2 * this.getBorderSize()
        );
        this.label.setText("0");
        this.label.horizontalAlignCenter(this.getY(), this.getY() + this.getHeight());
        this.label.verticalAlignCenter(
                this.getX() + this.getWidth() / 2,
                this.getX() + this.getWidth()
        );
        this.label.render(context);


    }

}
