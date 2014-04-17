package de.spareripsproduction.madn.client.scene;

import de.spareripsproduction.madn.client.Game;
import de.spareripsproduction.tinyengine.FontManager;
import de.spareripsproduction.tinyengine.GameWindow;
import de.spareripsproduction.tinyengine.Timer;
import de.spareripsproduction.tinyengine.entity.Entity;
import de.spareripsproduction.tinyengine.gui.TELabel;
import de.spareripsproduction.tinyengine.input.Keyboard;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * @author Thomas Hampe <thomas@hampe.co>
 * @version 1.0
 * @since 2014-03-19
 */
public class IntroScene extends Scene {
    /**
     * Transparency for fade effect
     */
    private float alpha = 0.0f;

    private boolean increaseAlpha = true;

    private long lastAlphaUpdate;

    private TELabel firstTitleLabel;

    private TELabel secondTitleLabel;

    private TELabel pressSpaceLabel;

    private Entity backgroundImage;

    public void load() {

        this.backgroundImage = new Entity("sprites/intro.png", 0, 0);

        int fontSize = 90;
        Font titleFont = FontManager.getFont(FontManager.FONT_COMIC_NEUE_BOLD, fontSize);
        this.firstTitleLabel = new TELabel("Mensch ärgere", 0, 100, titleFont);
        this.secondTitleLabel = new TELabel("dich nicht!", 0, 0, titleFont);

        fontSize = 40;
        Font pressSpaceFont = FontManager.getFont(FontManager.FONT_COMIC_NEUE, fontSize);
        this.pressSpaceLabel = new TELabel(">Press Space", 0, 0, pressSpaceFont);

    }

    public void unload() {
        this.firstTitleLabel = null;
        this.secondTitleLabel = null;
        this.pressSpaceLabel = null;
    }

    @Override
    public void update() {
        if (this.lastAlphaUpdate == 0) {
            this.lastAlphaUpdate = Timer.getTime();
        }
        if (Timer.getTime() - this.lastAlphaUpdate >= 75) {
            if (this.increaseAlpha) {
                this.alpha += 0.04f;
                if (this.alpha >= 1.0f) {
                    this.alpha = 1.0f;
                    this.increaseAlpha = false;
                }
            } else {
                this.alpha -= 0.04f;
                if (this.alpha <= 0.0f) {
                    this.alpha = 0.0f;
                    this.increaseAlpha = true;
                }
            }
            this.lastAlphaUpdate = Timer.getTime();

        }
        //update Labels -> align them correct
        int width = GameWindow.getInstance().getWidth();
        int height = GameWindow.getInstance().getHeight();

        this.firstTitleLabel.verticalAlignCenter(0, width);

        this.secondTitleLabel.setY(this.firstTitleLabel.getY() + 10);
        this.secondTitleLabel.verticalAlignCenter(0, width);

        this.pressSpaceLabel.setY(height * 9 / 10 - this.pressSpaceLabel.getHeight() / 2);
        this.pressSpaceLabel.verticalAlignCenter(0, width);

        if (Keyboard.isPressed(KeyEvent.VK_SPACE)) {
            Game.getInstance().loadScene(Scene.SCENE_MENU);
        }

    }

    @Override
    public void render(Graphics2D g) {
        this.renderStart(g);
    }

    private void renderStart(Graphics2D g) {
        /*** Draw Background ***/
        this.backgroundImage.render(g);

        Color color = g.getColor();

        /*** Draw Title ***/
        g.setColor(Color.white);
        this.firstTitleLabel.render(g);
        this.secondTitleLabel.render(g);

        /*** draw press any key ***/
        g.setColor(new Color(1, 1, 1, this.alpha));
        this.pressSpaceLabel.render(g);

        /*** reset ***/
        g.setColor(color);
    }
}
