package edu.ttap.lootgenerator;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class Armors {
    ArrayList<Armor> armors = new ArrayList<>();

    public class Armor {
        public String name;
        public int lowerBound;
        public int upperBound;
        public Armor(String name, int min, int max) {
            this.name = name;
            this.lowerBound = min;
            this.upperBound = max;
        }

        public int getDefense() {
            Random r = new Random();
            return r.nextInt(lowerBound, upperBound);
        }
    }
    
    public boolean containsKey(String name) {
        for (int i = 0; i < armors.size(); i++) {
            if ((armors.get(i).name).matches(name)) {
                return true;
            }
        }
        return false;
    }

    public Armor getArmor(String armorPiece) {
        for (int i = 0; i < armors.size(); i++) {
            if ((armors.get(i).name).matches(armorPiece)) {
                return armors.get(i);
            }
        }
        return null;
    }

    public void addArmor(String name, int min, int max){
        armors.add(new Armor(name, min, max));
    }


}   
