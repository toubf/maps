package edu.ttap.lootgenerator;

import java.util.Random;

public class Affix {
    String name;
    String mod1code;
    int mod1min;
    int mod1max;

    public Affix (String name, String mod, int min, int max) {
        this.name = name;
        this.mod1code = mod;
        this.mod1min = min;
        this.mod1max = max;
    }

    public String printStat() {
        Random r = new Random();
        int stat = r.nextInt(mod1min, mod1max + 1);
        return stat + " " + mod1code;
    }
}