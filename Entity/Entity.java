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
        ArrayList<Item> newInventory = (ArrayList<Item>) inventory.clone();
        return newInventory;
    }

    public void addToInventory(Item newItem) {
        if (inventory.contains(newItem)) {
            int index = inventory.indexOf(newItem);
        }
    }
}
