package Entity;

import java.util.ArrayList;

import ItemsAndSpells.*;

public class Player extends Entity {
    private int availableSkillPoints = 0;

    public Player() {
        super();
    }

    public Player(String name, int armorClass, int hitPoints, int speed, int strength, int dexterity, int constitution, int intelligence, int wisdom, int charisma, ArrayList<Item> inventory, ArrayList<Integer> inventoryCount) {
        super(name, armorClass, hitPoints, speed, strength, dexterity, constitution, intelligence, wisdom, charisma, inventory, inventoryCount);
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
        if (this.getInventory().contains(new Item("Potion", "Potion description"))) { // ToDo: needs concrete name and description for potion
            return true;
        } else return false;
    }

    public void usePotion() {
        if (checkForPotion()) {
            takeFromInventory(new Item("Potion", "Potion description")); // ToDo: needs concrete name and description for potion
            this.setHitPoints(this.getHitPoints() + 50);
        }
    }

    @Override
    public Player clone() {
        return (Player) super.clone();
    }
}
