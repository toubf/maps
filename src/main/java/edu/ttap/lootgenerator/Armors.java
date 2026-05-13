package edu.ttap.lootgenerator;

import java.util.Random;
import java.util.ArrayList;

/** Class representing a collection of armor pieces. */
public class Armors {
    ArrayList<Armor> armors = new ArrayList<>();
    
    /** Class for an armor piece. */
    public class Armor {
        public String name;

        public int lowerBound;

        public int upperBound;

        /** 
         * Constructor for an armor piece. 
         * @param name the name of the armor piece
         * @param min the minimum defense value for the armor piece
         * @param max the maximum defense value for the armor piece
        */
        public Armor(String name, int min, int max) {
            this.name = name;
            this.lowerBound = min;
            this.upperBound = max;
        }

        /** 
         * Returns the defense value for this armor piece. 
         * @return a random integer between the lower and upper bounds for this armor piece
         */
        public int getDefense() {
            Random r = new Random();
            return r.nextInt(lowerBound, upperBound);
        }
    }
    
    /**
     * Checks if the collection contains an armor piece with the given name.
     * @param name the name of the armor piece to check for
     * @return true if the collection contains an armor piece with the given name
     */
    public boolean containsKey(String name) {
        for (int i = 0; i < armors.size(); i++) {
            if ((armors.get(i).name).matches(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the armor piece with the given name.
     * @param armorPiece the name of the armor piece to return
     * @return the armor piece with the given name, or null if not found
     */
    public Armor getArmor(String armorPiece) {
        for (int i = 0; i < armors.size(); i++) {
            if ((armors.get(i).name).matches(armorPiece)) {
                return armors.get(i);
            }
        }
        return null;
    }

    /**
     * Adds an armor piece to the collection.
     * @param name the name of the armor piece to add
     * @param min the minimum defense value for the armor piece
     * @param max the maximum defense value for the armor piece
     */
    public void addArmor(String name, int min, int max) {
        armors.add(new Armor(name, min, max));
    }


}   
