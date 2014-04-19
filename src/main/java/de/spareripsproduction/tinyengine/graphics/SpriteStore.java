package de.spareripsproduction.tinyengine.graphics;

import de.spareripsproduction.tinyengine.Core;

import java.net.URL;
import java.util.HashMap;

/**
 * Holds all sprites in a HashMap, so they don't need to be created more than once
 *
 * @author Thomas Hampe <thomas@hampe.co>
 * @version 1.0
 * @since 2014-03-09
 */
public class SpriteStore {

    private HashMap<String, Sprite> store;

    private static SpriteStore instance;

    private SpriteStore() {
        store = new HashMap<String, Sprite>();
    }


    /**
     * Get the singleton SpriteStore
     *
     * @return singleton
     */
    public static SpriteStore getInstance() {
        if (SpriteStore.instance == null) {
            SpriteStore.instance = new SpriteStore();
        }
        return SpriteStore.instance;
    }

    /**
     * get a Sprite from the store
     *
     * @param ref Reference to the spriteImage
     * @return Sprite
     */
    public Sprite get(String ref) {

        // check if Sprite exists
        if (store.get(ref) != null) {
            return store.get(ref);
        }
        URL url = this.getClass().getClassLoader().getResource(ref);
        if (url == null) {
            url = this.getClass().getClassLoader().getResource("sprites/notFound.png");
            if (url == null) {
                Core.log("Fallback 'notFound.png' not found!!!");
                Core.exit(1);
            }
            return get("sprites/notFound.png");
        }
        Sprite sprite = new Sprite(url);
        store.put(ref, sprite);

        return sprite;
    }
}
