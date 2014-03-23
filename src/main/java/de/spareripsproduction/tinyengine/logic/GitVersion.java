package de.spareripsproduction.tinyengine.logic;

import de.spareripsproduction.tinyengine.GameWindow;
import de.spareripsproduction.tinyengine.graphics.RenderInterface;
import de.spareripsproduction.tinyengine.gui.TELabel;

import java.awt.*;

/**
 * @author Thomas Hampe <thomas@hampe.co>
 * @version 1.0
 * @since 2014-03-23
 */
public class GitVersion extends GitRepository implements RenderInterface {

    public TELabel gitVersionLabel;

    public GitVersion() {
        super();
        Font font = new Font("PressStart2P-Regular", Font.PLAIN, 12);
        this.gitVersionLabel = new TELabel(this.getVersionLabel(), 0, 0, font);
    }

    public void render(Graphics2D context) {

        Color color = context.getColor();

        context.setColor(Color.green);
        int width = GameWindow.getInstance().getWidth();
        int height = GameWindow.getInstance().getHeight();
        this.gitVersionLabel.verticalAlignRight(width);
        this.gitVersionLabel.setY(height - 5);
        this.gitVersionLabel.render(context);

        context.setColor(color);
    }

    private String getVersionLabel() {
        return String.format("%s@%s", this.getDescribe(), this.getBranch());
    }

}
