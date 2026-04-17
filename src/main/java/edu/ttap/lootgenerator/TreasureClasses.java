package edu.ttap.lootgenerator;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class TreasureClasses {
    ArrayList<TreasureClass> treasureTypes = new ArrayList<>();
    
    private class TreasureClass {
        public String name;
        public String[] drops = new String[3];
        private TreasureClass(String name, String drop1, String drop2, String drop3) {
            this.name = name;
            drops[0] = drop1;
            drops[1] = drop2;
            drops[2] = drop3;
        }
    }

    public boolean containsKey(String name) {
        for (int i = 0; i < treasureTypes.size(); i++) {
            if ((treasureTypes.get(i).name).equals(name)) {
                return true;
            }
        }
        return false;
    }

    public TreasureClass getClass(String className) {
        for (int i = 0; i < treasureTypes.size(); i++) {
            TreasureClass treasureI = treasureTypes.get(i);
            if ((treasureI.name).equals(className)) { 
                return treasureI;
            }
        }
        return null;
    }

    public String getRandomDrop(String name) {
        if (containsKey(name)) { // we have not yet reached a base item
            Random r = new Random();
            int i = r.nextInt(3);
            TreasureClass class1 = getClass(name);
            name = getRandomDrop(class1.drops[i]);
        }
        return name; // we have reached base item
    }
    
    public void addClass(String name, String drop1, String drop2, String drop3) {
        treasureTypes.add(new TreasureClass(name, drop1, drop2, drop3));
    }
}