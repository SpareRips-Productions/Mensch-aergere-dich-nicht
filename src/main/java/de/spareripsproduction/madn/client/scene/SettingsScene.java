package de.spareripsproduction.madn.client.scene;

import de.spareripsproduction.madn.client.Game;
import de.spareripsproduction.madn.client.logic.Settings;
import de.spareripsproduction.tinyengine.FontManager;
import de.spareripsproduction.tinyengine.GameWindow;
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
    public void load() {
        this.backgroundImage = new Entity("sprites/menu.png", 0, 0);

        Font titleFont = FontManager.getFont(FontManager.FONT_ARIZONIA, 30);
        Font menuFont = FontManager.getFont(FontManager.FONT_DROID_SANS, 30);


        this.pLabel1 = new TELabel("Spieler 1: ",50,20,titleFont);
        this.pLabel2 = new TELabel("Spieler 2: ",50,40,titleFont);
        this.pLabel3 = new TELabel("Spieler 3: ",50,60,titleFont);
        this.pLabel4 = new TELabel("Spieler 4: ",50,80,titleFont);
        this.p1Text = new TETextField(Settings.Player1Name,100,100,200,50,menuFont,10,2);
        this.p2Text = new TETextField(Settings.Player2Name,100,100,200,50,menuFont,10,2);
        this.p3Text = new TETextField(Settings.Player3Name,100,100,200,50,menuFont,10,2);
        this.p4Text = new TETextField(Settings.Player4Name,100,100,200,50,menuFont,10,2);

        LabelCollection = new TECollectionVertical(0,0,15);

        LabelCollection.addView(this.pLabel1);
        LabelCollection.addView(this.p1Text);
        LabelCollection.addView(this.pLabel2);
        LabelCollection.addView(this.p2Text);
        LabelCollection.addView(this.pLabel3);
        LabelCollection.addView(this.p3Text);
        LabelCollection.addView(this.pLabel4);
        LabelCollection.addView(this.p4Text);

        this.backButton = new TEButton("Back", 130,LabelCollection.getCollection().get(7).getY() + 100, 120, 50, 2, menuFont);
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
    private Entity backgroundImage;
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
            if(validNames() != null){
                validNames().active = true;
            }else{
            Game.getInstance().loadScene(SCENE_MENU);}
        }
        Settings.Player1Name = this.p1Text.getText();
        Settings.Player2Name = this.p2Text.getText();
        Settings.Player3Name = this.p3Text.getText();
        Settings.Player4Name = this.p4Text.getText();
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.black);
        this.backgroundImage.render((Graphics2D) g);
        this.backButton.render((Graphics2D)g);
        this.LabelCollection.render((Graphics2D)g);
    }

    private TETextField validNames(){
        TETextField retVal = null;

        if(this.p1Text.getText().length() < 1){ retVal = this.p1Text;}
        else if(this.p2Text.getText().length() < 1){ retVal = this.p2Text;}
        else if(this.p3Text.getText().length() < 1){ retVal = this.p3Text;}
        else if(this.p4Text.getText().length() < 1){ retVal = this.p4Text;}

        return retVal;
    }

}
