package Parsers;

import ItemsAndSpells.Item;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class ItemParser {
    private ArrayList<Item> items = new ArrayList<>();

    public void parseDatabase() {
        Scanner inputStream = null;

        try {
            inputStream = new Scanner(new FileInputStream("Databases\\items_database.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File Databases\\items_database.txt was not found");
            System.out.println("or could not be opened.");
            System.exit(0); // todo error then system.exit()
        }

        while (inputStream.hasNextLine()) {
            String[] splittedLine1 = inputStream.nextLine().split("; ");
            String name = splittedLine1[0];
            String description = splittedLine1[1];

            String[] splittedLine2 = inputStream.nextLine().split("; ");
            int value = Integer.parseInt(splittedLine2[0]);
            boolean isLootable = (splittedLine2[1] == "true");

            items.add(new Item(name, description, value, isLootable));
            inputStream.nextLine();
        }
    }

    public ArrayList<Item> getSelectedItems(String... names) {
        ArrayList<Item> selectedItems = new ArrayList<>();

        for (int i = 0; i < names.length; i++){
            for (int j = 0; j < items.size(); j++){
                if (names[i].equals(items.get(j).getName()))
                    selectedItems.add(items.get(j).clone());
            }
        }

        return selectedItems;
    }
}
