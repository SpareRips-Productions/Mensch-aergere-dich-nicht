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

        Assert.assertSame("SpriteStore::singleton not working", SpriteStore.singleton(), SpriteStore.singleton());
    }

    @Test
    public void testGet() throws Exception {
        //check if Store works
        Sprite sprite = SpriteStore.singleton().get("sprites/test.png");
        Sprite sprite2 = SpriteStore.singleton().get("sprites/test.png");
        Assert.assertSame("SpriteStore::get Store is not Storing entities", sprite, sprite2);

        //check if finding images works
        Sprite notFound = SpriteStore.singleton().get("sprites/notFound.png");
        Assert.assertNotSame("SpriteStore::get Sprite with test.png not found", notFound, sprite);

        //check if fallback works
        Sprite notExistent = SpriteStore.singleton().get("sprites/djshgjhsdfjhavs.jpg");
        Assert.assertSame("SpriteStore::get Fallback not working", notFound, notExistent);


    }
}
