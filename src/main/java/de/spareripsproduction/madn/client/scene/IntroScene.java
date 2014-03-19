package de.spareripsproduction.madn.client.scene;

import de.spareripsproduction.madn.client.Game;
import de.spareripsproduction.tinyengine.GameWindow;
import de.spareripsproduction.tinyengine.Timer;
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

    @Override
    public void update() {
        if(Keyboard.isPressed(KeyEvent.VK_SPACE)) {
            Game.getInstance().loadScene(Scene.SCENE_MENU);
        }

        if(this.lastAlphaUpdate == 0) {
            this.lastAlphaUpdate = Timer.getTime();
        }
        if(Timer.getTime() - this.lastAlphaUpdate >= 75) {
            if(this.increaseAlpha) {
                this.alpha += 0.04f;
                if(this.alpha >= 1.0f) {
                    this.alpha = 1.0f;
                    this.increaseAlpha = false;
                }
            }else {
                this.alpha -= 0.04f;
                if(this.alpha <= 0.0f) {
                    this.alpha = 0.0f;
                    this.increaseAlpha = true;
                }
            }
            this.lastAlphaUpdate = Timer.getTime();

        }
    }

    @Override
    public void render(Graphics g) {
        this.renderStart(g);
    }

    private void renderStart(Graphics g) {
        Color color = g.getColor();
        Font font = g.getFont();

        /*** Draw Title ***/
        int fontSize = 85;
        g.setColor(Color.white);
        g.setFont(new Font("Arizonia-Regular", Font.PLAIN, fontSize));


        String firstLine = "Mensch ärgere";
        int firstLineWidth = g.getFontMetrics().stringWidth(firstLine);
        int centerFirstLineX = GameWindow.singleton().getWidth()/2 - firstLineWidth/2;
        int centerFirstLineY = GameWindow.singleton().getHeight()/2 - fontSize/2;
        g.drawString(firstLine, centerFirstLineX, centerFirstLineY);

        String secondLine = "dich nicht!";
        int secondLineWidth = g.getFontMetrics().stringWidth(secondLine);
        int centerSecondLineX = GameWindow.singleton().getWidth()/2 - secondLineWidth/2;
        int centerSecondLineY = centerFirstLineY + fontSize + fontSize/100;
        g.drawString(secondLine, centerSecondLineX, centerSecondLineY);

        /*** draw press any key ***/
        g.setColor(new Color(1, 1, 1, this.alpha));
        g.setFont(new Font("PressStart2P-Regular", Font.PLAIN, 30));
        String startString = ">>Press Space";
        int startStringLength = g.getFontMetrics().stringWidth(startString);
        int centerStartX = GameWindow.singleton().getWidth()/2 - startStringLength/2;
        int centerStartY = GameWindow.singleton().getHeight()*9/10-15;
        g.drawString(startString, centerStartX, centerStartY);


        /*** reset ***/
        g.setColor(color);
        g.setFont(font);


    }
}
