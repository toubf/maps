package edu.ttap.lootgenerator;

import java.util.Random;
import java.util.ArrayList;

/** Class representing a collection of treasure classes. */
public class TreasureClasses {
    ArrayList<TreasureClass> treasureTypes = new ArrayList<>();
    
    /** Class representing a type of treasure. */
    private class TreasureClass {
        public String name;

        public String[] drops = new String[3];

        /**
         * Constructor for a treasure class.
         * @param name the name of the treasure class
         * @param drop1 the first drop
         * @param drop2 the second drop
         * @param drop3 the third drop
         */
        private TreasureClass(String name, String drop1, String drop2, String drop3) {
            this.name = name;
            drops[0] = drop1;
            drops[1] = drop2;
            drops[2] = drop3;
        }
    }

    /**
     * Returns true if the given name is a treasure class, and false otherwise.
     * @param name the name of the treasure class
     * @return true if the given name is a treasure class, and false otherwise
     */
    public boolean containsKey(String name) {
        for (int i = 0; i < treasureTypes.size(); i++) {
            if ((treasureTypes.get(i).name).equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the treasure class with the given name.
     * @param className the name of the treasure class
     * @return the treasure class with the given name, or null if it does not exist
     */
    public TreasureClass getClass(String className) {
        for (int i = 0; i < treasureTypes.size(); i++) {
            TreasureClass treasureI = treasureTypes.get(i);
            if ((treasureI.name).equals(className)) { 
                return treasureI;
            }
        }
        return null;
    }

    /**
     * Returns a random drop from the given treasure class.
     * @param name the name of the treasure class
     * @return a random drop from the given treasure class
     */
    public String getRandomDrop(String name) {
        if (containsKey(name)) { // we have not yet reached a base item
            Random r = new Random();
            int i = r.nextInt(3);
            TreasureClass class1 = getClass(name);
            name = getRandomDrop(class1.drops[i]);
        }
        return name; // we have reached base item
    }
    
    /**
     * Adds a treasure class to the list of treasure classes.
     * @param name the name of the treasure class
     * @param drop1 the first drop
     * @param drop2 the second drop
     * @param drop3 the third drop
     */
    public void addClass(String name, String drop1, String drop2, String drop3) {
        treasureTypes.add(new TreasureClass(name, drop1, drop2, drop3));
    }
}