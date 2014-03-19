package de.spareripsproduction.tinyengine.entity;

import junit.framework.Assert;
import org.junit.Test;

/**
 * @author Thomas Hampe <thomas@hampe.co>
 * @version 1.0
 * @since 2014-03-09
 */
public class EntityTest {

    @Test
    public void testGetX() throws Exception {

        Entity entity = new Entity("sprites/test.png", 42, 23);
        Assert.assertEquals("Entity::getX not correct",42.0f, entity.getX());

    }

    @Test
    public void testGetY() throws Exception {
        Entity entity = new Entity("sprites/test.png", 42, 23);
        Assert.assertEquals("Entity::getY not correct", 23.0f, entity.getY());
    }

    @Test
    public void testGetHeight() throws Exception {
        Entity entity = new Entity("sprites/test.png", 42, 23, 100, 200);
        Assert.assertEquals("Entity::getHeight not correct", 200.0f, entity.getHeight());
    }

    @Test
    public void testGetWidth() throws Exception {
        Entity entity = new Entity("sprites/test.png", 42, 23, 100, 200);
        Assert.assertEquals("Entity::getWidth not correct", 100.0f, entity.getWidth());
    }

    @Test
    public void testSetLocation() throws Exception {
        Entity entity = new Entity("sprites/test.png", 42, 23, 100, 200);
        entity.setLocation(1.0f,2.0f);
        Assert.assertEquals("Entity::SetLocation X not correct", 1.0f, entity.getX());
        Assert.assertEquals("Entity::SetLocation Y not correct",2.0f, entity.getY());

    }

    @Test
    public void testSetSize() throws Exception {
        Entity entity = new Entity("sprites/test.png", 42, 23, 100, 200);
        entity.setSize(1.0f, 2.0f);
        Assert.assertEquals("Entity::SetSize Height not correct", 2.0f, entity.getHeight());
        Assert.assertEquals("Entity::SetSize Width not correct", 1.0f, entity.getWidth());
    }

    @Test
    public void testMove() throws Exception {
        Entity entity = new Entity("sprites/test.png", 42, 23, 100, 200);
        float x = entity.getX();
        float y = entity.getY();
        float dx = -1.0f;
        float dy = 2.0f;
        entity.move(-1.0f,2.0f);
        Assert.assertEquals("Entity::move X not correct", x+dx, entity.getX());
        Assert.assertEquals("Entity::move Y not correct",y+dy, entity.getY());
    }

    @Test
    public void testCollidesWith() throws Exception {
        Entity entity1 = new Entity("sprites/test.png", 0, 0, 1, 1);
        Entity entity2 = new Entity("sprites/test.png", 0, 0, 2, 2);
        Entity entity3 = new Entity("sprites/test.png", 2, 2, 1, 1);

        Assert.assertTrue("Entity::collidesWith Collision should be detected", entity1.collidesWith(entity2));
        Assert.assertFalse("Entity::collidesWith Collision should not be detected", entity1.collidesWith(entity3));
    }
}
