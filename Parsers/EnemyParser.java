package Parsers;

import Entity.Enemy;
import Entity.Mage;
import ItemsAndSpells.Item;
import ItemsAndSpells.Spell;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class EnemyParser {
    private ArrayList<Enemy> enemies = new ArrayList<>();

    public void parseDatabase() {
        Scanner inputStream = null;

        try {
            inputStream = new Scanner(new FileInputStream("Databases\\enemies_database.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File Databases\\enemies_database.txt was not found");
            System.out.println("or could not be opened.");
            System.exit(0); // todo error then system.exit()
        }

        while (inputStream.hasNextLine()) {
            String[] splittedLine1 = inputStream.nextLine().split("; ");
            String name = splittedLine1[0];
            int armorClass = Integer.parseInt(splittedLine1[1]);
            int hitPoints = Integer.parseInt(splittedLine1[2]);
            int speed = Integer.parseInt(splittedLine1[3]);

            String[] splittedLine2 = inputStream.nextLine().split("; ");
            int strength = Integer.parseInt(splittedLine2[0]);
            int dexterity = Integer.parseInt(splittedLine2[1]);
            int constitution = Integer.parseInt(splittedLine2[2]);
            int intelligence = Integer.parseInt(splittedLine2[3]);
            int wisdom = Integer.parseInt(splittedLine2[4]);
            int charisma = Integer.parseInt(splittedLine2[5]);

            String[] splittedLine3 = inputStream.nextLine().split("; ");
            ArrayList<Item> inventory = ItemParser.getSelectedItems(splittedLine3);

            String[] splittedLine4 = inputStream.nextLine().split("; ");
            ArrayList<Integer> inventoryCount = new ArrayList<>(splittedLine4.length);
            for (int i = 0; i < splittedLine4.length; i++){
                inventoryCount.add(Integer.parseInt(splittedLine4[i]));
            }

            String[] splittedLine5 = inputStream.nextLine().split("; ");
            int XP = Integer.parseInt(splittedLine5[0]);
            Enemy.AItype enemyType = Enemy.AItype.valueOf(splittedLine5[1]);

            if (enemyType == Enemy.AItype.MAGE){
                String[] splittedLine6 = inputStream.nextLine().split("; ");
                ArrayList<Spell> spells = SpellParser.getSelectedSpells(splittedLine6);
                enemies.add(new Mage(name, armorClass, hitPoints, speed, strength, dexterity, constitution, intelligence, wisdom, charisma, inventory, inventoryCount, XP,enemyType, spells));
            }else{
                enemies.add(new Enemy(name, armorClass, hitPoints, speed, strength, dexterity, constitution, intelligence, wisdom, charisma, inventory, inventoryCount, XP,enemyType));
            }


        }
    }

}
