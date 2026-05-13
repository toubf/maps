package edu.ttap.lootgenerator;

import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

/** Class representing the loot generator. */
public class LootGenerator {
    /** The path to the dataset (either the small or large set). */
    private static final String DATA_SET = "data/large";

    /** 
     * Generates a list of monsters from the dataset. 
     * @param monsters the list of monsters to populate with the dataset
    */
    public static void generateMonsters(ArrayList<Monster> monsters) throws FileNotFoundException {
        String monstersPath = DATA_SET + "/monstats.txt";
        File toRead = new File(monstersPath);
        Scanner mons = new Scanner(toRead);
        while (mons.hasNextLine()) {
            String line = mons.nextLine();
            String[] lineArgs = line.split("\t");
            String name = lineArgs[0];
            String tClass = lineArgs[3];
            monsters.add(new Monster(name, tClass));
        }
    }

    /** 
     * Generates a list of treasure classes from the dataset. 
     * @param treasure the list of treasure classes to populate with the dataset
    */
    public static void generateTreasureClasses(TreasureClasses treasure) 
        throws FileNotFoundException {
        String treasurePath = DATA_SET + "/TreasureClassEx.txt";
        File toRead = new File(treasurePath);
        Scanner tre = new Scanner(toRead);
        while (tre.hasNextLine()) {
            String line = tre.nextLine();
            String[] lineArgs = line.split("\t");
            String name = lineArgs[0];
            String drop1 = lineArgs[1];
            String drop2 = lineArgs[2];
            String drop3 = lineArgs[3];
            treasure.addClass(name, drop1, drop2, drop3);
        }
    }

    /** 
     * Generates a list of armor from the dataset. 
     * @param armors the list of armor to populate with the dataset
    */
    public static void generateArmor(Armors armors) throws FileNotFoundException {
        String armorPath = DATA_SET + "/armor.txt";
        File toRead = new File(armorPath);
        Scanner arm = new Scanner(toRead);
        while (arm.hasNextLine()) {
            String line = arm.nextLine();
            String[] lineArgs = line.split("\t");
            String name = lineArgs[0];
            int lowerBound = Integer.valueOf(lineArgs[1]);
            int upperBound = Integer.valueOf(lineArgs[2]);
            armors.addArmor(name, lowerBound, upperBound);
        }
    }

    /** 
     * Generates a list of affixes from the dataset. 
     * @param affixes the list of affixes to populate with the dataset
     * @param txtfile the name of the text file containing the affixes to generate
    */
    public static void generateAffixes(ArrayList<Affix> affixes, String txtfile) 
        throws FileNotFoundException {
        String path = DATA_SET + txtfile;
        File toRead = new File(path);
        Scanner in = new Scanner(toRead);
        while (in.hasNextLine()) {
            String line = in.nextLine();
            String[] lineArgs = line.split("\t");
            String name = lineArgs[0];
            String mod = lineArgs[1];
            int modMin = Integer.valueOf(lineArgs[2]);
            int modMax = Integer.valueOf(lineArgs[3]);
            affixes.add(new Affix(name, mod, modMin, modMax));
        }
    }

    /**
     * Picks a random affix from the given list of affixes.
     * @param affixes the list of affixes to pick from
     * @param affix the affix to potentially replace with a random affix from the list
     * @return a random affix from the list of affixes, or the given affix if no affix is picked
     */
    public static Affix pickAffix(ArrayList<Affix> affixes, Affix affix) {
        Random rand = new Random();
        if (rand.nextBoolean() == true) { // 50/50 on adding affix
            int j = rand.nextInt(affixes.size());
            return (affixes.get(j));
        }
        return affix;
    }

    /**
     * The main method.
     * @param args the command line arguments
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Monster> monsters = new ArrayList<>();
        TreasureClasses treasure = new TreasureClasses();
        Armors armors = new Armors();
        ArrayList<Affix> prefixes = new ArrayList<>();
        ArrayList<Affix> suffixes = new ArrayList<>();
        
        generateMonsters(monsters);
        generateTreasureClasses(treasure);
        generateArmor(armors);
        generateAffixes(prefixes, "/MagicPrefix.txt");
        generateAffixes(suffixes, "/MagicSuffix.txt");

        while (true) {
            Random rand = new Random();
            int i = rand.nextInt(monsters.size());
            Monster randMonster = monsters.get(i);
            System.out.println("Fighting " + randMonster.name + "...");
            try {
                Thread.sleep(2000); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("You have slain " + randMonster.name + "!");
            System.out.println(randMonster.name + " dropped: ");

            String randomClass = randMonster.treasureClass;
            String baseItem = treasure.getRandomDrop(randomClass); //now we have rand base item

            int def = armors.getArmor(baseItem).getDefense();

            Affix prefix = new Affix("", "", 0, 0);
            Affix suffix = new Affix("", "", 0, 0);
            prefix = pickAffix(prefixes, prefix);
            suffix = pickAffix(suffixes, suffix);

            System.out.println(prefix.name + " " + baseItem + " " + suffix.name);
            System.out.println(def + " Defense");
            if (prefix.mod1max != 0) {
                System.out.println(prefix.printStat());
            }
            if (suffix.mod1max != 0) {
                System.out.println(suffix.printStat());
            }

            Scanner in = new Scanner(System.in);
            while (true) {
                System.out.println("Fight again [Y/N]?");
                String input = in.nextLine();
                input = input.toLowerCase();
                char ch = input.charAt(0);
                if (ch == 'y') {
                    break;
                }
                if (ch == 'n') {
                    in.close();
                    return;
                }
            }
        }
    }
}