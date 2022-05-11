package Entity;

import java.util.ArrayList;

import ItemsAndSpells.*;
import Parsers.ItemParser;
import Parsers.WeaponParser;

public class Player extends Entity {
    private int availableSkillPoints = 0;

    public Player() {
        super();
    }

    public Player(String name, int armorClass, int hitPoints, int speed, int strength, int dexterity, int constitution, int intelligence, int wisdom, int charisma, ArrayList<Item> inventory, ArrayList<Integer> inventoryCount) {
        super(name, armorClass, hitPoints, speed, strength, dexterity, constitution, intelligence, wisdom, charisma,
                inventory, inventoryCount);
    }

    public Player(String name) {
        super(name, 20, 200, 4, 12, 12, 12,12, 12, 12,
                getBasicInventory(), getBasicInventoryCount());
    }

    public void setChosenWeapon(int index) {
        setChosenWeapon(getWeapons().get(index));
    }

    public void increaseStrength() {
        if (availableSkillPoints > 0) {
            setStrength(getStrength() + 1);
            availableSkillPoints--;
        }
    }

    public void increaseDexterity() {
        if (availableSkillPoints > 0) {
            setDexterity(getDexterity() + 1);
            availableSkillPoints--;
        }
    }

    public void increaseConstitution() {
        if (availableSkillPoints > 0) {
            setConstitution(getConstitution() + 1);
            availableSkillPoints--;
        }
    }

    public void increaseIntelligence() {
        if (availableSkillPoints > 0) {
            setIntelligence(getIntelligence() + 1);
            availableSkillPoints--;
        }
    }

    public void increaseWisdom() {
        if (availableSkillPoints > 0) {
            setWisdom(getWisdom() + 1);
            availableSkillPoints--;
        }
    }

    public void increaseCharisma() {
        setCharisma(getCharisma() + 1);
        if (availableSkillPoints > 0) {
            setStrength(getStrength() + 1);
            availableSkillPoints--;
        }
    }

    public int getAvailableSkillPoints() {
        return availableSkillPoints;
    }

    public void addSkillPoints(int skillPoints) {
        availableSkillPoints += skillPoints;
    }

    public boolean checkForPotion() {
        if (this.getInventory().contains(new Item("Healing Potion",
                "Healing potion made by the priests from the shrine of Artesha", 50,  true))) {
            return true;
        } else return false;
    }

    public void usePotion() {
        if (checkForPotion()) {
            takeFromInventory(new Item("Healing Potion",
                    "Healing potion made by the priests from the shrine of Artesha", 50,  true), 1);
            this.setHitPoints(this.getHitPoints() + 50);
        }
    }

    private static ArrayList<Item> getBasicInventory() {
        ArrayList<Item> inventory = ItemParser.getSelectedItems("Coins", "Healing Potion");
        ArrayList<Item> weapons = WeaponParser.getSelectedWeapons("Iron Sword");
        inventory.addAll(weapons);
        return inventory;
    }

    private static ArrayList<Integer> getBasicInventoryCount() {
        ArrayList<Integer> inventoryCount = new ArrayList<>();
        inventoryCount.add(300);
        inventoryCount.add(4);
        inventoryCount.add(1);
        return inventoryCount;
    }

    @Override
    public Player clone() {
        return (Player) super.clone();
    }
}
