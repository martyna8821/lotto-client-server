package pl.martyna.server.model;

import java.util.Collections;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

/**
 * Class responsible for pl.martyna.server simulation drawings
 * @author Martyna Drabinska
 * @version 2.0
 */
public class Draw {

    private int min = 1;
    private int max = 49;
    private int quantity = 5;

    public Draw(){}

    /**
     * Changes settings of simulation if passed parameters are correct
     * @param min new minimal value of a result
     * @param max new maximum value of a result
     * @param quantity new quantity of results
     * @throws IllegalValueException if for passed arguments model can't carry out a simulation
     */
    public void changeSettings(int min, int max, int quantity) throws IllegalValueException {
        if(min > max || (max - min) < quantity || quantity < 1){
            throw new IllegalValueException();
        }
        else{
            this.min = min;
            this.max = max;
            this.quantity = quantity;
        }
    }

    /**
     * Carries out a pl.martyna.server drawing simulation and returns set representing its results
     * @return unmodifiable set of drawn results
     */
    public Set<Integer> randomResults(){

        Set<Integer> results = new TreeSet<>();
        Random randomGenerator = new Random();
        while(results.size() < quantity){
            results.add(randomGenerator.nextInt(max)+min);
        }
        return Collections.unmodifiableSet(results);
    }

    /**
     * Returns minimal value of drawn result
     * @return minimum value of drawn result
     */
    public int getMin() {
        return min;
    }

    /**
     * Returns maximum value of drawn result
     * @return maximum value of drawn result
     */
    public int getMax() {
        return max;
    }

    /**
     * Returns quantity of drawn results
     * @return quantity of drawn results
     */
    public int getQuantity() {
        return quantity;
    }
}

