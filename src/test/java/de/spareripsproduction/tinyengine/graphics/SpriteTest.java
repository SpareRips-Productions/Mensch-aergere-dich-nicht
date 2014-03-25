package de.spareripsproduction.tinyengine.graphics;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Thomas Hampe <thomas@hampe.co>
 * @version 1.0
 * @since 2014-03-09
 */
public class SpriteTest {

    @Test
    public void testGetWidth() throws Exception {
        Sprite sprite = SpriteStore.getInstance().get("sprites/test.png");
        Assert.assertEquals("Sprite::getWidth: width not correct", 232, sprite.getWidth());
    }

    @Test
    public void testGetHeight() throws Exception {
        Sprite sprite = SpriteStore.getInstance().get("sprites/test.png");
        Assert.assertEquals("Sprite::getWidth: width not correct", 242, sprite.getHeight());

    }
}
