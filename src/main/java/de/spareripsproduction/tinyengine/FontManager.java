package de.spareripsproduction.tinyengine;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

/**
 * The FontManager Class is responsible loading fonts
 *
 * @author Thomas Hampe <thomas@hampe.co>
 * @version 1.0
 * @since 2014-03-11
 */
public class FontManager {

    /**
     * Reference to the <i>Press Start 2P</i> True Type Font File, used by TinyEngine
     */
    public static final String FONT_PRESS_START_2P = "fonts/PressStart2P-Regular.ttf";

    /**
     *  Reference to the <i>Comic Neue</i> True Type Font File
     */
    public static final String FONT_COMIC_NEUE = "fonts/ComicNeue-Regular.ttf";

    /**
     *  Reference to the <i>Comic Neue</i> True Type Font File
     */
    public static final String FONT_COMIC_NEUE_BOLD = "fonts/ComicNeue-Bold-Oblique.ttf";

    private static HashMap<String, Font> fonts = new HashMap<String, Font>();

    /**
     * loads a TrueType font
     *
     * @param resourceRef reference to the TrueTypeFont
     */
    private static Font loadFont(String resourceRef) {
        URL url = FontManager.class.getClassLoader().getResource(resourceRef);
        if (url != null) {
            try {
                Font font = Font.createFont(Font.TRUETYPE_FONT, url.openStream());
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                return font;
            } catch (FontFormatException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Core.log(String.format("Font not found %s", resourceRef));
        }
        return null;
    }

    /**
     *
     * @param resourceRef Path to the ttf-Font file
     * @param fontSize Size of the Font in px
     * @return the Font
     */
    public static Font getFont(String resourceRef, int fontSize) {
        if (!fonts.containsKey(resourceRef)) {
            Font font = loadFont(resourceRef);
            fonts.put(resourceRef, font);
        }
        return fonts.get(resourceRef).deriveFont((float) fontSize);
    }
}
