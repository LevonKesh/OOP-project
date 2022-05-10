package Parsers;

import ItemsAndSpells.Weapon;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class WeaponParser {
    private ArrayList<Weapon> weapons = new ArrayList<>();

    public void parseDatabase() {
        Scanner inputStream = null;

        try {
            inputStream = new Scanner(new FileInputStream("Databases\\weapons_database.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File Databases\\weapons_database.txt was not found");
            System.out.println("or could not be opened.");
            System.exit(0); // todo error then system.exit()
        }

        while (inputStream.hasNextLine()) {
            String line = inputStream.nextLine();
            if (line != "") {
                String[] splittedLine1 = line.split("; ");
                String name = splittedLine1[0];
                String description = splittedLine1[0];

                String[] splittedLine2 = inputStream.nextLine().split("; ");
                int value = Integer.parseInt(splittedLine2[0]);
                int damage = Integer.parseInt(splittedLine2[1]);

                String[] splittedLine3 = inputStream.nextLine().split("; ");
                boolean isLootable = (splittedLine3[0] == "true");
                boolean isRanged = (splittedLine3[1] == "true");

                weapons.add(new Weapon(name, description, value, damage, isLootable, isRanged));
            }
        }
    }

    public ArrayList<Weapon> getSelectedWeapons(String... names) {
        ArrayList<Weapon> selectedWeapons = new ArrayList<>();

        for (int i = 0; i < names.length; i++){
            for (int j = 0; j < weapons.size(); j++){
                if (names[i] == weapons.get(j).getName())
                    selectedWeapons.add(weapons.get(j).clone());
            }
        }

        return selectedWeapons;
    }


}
