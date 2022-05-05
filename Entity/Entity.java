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
    private Item[] inventory;
    private int[] inventoryCount;

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

                  double challenge, int XP, Action[] actions, Item[] inventory) {
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

    public Item[] getInventory() {
        Item[] newInventory = new Item[this.inventory.length];
        for (int i = 0; i < this.inventory.length; i++) {
                newInventory[i] = this.inventory[i].clone();
        }
    }

    public void addToInventory(Item newItem) {
        if (inventory.contains(newItem)) {
            int index = inventory.indexOf(newItem);

        }
    }
}
