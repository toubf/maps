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
        LetterCounter letters = new LetterCounter();

        File toRead = new File(path);
        Scanner in = new Scanner(toRead);
        while (in.hasNextLine()) {
            String line = in.nextLine();
            line = line.toLowerCase();
            for(int i = 0; i < line.length(); i++) {
                int c = (int) (line.charAt(i));            
                int prevNum;
                try {
                    prevNum = letters.get((char) c) + 1;
                } catch (Exception e) {
                    prevNum = 0;
                }
                letters.put((char) c, prevNum + 1);
            }
        }
        in.close();

        for(int i = 0; i < letters.size(); i++){
            letters.print(i);
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

    public static void main (String[] args) throws FileNotFoundException {
        if (args.length != 1) {
            System.err.println(
                "Usage: java IntegerMaps <filename>");
            System.exit(1);
        }
        reportCounts(args[0]);
        //countChars(args[0]);
    }
}