package de.spareripsproduction.madn.client.scene;

import de.spareripsproduction.madn.client.Game;
import de.spareripsproduction.madn.client.logic.Settings;
import de.spareripsproduction.tinyengine.GameWindow;
import de.spareripsproduction.tinyengine.gui.TEButton;
import de.spareripsproduction.tinyengine.gui.TECollectionVertical;
import de.spareripsproduction.tinyengine.gui.TELabel;
import de.spareripsproduction.tinyengine.gui.TETextField;

import java.awt.*;

/**
 * Created by atissen on 26.03.14.
 */


public class SettingsScene extends Scene {
    public void load() {

        Font menuFont = new Font("PressStart2P-Regular", Font.PLAIN, 30);
        this.backButton = new TEButton("Back", 5,5, 300, 50, 5, menuFont);
        this.pLabel1 = new TELabel("Spieler 1: ",50,20,menuFont);
        this.pLabel2 = new TELabel("Spieler 2: ",50,40,menuFont);
        this.pLabel3 = new TELabel("Spieler 3: ",50,60,menuFont);
        this.pLabel4 = new TELabel("Spieler 4: ",50,80,menuFont);
        this.p1Text = new TETextField(Settings.Player1Name,100,100,200,50,menuFont,10,3);
        this.p2Text = new TETextField(Settings.Player2Name,100,100,200,50,menuFont,10,3);
        this.p3Text = new TETextField(Settings.Player3Name,100,100,200,50,menuFont,10,3);
        this.p4Text = new TETextField(Settings.Player4Name,100,100,200,50,menuFont,10,3);
        LabelCollection = new TECollectionVertical(0,0,30);

        LabelCollection.addView(this.pLabel1);
        LabelCollection.addView(this.p1Text);
        LabelCollection.addView(this.pLabel2);
        LabelCollection.addView(this.p2Text);
        LabelCollection.addView(this.pLabel3);
        LabelCollection.addView(this.p3Text);
        LabelCollection.addView(this.pLabel4);
        LabelCollection.addView(this.p4Text);
    }

    private TELabel pLabel1;
    private TELabel pLabel2;
    private TELabel pLabel3;
    private TELabel pLabel4;
    private TECollectionVertical LabelCollection;

    private TETextField p1Text;
    private TETextField p2Text;
    private TETextField p3Text;
    private TETextField p4Text;

    private TEButton backButton;
    public void unload() {

    }

    @Override
    public void update() {
        this.backButton.update();
        LabelCollection.horizontalAlignCenter(0,GameWindow.getInstance().getHeight());
        LabelCollection.verticalAlignCenter(0,GameWindow.getInstance().getWidth());
        LabelCollection.update();
        if (this.backButton.isClicked()){
            Game.getInstance().loadScene(SCENE_MENU);
        }
        Settings.Player1Name = this.p1Text.getText();
        Settings.Player2Name = this.p2Text.getText();
        Settings.Player3Name = this.p3Text.getText();
        Settings.Player4Name = this.p4Text.getText();
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.white);
        this.backButton.render(g);
        this.LabelCollection.render(g);
    }

}
