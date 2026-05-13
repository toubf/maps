package edu.ttap.intmap;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/** IntegerMaps class */
public class IntegerMaps {
    
    /** Class for a key-value pair. */
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

    /**
     * Counts the number of occurrences of each character in a file and prints the counts.
     * @param path the path to the file to read
     * @throws FileNotFoundException
     */
    public static void reportCounts(String path) throws FileNotFoundException {
        LetterCounter letters = new LetterCounter();

        File toRead = new File(path);
        Scanner in = new Scanner(toRead);
        while (in.hasNextLine()) {
            String line = in.nextLine();
            line = line.toLowerCase();
            for (int i = 0; i < line.length(); i++) {
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

        for (int i = 0; i < letters.size(); i++) {
            letters.print(i);
        }
    }

    /**
     * Counts the number of unique characters in a file
     * @param path the path to the file to read
     * @return the number of unique characters in the file
     * @throws FileNotFoundException
     */
    public static int countChars(String path) throws FileNotFoundException {
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
            System.out.print("<" + c + "," + (int) c + "> ");
        }
        return chars.size();
    }

    /** 
     * The main method. 
     * @param args the command line arguments
     * @throws FileNotFoundException
    */
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length != 1) {
            System.err.println(
                "Usage: java IntegerMaps <filename>");
            System.exit(1);
        }
        reportCounts(args[0]);
    }
}