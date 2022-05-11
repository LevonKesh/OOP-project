package Parsers;

import ItemsAndSpells.Spell;
import ItemsAndSpells.Weapon;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class SpellParser {
    private static ArrayList<Spell> spells = parseDatabase();

    public static ArrayList<Spell> parseDatabase() {
        ArrayList<Spell> spells = new ArrayList<>();
        Scanner inputStream = null;

        try {
            inputStream = new Scanner(new FileInputStream("Databases/spells_database.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File Databases\\spells_database.txt was not found");
            System.out.println("or could not be opened.");
            System.exit(0); // todo error then system.exit()
        }

        while (inputStream.hasNextLine()) {
            String[] splittedLine1 = inputStream.nextLine().split("; ");
            String name = splittedLine1[0];
            String description = splittedLine1[1];

            String[] splittedLine2 = inputStream.nextLine().split("; ");
            Spell.SpellType spellType = Spell.SpellType.valueOf(splittedLine2[0]);
            int value = Integer.parseInt(splittedLine2[1]);

            spells.add(new Spell(name, description, spellType, value));
            inputStream.nextLine();
        }
        return spells;
    }

    public static ArrayList<Spell> getSelectedSpells(String... names) {
        ArrayList<Spell> selectedSpells = new ArrayList<>();

        for (int i = 0; i < names.length; i++){
            for (int j = 0; j < spells.size(); j++){
                if (names[i].equals(spells.get(j).getName()))
                    selectedSpells.add(spells.get(j).clone());
            }
        }

        return selectedSpells;
    }

}
