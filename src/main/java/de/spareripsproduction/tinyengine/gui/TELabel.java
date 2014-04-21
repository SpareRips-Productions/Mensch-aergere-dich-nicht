package de.spareripsproduction.tinyengine.gui;

import de.spareripsproduction.tinyengine.GameWindow;
import de.spareripsproduction.tinyengine.Os;

import java.awt.*;

/**
 * Label
 *
 * @author Thomas Hampe <thomas@hampe.co>
 * @version 1.0
 * @since 2014-03-21
 */
public class TELabel extends TEView {

    private String text;

    protected Color textColor;

    private Font font;

    /**
     *
     * @param text
     * @param x
     * @param y
     * @param font
     */
    public TELabel(String text, int x, int y, Font font) {
        super(x, y, 0, 0);
        this.text = text;
        this.setFont(font);

    }

    /**
     * {@inheritDoc}
     */
    public int getHeight() {
        return this.getFont().getSize();
    }

    /**
     * {@inheritDoc}
     */
    public int getWidth() {
        Graphics context = this.getContext();
        return context.getFontMetrics(this.getFont()).stringWidth(this.text);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Font getFont() {
        return font;
    }

    public int getY() {
        return super.getY() + this.getHeight();
    }

    public void setFont(Font font) {
        this.font = font;
    }

    /**
     * {@inheritDoc}
     */
    public void render(Graphics2D context) {
        Font contextFont = context.getFont();
        context.setColor(this.textColor);
        if(Os.isWindows()) {
            this.setText(this.getText().replace("ä", "ae"));
            this.setText(this.getText().replace("ü", "ue"));
            this.setText(this.getText().replace("ö", "oe"));
            this.setText(this.getText().replace("ß", "ss"));

            this.setText(this.getText().replace("Ä", "Ae"));
            this.setText(this.getText().replace("Ü", "Ue"));
            this.setText(this.getText().replace("Ö", "Oe"));
        }

        context.setFont(this.getFont());
        context.drawString(this.text, this.getX(), this.getY());


        context.setFont(contextFont);
    }

    private Graphics getContext() {
        return GameWindow.getInstance().getGraphicsContext();
    }

}
