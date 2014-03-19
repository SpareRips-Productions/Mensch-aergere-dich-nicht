package de.spareripsproduction.tinyengine;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Thomas Hampe <thomas@hampe.co>
 * @version 1.0
 * @since 2014-03-09
 */
public class RegistryTest {

    @Test
    public void testSingleton() throws Exception {

        Assert.assertSame("Registry::singleton not working", Registry.singleton(), Registry.singleton());
    }

    @Test
    public void testSet() throws Exception {
        Registry registry = Registry.singleton();
        String test = "Test Set";
        registry.set("Registry::set",test);

        Assert.assertSame("Registry::set not working", registry.get("Registry::set"), test);
    }

    @Test
    public void testGet() throws Exception {
        Registry registry = Registry.singleton();
        String test = "Test Get";
        registry.set("Registry::get",test);

        Assert.assertSame("Registry::get not working", registry.get("Registry::get"), test);

    }

    @Test
    public void testHas() throws Exception {
        Registry registry = Registry.singleton();
        String test = "Test Has";
        registry.set("Registry::has",test);

        Assert.assertTrue("Registry::has value not found", registry.has("Registry::has"));
        Assert.assertFalse("Registry::has non existend value found", registry.has("!Registry::has"));
    }
}
