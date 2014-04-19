package de.spareripsproduction.madn.client.scene;

import de.spareripsproduction.madn.client.Game;
import de.spareripsproduction.madn.client.logic.Settings;
import de.spareripsproduction.tinyengine.FontManager;
import de.spareripsproduction.tinyengine.GameWindow;
import de.spareripsproduction.tinyengine.Timer;
import de.spareripsproduction.tinyengine.entity.Entity;
import de.spareripsproduction.tinyengine.gui.TEButton;
import de.spareripsproduction.tinyengine.gui.TECollectionVertical;
import de.spareripsproduction.tinyengine.gui.TELabel;
import de.spareripsproduction.tinyengine.gui.TETextField;

import java.awt.*;

/**
 * Created by atissen on 26.03.14.
 */


public class SettingsScene extends Scene {

    private TELabel pLabel1;
    private TELabel pLabel2;
    private TELabel pLabel3;
    private TELabel pLabel4;
    private TECollectionVertical LabelCollection;
    private TETextField p1Text;
    private TETextField p2Text;
    private TETextField p3Text;
    private TETextField p4Text;
    private Entity backgroundImage;
    private TEButton backButton;
    private TEButton playButton;

    private TECollectionVertical counterCollection;
    private TEButton upCount;
    private TEButton downCount;
    private TELabel countLabel;

    private long last = Timer.getTime();

    /**
     * {@inheritDoc}
     */
    public void load() {
        this.backgroundImage = new Entity("sprites/menu.png", 0, 0);

        Font titleFont = FontManager.getFont(FontManager.FONT_COMIC_NEUE_BOLD, 30);
        Font menuFont = FontManager.getFont(FontManager.FONT_COMIC_NEUE, 25);


        this.pLabel1 = new TELabel("Spieler 1: ", 50, 20, titleFont);
        this.pLabel2 = new TELabel("Spieler 2: ", 50, 40, titleFont);
        this.pLabel3 = new TELabel("Spieler 3: ", 50, 60, titleFont);
        this.pLabel4 = new TELabel("Spieler 4: ", 50, 80, titleFont);
        this.p1Text = new TETextField(Settings.Player1Name, 100, 100, 200, 50, menuFont, 14, 2);
        this.p2Text = new TETextField(Settings.Player2Name, 100, 100, 200, 50, menuFont, 14, 2);
        this.p3Text = new TETextField(Settings.Player3Name, 100, 100, 200, 50, menuFont, 14, 2);
        this.p4Text = new TETextField(Settings.Player4Name, 100, 100, 200, 50, menuFont, 14, 2);

        LabelCollection = new TECollectionVertical(0, 0, 15);

        LabelCollection.addView(this.pLabel1);
        LabelCollection.addView(this.p1Text);
        LabelCollection.addView(this.pLabel2);
        LabelCollection.addView(this.p2Text);
        LabelCollection.addView(this.pLabel3);
        LabelCollection.addView(this.p3Text);
        LabelCollection.addView(this.pLabel4);
        LabelCollection.addView(this.p4Text);

        this.backButton = new TEButton("< Back", 130, LabelCollection.getCollection().get(7).getY() + 100, 120, 50, 2, menuFont);
        this.playButton = new TEButton("Play >", GameWindow.getInstance().getWidth() - 250, LabelCollection.getCollection().get(7).getY() + 100, 120, 50, 2, menuFont);

        this.counterCollection = new TECollectionVertical(0, 0, 15);
        this.upCount = new TEButton("+", 0, 0, 50, 50, 2, menuFont);
        this.downCount = new TEButton("-", 0, 0, 50, 50, 2, menuFont);
        this.countLabel = new TELabel(String.format("%d", Settings.playerCount), 0, 0, menuFont);
        this.counterCollection.addView(this.upCount);
        this.counterCollection.addView(this.countLabel);
        this.counterCollection.addView(this.downCount);

    }

    /**
     * {@inheritDoc}
     */
    public void unload() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        this.backButton.update();
        this.playButton.update();

        LabelCollection.horizontalAlignCenter(0, GameWindow.getInstance().getHeight());
        LabelCollection.verticalAlignCenter(0, GameWindow.getInstance().getWidth());

        counterCollection.update();
        counterCollection.setX(LabelCollection.getX() - 150);
        counterCollection.horizontalAlignCenter(LabelCollection.getY(), LabelCollection.getY() + LabelCollection.getHeight());
        countLabel.verticalAlignCenter(counterCollection.getX(), counterCollection.getX() + counterCollection.getWidth());

        if (Timer.getTime() - last > 500 && (upCount.isClicked() || downCount.isClicked())) {
            if (upCount.isClicked() && Settings.playerCount < 4) {
                Settings.playerCount++;
            }
            if (downCount.isClicked() && Settings.playerCount > 2) {
                Settings.playerCount--;
            }

            last = Timer.getTime();
        }
        countLabel.setText(String.format("%d", Settings.playerCount));


        LabelCollection.update();
        if (this.playButton.isClicked()) {
            if (validNames() != null) {
                validNames().active = true;
            } else {
                Game.getInstance().loadScene(SCENE_GAME);
            }
        }
        if (this.backButton.isClicked()) {
            Game.getInstance().loadScene(SCENE_MENU);
        }

        Settings.Player1Name = this.p1Text.getText();
        Settings.Player2Name = this.p2Text.getText();
        Settings.Player3Name = this.p3Text.getText();
        Settings.Player4Name = this.p4Text.getText();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.black);
        this.backgroundImage.render(g);
        this.backButton.render(g);
        this.playButton.render(g);
        this.counterCollection.render(g);
        for (int i = 0; i < Settings.playerCount * 2; i += 2) {
            this.LabelCollection.get(i).render(g);
            this.LabelCollection.get(i + 1).render(g);
        }
    }

    private TETextField validNames() {
        TETextField retVal = null;

        if (this.p1Text.getText().length() < 1) {
            retVal = this.p1Text;
        } else if (this.p2Text.getText().length() < 1) {
            retVal = this.p2Text;
        } else if (this.p3Text.getText().length() < 1) {
            retVal = this.p3Text;
        } else if (this.p4Text.getText().length() < 1) {
            retVal = this.p4Text;
        }

        return retVal;
    }

}
