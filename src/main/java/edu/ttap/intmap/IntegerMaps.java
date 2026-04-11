package edu.ttap.intmap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class IntegerMaps {    
    public class Pair<K, V> {
        public K key;
        public V value;
        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void reportCounts (String path) throws FileNotFoundException {
        List<Integer> letters = new ArrayList<>();
        for(int i = 0; i < 26; i++) {
            letters.add(i, 0);
        }

        File toRead = new File(path);
        Scanner in = new Scanner(toRead);
        while (in.hasNextLine()) {
            String line = in.nextLine();
            line = line.toLowerCase();
            for(int i = 0; i < line.length(); i++) {
                int c = (int)(line.charAt(i)) - 97;
                if((c >= 0) && (c < 26)) {
                    letters.set(c, letters.get(c)+1);
                }
            }
        }
        in.close();

        for (int i = 0; i < 26; i++){
            char letter = (char) (i + 97);
            System.out.println(letter + ": " + letters.get(i));
        }
    }

    public static int countChars (String path) throws FileNotFoundException {
        Set<Character> chars = new TreeSet<>();
        File toRead = new File(path);
        Scanner in = new Scanner(toRead);
        while (in.hasNextLine()) {
            String line = in.nextLine();
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                chars.add(c);
            }
        }
        in.close();
        for (Character c : chars) {
            System.out.print("<" + c + "," + (int)c + "> ");
        }
        return chars.size();
    }

    private class LetterCounter {
        ArrayList<Pair<Integer, Integer>> letters; 
        private LetterCounter() {
            letters = new ArrayList<>(35); //intl capacity of 35?
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

        }
        
        int get(char ch) {
            int prefIndex = ((int)ch) % letters.size();
            if (letters.get(prefIndex).key != null) {
                if (letters.get(prefIndex).key == (int)ch) {
                    return letters.get(prefIndex).value;
                }
                
                int index = prefIndex + 1;
                while (true) {
                    if (letters.get(index).key == (int)ch) {
                        return letters.get(index).value;
                    }
                    if ((letters.get(index).key == null) || index == prefIndex) { //hit empty slot or wrapped all the way around
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

    public static void main (String[] args) throws FileNotFoundException {
        if (args.length != 1) {
            System.err.println(
                "Usage: java IntegerMaps <filename>");
            System.exit(1);
        }
        //reportCounts(args[0]);
        countChars(args[0]);
    }
}