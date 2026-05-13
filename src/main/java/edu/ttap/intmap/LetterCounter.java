package edu.ttap.intmap;

import java.util.ArrayList;

/** LetterCounter class */
public class LetterCounter {
    
    /**
     * Class for a key-value pair.
     */
    public class Pair<K, V> {
        public K key;

        public V value;

        /**
         * Constructor for a key-value pair.
         * @param key
         * @param value
         */
        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    /** ArrayList to store letter counts */
    ArrayList<Pair<Integer, Integer>> letters;

    /** Constructor for LetterCounter. Initializes the array list to store letter counts. */
    public LetterCounter() {
        letters = new ArrayList<Pair<Integer, Integer>>(35);
        for (int i = 0; i < 35; i++) {
            letters.add(null);
        }
    }

    /**
     * Checks whether the given character is a key in the map.
     * @param ch the character to check for in the map
     * @return true iff the given character is a key in the map
     */
    boolean hasKey(char ch) {
        try {
            letters.get(ch);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    /**
     * Puts the given key-value pair into the map.
     * @param ch the character key to put into the map
     * @param v the value to associate with the given key
     */
    void put(char ch, int v) {
        int prefIndex = ((int) ch) % letters.size();
        
        //correctly keyed entry already exists, or slot is empty
        if ((letters.get(prefIndex) == null) 
            || (letters.get(prefIndex).key == (int) ch)) {
            letters.set(prefIndex, (new Pair<Integer, Integer>((int) ch, v)));
        }
        
        int index = prefIndex;
        while (true) {
            //increment & loop around
            if (index >= letters.size() - 1) { 
                index = 0;
            } else {
                index++;
            }

            // found correct entry or empty slot
            if ((letters.get(index) == null)
                || (letters.get(index).key == (int) ch)) { 
                letters.set(index, (new Pair<Integer, Integer>((int) ch, v)));
                return;           
            } else if (index == prefIndex) { 
                ArrayList<Pair<Integer, Integer>> oldLetters = 
                    (ArrayList<Pair<Integer, Integer>>) letters.clone();
                letters = new ArrayList<Pair<Integer, Integer>>(letters.size() * 2);
                for (int i = 0; i < oldLetters.size() * 2; i++) {
                    letters.add(null);
                }
                for (int i = 0; i < letters.size(); i++) {
                    int newKey = (int) oldLetters.get(i).key;
                    int newValue = (int) oldLetters.get(i).key;
                    put((char) newKey, newValue);
                }
                put(ch, v);
                return;
            }
        }
    }
    
    /**
     * Gets the value associated with the given key.
     * @param ch the character key to get the value for
     * @return the value associated with the given key
     */
    int get(char ch) {
        int prefIndex = ((int) ch) % letters.size();
        if (letters.get(prefIndex) != null) {
            //correctly keyed entry already exists
            if (letters.get(prefIndex).key == (int) ch) { 
                return letters.get(prefIndex).value;
            }
            
            int index = prefIndex;
            while (true) {
                //increment & loop around
                if (index >= letters.size() - 1) { 
                    index = 0;
                } else {
                    index++;
                }

                if (letters.get(index).key == (int) ch) {
                    return letters.get(index).value;
                } else if ((letters.get(index) == null) || index == prefIndex) { 
                    throw new IllegalArgumentException();
                }
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     * Prints the key-value pair at the given index in the array list.
     * @param index the index to print the key-value pair for
     */
    public void print(int index) {
        if (letters.get(index) != null) {
            Integer e = letters.get(index).key;
            char c = (char) e.intValue();
            System.out.println(c + ": " + letters.get(index).value);
        }
    }

    /**
     * @return the number of key-value pairs in the map
     */
    public int size() {
        return letters.size();
    }
}