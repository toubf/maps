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
        
        if ((letters.get(prefIndex) == null) || 
            (letters.get(prefIndex).key == (int) ch)) { //correctly keyed entry already exists, or slot is empty
            letters.set(prefIndex, (new Pair<Integer, Integer> ((int) ch, v)));
        }
        
        int index = prefIndex + 1;
        while (true) {
            if ((letters.get(index) == null) ||
                (letters.get(index).key == (int) ch)) { //we find it later or hit empty slot
                letters.set(index, (new Pair<Integer, Integer> ((int) ch, v)));                
            } else if (index == prefIndex) { // wrapped all the way around
                ArrayList<Pair<Integer, Integer>> oldLetters = (ArrayList<Pair<Integer, Integer>>) letters.clone();
                ///letterCap *= 2;
                letters = new ArrayList<Pair<Integer, Integer>>(letters.size() * 2);
                for (int i = 0; i < oldLetters.size() * 2; i++) {
                    letters.add(null);
                }
                for (int i = 0; i < letters.size(); i++) {
                    int newKey = (int) oldLetters.get(i).key;
                    int newValue = (int) oldLetters.get(i).key;
                    put((char) newKey, newValue);
                }
            }

            if (index >= letters.size() - 1) { //increment & loop around
                index = 0;
            } else {
                index++;
            }
        }
    }
    
    int get(char ch) {
        int prefIndex = ((int)ch) % letters.size();
        if (letters.get(prefIndex) != null) {
            if (letters.get(prefIndex).key == (int)ch) { //correctly keyed entry already exists
                return letters.get(prefIndex).value;
            }
            
            int index = prefIndex + 1;
            while (true) {
                if (letters.get(index).key == (int)ch) {
                    return letters.get(index).value;
                } else if ((letters.get(index) == null) || index == prefIndex) { //hit empty slot or wrapped all the way around
                    throw new IllegalArgumentException();
                }

                if (index >= letters.size() - 1) { //increment & loop around
                    index = 0;
                } else {
                    index++;
                }
            }
        }
        throw new IllegalArgumentException();
    }
}