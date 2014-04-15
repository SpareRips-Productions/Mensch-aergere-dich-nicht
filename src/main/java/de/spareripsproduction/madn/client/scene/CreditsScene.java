package de.spareripsproduction.madn.client.scene;

import de.spareripsproduction.tinyengine.GameWindow;
import de.spareripsproduction.tinyengine.entity.Entity;

import javax.swing.*;
import java.awt.*;

/**
 * @author Thomas Hampe <thomas@hampe.co>
 * @version 1.0
 * @since 2014-04-15
 */
public class CreditsScene extends Scene {

    Entity background;

    ImageIcon fatRaccoon;

    public void load() {
        this.background = new Entity("sprites/title.png", 0, 0);
        fatRaccoon = new ImageIcon(this.getClass().getClassLoader().getResource("raccoon.gif"));
    }
    public void unload() {

    }

    @Override
    public void update() {
        this.background.update();
    }

    @Override
    public void render(Graphics2D g) {

        this.background.render(g);
        fatRaccoon.paintIcon(GameWindow.getInstance().getFrame(),g,GameWindow.getInstance().getWidth()/2-fatRaccoon.getIconWidth()/2,25);
    }
}
