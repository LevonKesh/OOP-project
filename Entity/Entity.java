package Entity;

import java.util.ArrayList;
import Items.*;

public class Entity {
    private String name;
    private String size;
    private int armorClass;
    private int hitPoints;
    private int speed;

    private Skill skills;

    private double challenge;
    private int XP;

    private Action[] actions;
    private ArrayList<Item> inventory;
    private ArrayList<Integer> inventoryCount;

    public Entity() {
        name = null;
        size = null;
        armorClass = 0;
        hitPoints = 0;
        speed = 0;
        skills = null;
        challenge = 0;
        XP = 0;
        actions = null;
        inventory = null;
    }

    public Entity(String name, String size, int armorClass, int hitPoints, int speed, Skill skills,
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

    public ArrayList<Item> getInventory() {
        ArrayList<Item> newInventory = new ArrayList<Item>;
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
        }
        else {
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
        }
        else {
            System.out.println("No such item in inventory."); // Fixme: May be written as an exception needs a double check
        }
    }

    public Action[] getActions() {
        Action[] newActions = new Action[this.actions.length];
        for (int i = 0; i < this.actions.length; i++) {
            newActions[i] = new Action(this.actions[i]);
        }
        return newActions;
    }

    public Action getRandomAction() {
        int index = (int) (Math.random() * this.actions.length);
        return new Action(this.actions[index]);
    }

    public boolean checkForPotion() {
        if(this.inventory.contains(new Item("Potion", "Potion description"))) { // ToDo: needs concrete name and description for potion
            return true;
        }
        else return false;
    }

    public void usePotion() {
        if (checkForPotion()) {
            takeFromInventory(new Item("Potion", "Potion description")); // ToDo: needs concrete name and description for potion
            this.hitPoints += 50;
        }
    }


}
