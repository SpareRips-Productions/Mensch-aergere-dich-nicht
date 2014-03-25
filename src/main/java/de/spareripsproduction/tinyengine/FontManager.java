package de.spareripsproduction.tinyengine;

import java.awt.*;
import java.io.IOException;
import java.net.URL;

/**
 * The FontManager Class is responsible loading fonts
 *
 * @author Thomas Hampe <thomas@hampe.co>
 * @version 1.0
 * @since 2014-03-11
 */
public class FontManager {

    /**
     * loads a TrueType font
     *
     * @param resourceRef reference to the TrueTypeFont
     * @param name name of the font
     */
    public static void loadFont(String resourceRef, String name) {
        URL url = FontManager.class.getClassLoader().getResource(resourceRef);
        if (url != null) {
            try {
                Font font = Font.createFont(Font.TRUETYPE_FONT, url.openStream());
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(font);
            } catch (FontFormatException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Core.log(String.format("Font not found %s", resourceRef));
        }

    }

}
