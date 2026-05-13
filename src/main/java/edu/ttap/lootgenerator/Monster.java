package edu.ttap.lootgenerator;

/** Class representing a monster */
public class Monster {
    public String name;

    public String treasureClass;

    /**
     * Constructor for a monster.
     * @param name the name of the monster 
     * @param treasureClass the treasure class of the monster
     */
    public Monster(String name, String treasureClass) {
        this.name = name;
        this.treasureClass = treasureClass;
    }
}