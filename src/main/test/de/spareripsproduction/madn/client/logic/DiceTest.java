package de.spareripsproduction.madn.client.logic;

import org.junit.*;

/**
 * Created by marian on 12/03/14.
 */
public class DiceTest {
    @Test
    public void testGetNextNumber() throws Exception {
        Dice dice = new Dice();

        int nextNumber;
        for(int i = 0; i < 300; i++) {

            nextNumber = dice.getNextNumber();

            Assert.assertEquals(true, nextNumber > 0 && nextNumber <= 6);
        }
    }
}
