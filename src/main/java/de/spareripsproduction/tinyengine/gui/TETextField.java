package de.spareripsproduction.tinyengine.gui;

import de.spareripsproduction.tinyengine.GameWindow;
import de.spareripsproduction.tinyengine.input.Mouse;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

/**
 * Textfield
 *
 * @author Thomas Hampe <thomas@hampe.co>
 * @version 1.0
 * @since 2014-03-25
 */
public class TETextField extends TEView implements KeyListener {

    protected TELabel label;

    public boolean active;

    protected boolean hover;

    protected int size;

    protected int borderSize;

    protected Color borderColor;

    protected Color activeBorderColor;

    protected Color hoverBorderColor;

    protected Color backgroundColor;

    protected Color textColor;

    /**
     * Default Constructor
     *
     * @param text
     * @param x
     * @param y
     * @param width
     * @param height
     * @param font
     * @param size
     * @param borderSize
     * @param borderColor
     * @param backgroundColor
     * @param textColor
     */
    public TETextField(
            String text,
            int x,
            int y,
            int width,
            int height,
            Font font,
            int size,
            int borderSize,
            Color borderColor,
            Color backgroundColor,
            Color textColor
    ) {
        super(x, y, width, height);
        this.label = new TELabel(text, x, y, font);
        this.active = false;
        this.size = size;
        this.borderSize = borderSize;
        this.borderColor = borderColor;
        this.activeBorderColor = Color.lightGray;
        this.hoverBorderColor = Color.red;
        this.backgroundColor = backgroundColor;
        this.textColor = textColor;

        //add Keyboard Listener
        GameWindow.getInstance().addKeyListener(this);
    }

    /**
     * constructor with default colors
     *
     * @param text
     * @param x
     * @param y
     * @param width
     * @param height
     * @param font
     * @param size
     * @param borderSize
     */
    public TETextField(
            String text,
            int x,
            int y,
            int width,
            int height,
            Font font,
            int size,
            int borderSize
    ) {
        this(text, x, y, width, height, font, size, borderSize, Color.black, Color.white, Color.black);
    }

    /**
     * {@inheritDoc}
     */
    public void update() {
        if (Mouse.isClicked(MouseEvent.BUTTON1)) {
            Point p = Mouse.position(MouseEvent.BUTTON1);
            this.active = this.insideView(p);
        }
        this.hover = this.insideView(Mouse.location());


    }


    /**
     * {@inheritDoc}
     */
    public void render(Graphics2D context) {
        Color color = context.getColor();
        Stroke stroke = context.getStroke();

        super.render(context);

        //background
        Color background = (this.isActive()) ? this.getBackgroundColor() : this.getActiveBorderColor();
        context.setColor(background);
        context.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());

        //border
        if (this.isActive()) {
            context.setColor(this.activeBorderColor);
        } else if (this.isHover()) {
            context.setColor(this.hoverBorderColor);
        } else {
            context.setColor(this.borderColor);
        }
        context.setStroke(new BasicStroke(this.getBorderSize()));
        context.drawRect(
                this.getX() + this.getBorderSize() / 2,
                this.getY() + this.getBorderSize() / 2,
                this.getWidth() - this.getBorderSize(),
                this.getHeight() - this.getBorderSize()
        );

        //text
        context.setColor(this.textColor);
        this.label.horizontalAlignCenter(this.getY() + this.getBorderSize() - 8, this.getY() + this.getHeight() - this.getBorderSize());
        this.label.setX(this.getX() + this.borderSize + 5);
        this.label.render(context);


        context.setColor(color);
        context.setStroke(stroke);
    }


    /**
     * currently selected and accepts input
     *
     * @return true if active
     */
    public boolean isActive() {
        return this.active;
    }

    /**
     * returns borderthickness
     *
     * @return in pixel
     */
    public int getBorderSize() {
        return borderSize;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public Color getActiveBorderColor() {
        return activeBorderColor;
    }

    /**
     * currently hovered by the mouse
     *
     * @return true if hovered
     */
    public boolean isHover() {
        return this.hover;
    }

    public int getSize() {
        return size;
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
        if (this.isActive()) {
            char c = keyEvent.getKeyChar();
            String text = this.label.getText();
            if (c > 31 && c != KeyEvent.VK_DELETE && text.length() < this.getSize()) {
                this.label.setText(text + c);
            } else if (c == KeyEvent.VK_BACK_SPACE && text.length() > 0) {
                this.label.setText(text.substring(0, text.length() - 1));
            } else if (c == KeyEvent.VK_ENTER) {
                this.active = false;
            }
        }

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
    }

    /**
     * Text in the textfield
     *
     * @return textfield text
     */
    public String getText() {
        return this.label.getText();
    }
}
