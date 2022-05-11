package Parsers;

import ItemsAndSpells.Item;
import ItemsAndSpells.Weapon;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class WeaponParser {
    private static ArrayList<Weapon> weapons = parseDatabase();

    public static ArrayList<Weapon> parseDatabase() {
        ArrayList<Weapon> weapons = new ArrayList<>();
        Scanner inputStream = null;

        try {
            inputStream = new Scanner(new FileInputStream("Databases/weapons_database.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File Databases\\weapons_database.txt was not found");
            System.out.println("or could not be opened.");
            System.exit(0); // todo error then system.exit()
        }

        while (inputStream.hasNextLine()) {
            String[] splittedLine1 = inputStream.nextLine().split("; ");
            String name = splittedLine1[0];
            String description = splittedLine1[1];

            String[] splittedLine2 = inputStream.nextLine().split("; ");
            int value = Integer.parseInt(splittedLine2[0]);
            int damage = Integer.parseInt(splittedLine2[1]);

            String[] splittedLine3 = inputStream.nextLine().split("; ");
            boolean isLootable = (splittedLine3[0].equals("true"));
            boolean isRanged = (splittedLine3[1].equals("true"));

            weapons.add(new Weapon(name, description, value, damage, isLootable, isRanged));
            inputStream.nextLine();
        }
        return weapons;
    }

    public static ArrayList<Item> getSelectedWeapons(String... names) {
        ArrayList<Item> selectedWeapons = new ArrayList<>();

        for (int i = 0; i < names.length; i++){
            for (int j = 0; j < weapons.size(); j++){
                if (names[i].equals(weapons.get(j).getName()))
                    selectedWeapons.add(weapons.get(j).clone());
            }
        }

        return selectedWeapons;
    }
}
