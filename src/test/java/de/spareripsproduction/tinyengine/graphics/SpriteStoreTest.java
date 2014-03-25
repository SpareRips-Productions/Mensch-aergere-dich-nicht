package de.spareripsproduction.tinyengine.graphics;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Thomas Hampe <thomas@hampe.co>
 * @version 1.0
 * @since 2014-03-09
 */
public class SpriteStoreTest {

    @Test
    public void testSingleton() throws Exception {

        Assert.assertSame("SpriteStore::getInstance not working", SpriteStore.getInstance(), SpriteStore.getInstance());
    }

    @Test
    public void testGet() throws Exception {
        //check if Store works
        Sprite sprite = SpriteStore.getInstance().get("sprites/test.png");
        Sprite sprite2 = SpriteStore.getInstance().get("sprites/test.png");
        Assert.assertSame("SpriteStore::get Store is not Storing entities", sprite, sprite2);

        //check if finding images works
        Sprite notFound = SpriteStore.getInstance().get("sprites/notFound.png");
        Assert.assertNotSame("SpriteStore::get Sprite with test.png not found", notFound, sprite);

        //check if fallback works
        Sprite notExistent = SpriteStore.getInstance().get("sprites/djshgjhsdfjhavs.jpg");
        Assert.assertSame("SpriteStore::get Fallback not working", notFound, notExistent);


    }
}
