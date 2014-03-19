package de.spareripsproduction.tinyengine.graphics;

import de.spareripsproduction.tinyengine.Core;

import java.net.URL;
import java.util.HashMap;

/**
 * @author Thomas Hampe <thomas@hampe.co>
 * @version 1.0
 * @since 2014-03-09
 */
public class SpriteStore {

    private HashMap<String,Sprite> store;

    private static SpriteStore instance;

    private SpriteStore() {
        store = new HashMap<String, Sprite>();
    }

    public static SpriteStore singleton() {
        if(SpriteStore.instance == null) {
            SpriteStore.instance = new SpriteStore();
        }
        return SpriteStore.instance;
    }

    public Sprite get(String ref) {

        // check if Sprite exists
        if (store.get(ref) != null) {
            return store.get(ref);
        }
        URL url = this.getClass().getClassLoader().getResource(ref);
        if(url == null) {
            url = this.getClass().getClassLoader().getResource("sprites/notFound.png");
            if(url == null) {
                Core.log("Fallback 'notFound.png' not found!!!");
                Core.exit(1);
            }
            return get("sprites/notFound.png");
        }
        Sprite sprite = new Sprite(url);
        store.put(ref,sprite);

        return sprite;
    }
}
