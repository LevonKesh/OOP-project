package Entity;

import java.util.ArrayList;

import AI.AI_Constants;
import Actions.Spell;
import Items.*;

public class Entity implements AI_Constants {
    private String name;

    private AItype entityType;

    private int armorClass;
    private int hitPoints;
    private int speed;

    private Skill skills;

    private double challenge;
    private int XP;

    private Spell[] spells;
    private ArrayList<Item> inventory;
    private ArrayList<Integer> inventoryCount;

    public Entity() {
        name = null;
        armorClass = 0;
        hitPoints = 0;
        speed = 0;
        skills = null;
        challenge = 0;
        XP = 0;
        actions = null;
        inventory = null;
    }

    public Entity(String name, int armorClass, int hitPoints, int speed, Skill skills,
                  double challenge, int XP, Action[] actions, ArrayList<Item> inventory) {
        this.name = name;
        this.size = name;
        this.armorClass = armorClass;
        this.hitPoints = hitPoints;
        this.speed = speed;
        this.skills = skills;
        this.challenge = challenge;
        this.XP = XP;
        this.actions = actions;
        this.inventory = inventory;
    }

    public AItype getEntityType() {
        return this.entityType;
    }

    public ArrayList<Item> getInventory() {
        ArrayList<Item> newInventory = new ArrayList<Item>();
        for (int i = 0; i < this.inventory.size(); i++) {
            newInventory.set(i, this.inventory.get(i).clone());
        }
        return newInventory;
    }

    public ArrayList<Integer> getInventoryCount() {
//        ArrayList<Integer> newInventory = new ArrayList<Integer>;
//        for (int i = 0; i < this.inventoryCount.size(); i++) {
//            newInventory.set(i, this.inventoryCount.get(i));
//        }
        return (ArrayList<Integer>) this.inventoryCount.clone();
    }


    public void addToInventory(Item newItem) {
        if (this.inventory.contains(newItem)) {
            int index = this.inventory.indexOf(newItem);
            this.inventoryCount.set(index, this.inventoryCount.get(index) + 1);
        } else {
            this.inventory.add(newItem);
            this.inventoryCount.add(1);
        }
    }

    public void takeFromInventory(Item newItem) {
        if (this.inventory.contains(newItem)) {
            int index = this.inventory.indexOf(newItem);
            this.inventoryCount.set(index, this.inventoryCount.get(index) - 1);
            if (this.inventoryCount.get(index) == 0) {
                this.inventory.remove(index);
                this.inventoryCount.remove(index);
            }
        } else {
            System.out.println("No such item in inventory."); // Fixme: May be needs to be written as an exception needs a double check
        }
    }

    public ArrayList<Weapon> getWeapons() {
        ArrayList<Weapon> newWeapons = new ArrayList<Weapon>();
        for (Item i : this.inventory) {
            if (i.getClass() == Weapon.class) {
                newWeapons.add((Weapon) i);
            }
        }
        return newWeapons;
    }
    // todo: needs get melee weapons and get ranged weapons methods

    public Weapon getRandomWeapon() {
        ArrayList<Weapon> weapons = getWeapons();
        int index = (int) (Math.random() * weapons.size());
        return weapons.get(index);
    }

    public Spell[] getSpells() {
        Spell[] newSpells = new Spell[spells.length];
        for (int i = 0; i < spells.length; i++) {
            newSpells[i] = new Spell(spells[i]);
        }
        return newSpells;
    }

    public Spell getRandomSpell() {
        int index = (int) (Math.random() * spells.length);
        return spells[index];
    }


    public boolean checkForPotion() {
        if (this.inventory.contains(new Item("Potion", "Potion description"))) { // ToDo: needs concrete name and description for potion
            return true;
        } else return false;
    }

    public void usePotion() {
        if (checkForPotion()) {
            takeFromInventory(new Item("Potion", "Potion description")); // ToDo: needs concrete name and description for potion
            this.hitPoints += 50;
        }
    }


}
