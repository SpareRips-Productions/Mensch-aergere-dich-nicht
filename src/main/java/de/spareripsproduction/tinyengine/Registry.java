package de.spareripsproduction.tinyengine;

import java.util.HashMap;

/**
 * @author Thomas Hampe <thomas@hampe.co>
 * @version 1.0
 * @since 2014-03-08
 */
public class Registry {

    private HashMap<String,Object> register;

    private static Registry instance;

    private Registry() {
        register = new HashMap<String, Object>();
    }

    /**
     *
     * @return the singleton of Registry
     */
    public static Registry singleton() {
        if(Registry.instance == null) {
            Registry.instance = new Registry();
        }
        return Registry.instance;
    }

    /**
     * Saves a value in the Register
     *
     * @param name Name of the Key
     * @param object Value to Save
     */
    public void set(String name, Object object) {
        register.put(name,object);
    }

    /**
     * Gets a value from the register
     *
     * @param name the Key
     * @return the value for @param name
     */
    public Object get(String name){
        return register.get(name);
    }

    /**
     * Checks if a value with @param name exists
     *
     * @param name of the value
     * @return true if a value to key exists
     */
    public boolean has(String name) {
        return register.containsKey(name);
    }
}
