package de.spareripsproduction.tinyengine.logic;

import de.spareripsproduction.tinyengine.*;
import de.spareripsproduction.tinyengine.graphics.RenderInterface;

import java.awt.*;

/**
 * @author Thomas Hampe <thomas@hampe.co>
 * @version 1.0
 * @since 2014-03-19
 */
public class Version implements RenderInterface {
    private String version;

    private int build;

    private String codeName;

    public Version(String version, int build, String codeName) {
        this.version = version;
        this.build = build;
        this.codeName = codeName;
    }

    public void render(Graphics2D context) {
        int bottomY = de.spareripsproduction.tinyengine.Window.singleton().getHeight();
        int bottomX = de.spareripsproduction.tinyengine.Window.singleton().getWidth();
        String versionString = this.getFullVersionDescription();
        int stringLength = context.getFontMetrics().stringWidth(versionString);

        Color color = context.getColor();
        context.setColor(Color.green);

        context.drawString(this.getFullVersionDescription(), bottomX-stringLength-1, bottomY-3);

        context.setColor(color);
    }

    public String getCodeName() {
        return codeName;
    }

    public int getBuild() {
        return build;
    }

    public String getVersion() {
        return version;
    }

    /**
     * describes the state of the software, version build-number and code-name
     *
     * @return v{VESION}#{BUILD}-{CODENAME}
     */
    public String getFullVersionDescription() {
        return String.format("v%s#%d-%s",this.getVersion(), this.getBuild(), this.getCodeName());
    }


}
