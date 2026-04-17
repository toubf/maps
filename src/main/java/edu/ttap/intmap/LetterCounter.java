package edu.ttap.intmap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import java.util.List;
import java.util.ArrayList;

public class LetterCounter {
    public class Pair<K, V> {
        public K key;
        public V value;
        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    ArrayList<Pair<Integer, Integer>> letters;

    public LetterCounter() {
        letters = new ArrayList<Pair<Integer, Integer>>(35);
        for (int i = 0; i < 35; i++) {
            letters.add(null);
        }
    }

    boolean hasKey(char ch) {
        try {
            letters.get(ch);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    void put(char ch, int v){
        int prefIndex = ((int) ch) % letters.size();
        
        //correctly keyed entry already exists, or slot is empty
        if ((letters.get(prefIndex) == null) || 
            (letters.get(prefIndex).key == (int) ch)) {
            letters.set(prefIndex, (new Pair<Integer, Integer> ((int) ch, v)));
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
            if ((letters.get(index) == null) ||
                (letters.get(index).key == (int) ch)) { 
                letters.set(index, (new Pair<Integer, Integer> ((int) ch, v)));
                return;           
            }
            // wrapped around - expand array
            else if (index == prefIndex) { 
                ArrayList<Pair<Integer, Integer>> oldLetters = (ArrayList<Pair<Integer, Integer>>) letters.clone();
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
    
    int get(char ch) {
        int prefIndex = ((int)ch) % letters.size();
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

                if (letters.get(index).key == (int)ch) {
                    return letters.get(index).value;
                } else if ((letters.get(index) == null) || index == prefIndex) { //hit empty slot or wrapped all the way around
                    throw new IllegalArgumentException();
                }
            }
        }
        throw new IllegalArgumentException();
    }

    public void print(int index) {
        if (letters.get(index) != null){
            Integer e = letters.get(index).key;
            char c = (char) e.intValue();
            System.out.println(c + ": " + letters.get(index).value);
        }
    }

    public int size() {
        return letters.size();
    }
}