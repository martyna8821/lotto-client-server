package pl.martyna.test;

import org.junit.*;
import pl.martyna.server.model.Draw;
import pl.martyna.server.model.IllegalValueException;
import java.util.Collections;
import java.util.Set;
import static org.junit.Assert.*;

public class DrawTest {

    private Draw draw;
    public DrawTest(){}

    @Before
    public void setUp(){
        draw = new Draw();
    }

    @Test(expected = IllegalValueException.class)
    public void testIncorrectRange() throws IllegalValueException{
        draw.changeSettings(10,2,2);
    }

    @Test(expected = IllegalValueException.class)
    public void testNegativeQuantity() throws IllegalValueException{
        draw.changeSettings(1,4,-1);
    }

    @Test(expected = IllegalValueException.class)
    public void testQuantityOutOfRange() throws IllegalValueException{
        draw.changeSettings(1,10,20);
    }

    @Test
    public void testCorrectSettings(){
        try{
            draw.changeSettings(1,30, 5);
            assertEquals("Min not set",1, draw.getMin());
            assertEquals("Max not set", 30, draw.getMax());
            assertEquals("Quantity not set",5, draw.getQuantity());
        }
        catch (IllegalValueException ex){
            fail("Should not throw IllegalValueException for correct settings values");
        }
    }

    @Test
    public void testIfDrawnSetNotNull(){
        assertNotNull("Drawn result set is null",draw.randomResults());
    }

    @Test
    public void testQuantityOfDrawnNumbers(){
        assertEquals("Incorrect quantity of drawn results",draw.randomResults().size(), draw.getQuantity());
    }

    @Test
    public void testMinDrawnNumber(){
        Set<Integer> results = draw.randomResults();
        if(Collections.min(results) < draw.getMin())
                fail("Drawn minimum result smaller then min");

    }

    @Test
    public void testMaxDrawnNumber(){
        Set<Integer> results = draw.randomResults();
        if(   Collections.max(results) > draw.getMax())
            fail("Drawn maximum result bigger then max");
    }

    @Test
    public void testIfEachDrawIsDifferent(){
        Set<Integer> results1 = draw.randomResults();
        Set<Integer> results2 = draw.randomResults();
        assertFalse(results1.containsAll(results2));
    }
}

