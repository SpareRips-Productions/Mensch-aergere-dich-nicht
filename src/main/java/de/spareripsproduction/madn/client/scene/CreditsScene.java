package de.spareripsproduction.madn.client.scene;

import de.spareripsproduction.madn.client.Game;
import de.spareripsproduction.tinyengine.FontManager;
import de.spareripsproduction.tinyengine.GameWindow;
import de.spareripsproduction.tinyengine.entity.Entity;
import de.spareripsproduction.tinyengine.gui.TEButton;
import de.spareripsproduction.tinyengine.gui.TECollectionVertical;
import de.spareripsproduction.tinyengine.gui.TELabel;
import de.spareripsproduction.tinyengine.input.Keyboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * @author Thomas Hampe <thomas@hampe.co>
 * @version 1.0
 * @since 2014-04-15
 */
public class CreditsScene extends Scene {

    protected Entity background;

    protected ImageIcon fatRaccoon;

    protected boolean showRaccoon = false;

    protected TEButton backButton;

    protected  TELabel title;

    protected TECollectionVertical creditsCollection;

    public void load() {
        this.background = new Entity("sprites/title.png", 0, 0);
        fatRaccoon = new ImageIcon(this.getClass().getClassLoader().getResource("raccoon.gif"));
        Font menuFont = FontManager.getFont(FontManager.FONT_COMIC_NEUE, 30);
        Font titleFont = FontManager.getFont(FontManager.FONT_COMIC_NEUE_BOLD, 50);

        this.backButton = new TEButton("Back", 130, GameWindow.getInstance().getHeight()-100, 120, 50, 2, menuFont, Color.white, Color.red, Color.red);

        this.title = new TELabel("Credits", 0, 50, titleFont);
        this.title.verticalAlignCenter(0, GameWindow.getInstance().getWidth());

        this.creditsCollection = new TECollectionVertical(0, 0, 15);
        this.creditsCollection.addView(new TELabel("Jan Böckmann", 0,0, menuFont));
        this.creditsCollection.addView(new TELabel("Thomas Hampe", 0,0, menuFont));
        this.creditsCollection.addView(new TELabel("Sebastian Mathea", 0,0, menuFont));
        this.creditsCollection.addView(new TELabel("Marian Sievers", 0,0, menuFont));
        this.creditsCollection.addView(new TELabel("Arthur Tissen", 0,0, menuFont));

        this.creditsCollection.horizontalAlignCenter(0, GameWindow.getInstance().getHeight());
        this.creditsCollection.verticalAlignCenter(0, GameWindow.getInstance().getWidth());

    }
    public void unload() {

    }

    @Override
    public void update() {
        this.backButton.update();
        this.background.update();
        if (this.backButton.isClicked()){
            Game.getInstance().loadScene(SCENE_MENU);
        }
        if(Keyboard.isPressed(KeyEvent.VK_R)) {
            this.showRaccoon = true;
        }
        this.creditsCollection.update();

        this.title.update();
    }

    @Override
    public void render(Graphics2D g) {

        this.background.render(g);
        g.setColor(Color.white);
        this.title.render(g);
        if(showRaccoon) {
            fatRaccoon.paintIcon(GameWindow.getInstance().getFrame(),g,GameWindow.getInstance().getWidth()/2-fatRaccoon.getIconWidth()/2,25);
        }

        this.backButton.render(g);


        this.creditsCollection.render(g);
    }
}
