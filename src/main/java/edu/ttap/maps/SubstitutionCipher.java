package edu.ttap.maps;

import java.util.Map;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * A substitution cipher is a simple encryption scheme that associates each
 * letter of the alphabet with a different letter.
 */
public class SubstitutionCipher {
    /**
     * Creates a substitution cipher by reading a mapping of characters from the given
     * file. Each mapping of the file should be of the form "a b", where 'a' is mapped to
     * 'b' in the cipher. We require 
     * @param filename the name of the file containing the mapping
     * @return the cipher as a mapping between characters
     */
    public static Map<Character, Character> createCipher(String filename) throws IOException {
        File file = new File(filename);
        Scanner in = new Scanner(file);
        Map<Character, Character> cipher = new AssociationList<>();
        while(in.hasNextLine()){
            String line = in.nextLine();
            cipher.put(line.charAt(0), line.charAt(2));
        }
        
        return cipher;
    }

    /**
     * Determines whether the given mapping is a valid substitution cipher. A cipher is
     * valid if (a) it maps every letter of the alphabet (aâ€“z) and (b) it is a bijection,
     * i.e., no two letters map to the same letter (so that we can roundtrip encode/decode
     * a message without loss of fidelity).
     * @param cipher
     * @return true iff the given mapping is a valid substitution cipher
     */
    public static boolean isValidCipher(Map<Character, Character> cipher) {
        if (cipher.size() != 26)
            return false;
        for(int i = 97; i < 123; i++) { //lowercase a-z hopefully
            char ch = (char) i;
            if ((!cipher.containsValue(ch)) || (!cipher.containsKey(ch))){
                return false;
            }
        }
        return true;
    }

    /**
     * Given a valid substitution cipher, produces the inverse mapping, which
     * can be used to decode the encoded massage. For example, if the cipher
     * maps 'a' to 'b', then the inverse mapping should map 'b' to 'a'.
     * @param cipher the cipher to invert
     * @return the inverse mapping of the given cipher
     */
    public static Map<Character, Character> invertCipher(Map<Character, Character> cipher) {
        Map<Character, Character> newCipher = new AssociationList<>();
        for(char k : cipher.keySet()) {
            newCipher.put(cipher.get(k), k);
        }
        return newCipher;
    }

    /**
     * Translates the given string using the provided mapping.
     * @param s the string to translate
     * @param mapping the mapping to use
     * @return the translated string
     */
    public static String translate(String s, Map<Character, Character> mapping) {
        int len = s.length();
        char[] ret = new char[len];
        for (int i = 0; i <= len; i++){
            ret[i] = mapping.get(s.charAt(i));
        }
        return (new String(ret));
    }

    /**
     * The main driver for the substitution cipher program.
     * @param args the driver's command-line arguments
     */
    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            System.err.println(
                "Usage: java SubstitutionCipher <encode|decode> <cipherfile> <filename>");
            System.exit(1);
        }
        Map<Character, Character> cipher = createCipher(args[1]);
        if (!isValidCipher(cipher)) {
            System.err.println("Invalid cipher");
        }
        char invert = args[0].charAt(0);
        if (invert == 'd') {
            Map<Character, Character> invMapping = invertCipher(cipher);
        }
    }
}
