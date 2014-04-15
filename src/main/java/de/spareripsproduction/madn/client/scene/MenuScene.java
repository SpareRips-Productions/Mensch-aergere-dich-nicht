package de.spareripsproduction.madn.client.scene;

import de.spareripsproduction.madn.client.Game;
import de.spareripsproduction.tinyengine.FontManager;
import de.spareripsproduction.tinyengine.entity.Entity;
import de.spareripsproduction.tinyengine.gui.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by marian on 12/03/14.
 */
public class MenuScene extends Scene implements ActionListener {

    private TELabel header;

    private TECollectionVertical btnCollection;

    private Entity backgroundImage;

    public void load() {

        Font titleFont = FontManager.getFont(FontManager.FONT_ARIZONIA, 50);
        Font menuFont = FontManager.getFont(FontManager.FONT_DROID_SANS, 30);

        this.backgroundImage = new Entity("sprites/menu.png", 0, 0);
        this.header = new TELabel("Menu", 0, 50, titleFont);


        this.btnCollection = new TECollectionVertical(0,0, 20);
        this.btnCollection.addView(new TEButton("Join Game", 0, 0, 300, 50, 2, menuFont, Color.black, Color.blue, Color.red));
        this.btnCollection.addView(new TEButton("Settings", 0, 0, 300, 50, 2, menuFont, Color.black, Color.blue, Color.red));
        this.btnCollection.addView(new TEButton("Credits", 0, 0, 300, 50, 2, menuFont, Color.black, Color.blue, Color.red));
    }
    public void unload() {
        this.header = null;
        this.btnCollection = null;
    }

    @Override
    public void update() {
        int width = Game.getInstance().getWindow().getWidth();
        int height = Game.getInstance().getWindow().getHeight();

        this.header.verticalAlignCenter(0, width);

        this.btnCollection.verticalAlignCenter(0, width);
        this.btnCollection.horizontalAlignCenter(0, height);
        int counter = 0;
        this.btnCollection.update();
        for(TEView view : this.btnCollection.getCollection()) {
            TEButton btn = (TEButton) view;
            if(btn.isClicked()) {
                switch (counter) {
                    case 0:
                        Game.getInstance().loadScene(Scene.SCENE_GAME);
                        return;
                    case 1:
                        Game.getInstance().loadScene(SCENE_SETTINGS);
                        return;
                    case 2:
                        Game.getInstance().loadScene(SCENE_CREDITS);
                        return;
                }
            }
            counter++;
        }

    }

    @Override
    public void render(Graphics2D g) {

        Color color = g.getColor();
        g.setColor(Color.black);
        this.backgroundImage.render(g);
        this.header.render(g);
        this.btnCollection.render(g);

        /*** reset ***/
        g.setColor(color);

    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("Action!!!");
    }
}
