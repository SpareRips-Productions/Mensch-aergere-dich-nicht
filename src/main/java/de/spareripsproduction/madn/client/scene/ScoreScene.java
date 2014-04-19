package de.spareripsproduction.madn.client.scene;

import de.spareripsproduction.madn.client.Game;
import de.spareripsproduction.madn.client.graphics.Board;
import de.spareripsproduction.madn.client.logic.Player;
import de.spareripsproduction.tinyengine.FontManager;
import de.spareripsproduction.tinyengine.GameWindow;
import de.spareripsproduction.tinyengine.entity.Entity;
import de.spareripsproduction.tinyengine.gui.TECollection;
import de.spareripsproduction.tinyengine.gui.TECollectionVertical;
import de.spareripsproduction.tinyengine.gui.TELabel;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by marian on 12/03/14.
 */
public class ScoreScene extends Scene {

    private TECollectionVertical playerLabels;

    protected Entity background;

    protected TELabel title;

    /**
     * {@inheritDoc}
     */
    public void load() {

        Font titleFont = FontManager.getFont(FontManager.FONT_COMIC_NEUE_BOLD, 30);
        Font menuFont = FontManager.getFont(FontManager.FONT_COMIC_NEUE, 30);

        this.background = new Entity("sprites/menu.png", 0, 0);

        this.title = new TELabel("Game Over", 0, 50, titleFont);
        this.title.verticalAlignCenter(0, GameWindow.getInstance().getWidth());


        playerLabels = new TECollectionVertical(0,0, 15);


        ArrayList<Player> players = Board.getInstance().getPlayers();
        Collections.sort(players,new Comparator<Player>() {
            @Override
            public int compare(Player player, Player player2) {
                int pos1 = player.getPosition();
                int pos2 = player2.getPosition();
                if(pos1 == 0) pos1 = 4;
                if(pos2 == 0) pos2 = 4;
                return pos1-pos2;
            }
        });
        for(Player player : Board.getInstance().getPlayers()) {
            playerLabels.addView(new TELabel(player.scoreLabelStr(), 0, 0, menuFont));
        }

        playerLabels.verticalAlignCenter(0,GameWindow.getInstance().getWidth());
        playerLabels.horizontalAlignCenter(0, GameWindow.getInstance().getHeight());

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
       playerLabels.update();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(Graphics2D g) {
        background.render(g);

        title.render(g);
        playerLabels.render(g);
    }
}
