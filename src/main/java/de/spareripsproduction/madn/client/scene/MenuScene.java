package de.spareripsproduction.madn.client.scene;

import de.spareripsproduction.madn.client.Game;
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

    public void load() {

        int fontSize = 30;
        Font menuFont = new Font("PressStart2P-Regular", Font.PLAIN, fontSize);

        this.header = new TELabel("Menu", 0, 20, menuFont);

        this.btnCollection = new TECollectionVertical(0,0, 20);
        this.btnCollection.addView(new TEButton("Join Game", 0, 0, 300, 50, 5, menuFont));
        this.btnCollection.addView(new TEButton("Host Game", 0, 0, 300, 50, 5, menuFont));
        this.btnCollection.addView(new TEButton("Start Game", 0, 0, 300, 50, 5, menuFont));
        this.btnCollection.addView(new TEButton("Settings", 0, 0, 300, 50, 5, menuFont));
        this.btnCollection.addView(new TEButton("Exit", 0, 0, 300, 50, 5, menuFont));
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
        this.header.setY(this.header.getHeight()+10);

        this.btnCollection.verticalAlignCenter(0, width);
        this.btnCollection.horizontalAlignCenter(0, height);
        int counter = 0;
        this.btnCollection.update();
        for(TEView view : this.btnCollection.getCollection()) {
            TEButton btn = (TEButton) view;
            if(btn.isClicked()) {
                switch (counter) {
                    case 0:
                        Game.getInstance().loadScene(SCENE_LOBBY);
                        Game.getInstance().loadScene(SCENE_GAME);
                        return;
                    case 1:
                        //Game.getInstance().loadScene();
                        return;
                    case 2:
                        //Exit Game
                        return;

                }
            }
            counter++;
        }

    }

    @Override
    public void render(Graphics g) {

        Color color = g.getColor();
        g.setColor(Color.white);

        this.header.render((Graphics2D) g);
        this.btnCollection.render((Graphics2D) g);

        /*** reset ***/
        g.setColor(color);

    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("Action!!!");
    }
}
