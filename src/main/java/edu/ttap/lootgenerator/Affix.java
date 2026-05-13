package edu.ttap.lootgenerator;

import java.util.Random;

/** Class representing an affix for loot items. */
public class Affix {
    String name;

    String mod1code;

    int mod1min;

    int mod1max;

    /**
     * Constructor for an affix.
     * @param name the name of the affix
     * @param mod the code for the stat that this affix modifies
     * @param min the minimum value for the stat that this affix modifies
     * @param max the maximum value for the stat that this affix modifies
     */
    public Affix(String name, String mod, int min, int max) {
        this.name = name;
        this.mod1code = mod;
        this.mod1min = min;
        this.mod1max = max;
    }


    /**
     * Returns a string representing the stat for this affix.
     * @return a string representing the stat for this affix
     */
    public String printStat() {
        Random r = new Random();
        int stat = r.nextInt(mod1min, mod1max + 1);
        return stat + " " + mod1code;
    }
}