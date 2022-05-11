package Entity;

import java.util.ArrayList;

import ItemsAndSpells.*;

public class Entity implements Cloneable {
    private String name;

    private int armorClass;
    private int hitPoints;
    private int speed;


    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;

    private ArrayList<Item> inventory;
    private ArrayList<Integer> inventoryCount;

    private Weapon chosenWeapon;

    public Entity() {
        name = null;
        armorClass = 0;
        hitPoints = 0;
        speed = 4;
        charisma = 10;
        dexterity = 10;
        constitution = 10;
        intelligence = 10;
        strength = 10;
        wisdom = 10;
        inventory = null;
        inventoryCount = null;
        if (inventory != null) {
            this.chosenWeapon = getWeaponWithMaxDamage();
        }
    }

    public Entity(String name, int armorClass, int hitPoints, int speed,
                  int strength, int dexterity, int constitution, int intelligence, int wisdom, int charisma,
                  ArrayList<Item> inventory, ArrayList<Integer> inventoryCount) {
        this.name = name;
        this.armorClass = armorClass;
        this.hitPoints = hitPoints;
        this.speed = speed;
        this.charisma = charisma;
        this.dexterity = dexterity;
        this.constitution = constitution;
        this.intelligence = intelligence;
        this.strength = strength;
        this.wisdom = wisdom;
        this.inventory = inventory;
        this.inventoryCount = inventoryCount;
        this.chosenWeapon = getWeaponWithMaxDamage();
    }

    public String getName() {
        return this.name;
    }

    public int getArmorClass() {
        return this.armorClass;
    }

    public int getHitPoints() {
        return this.hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int getSpeed() {
        return this.speed;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }

    public int getCharisma() {
        return charisma;
    }

    public int getConstitution() {
        return constitution;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getStrength() {
        return strength;
    }

    public int getWisdom() {
        return wisdom;
    }

    public void setChosenWeapon(Weapon chosenWeapon) {
        this.chosenWeapon = chosenWeapon.clone();
    }

    public Weapon getChosenWeapon() {
        return chosenWeapon.clone();
    }

    public ArrayList<Item> getInventory() {
        ArrayList<Item> newInventory = new ArrayList<Item>();
        for (int i = 0; i < this.inventory.size(); i++) {
            newInventory.add(i, this.inventory.get(i).clone());
        }
        return newInventory;
    }

    public ArrayList<Integer> getInventoryCount() {
        ArrayList<Integer> newInventoryCount = new ArrayList<>();
        for (int i = 0; i < this.inventoryCount.size(); i++) {
            newInventoryCount.add(i, this.inventoryCount.get(i));
        }
        return newInventoryCount;
    }

    public void addToInventory(Item newItem, int count) {
        if (this.inventory.contains(newItem)) {
            int index = this.inventory.indexOf(newItem);
            this.inventoryCount.set(index, this.inventoryCount.get(index) + count);
        } else {
            this.inventory.add(newItem);
            this.inventoryCount.add(count);
        }
    }

    public void takeFromInventory(Item item, int count) {
        if (this.inventory.contains(item) &&
                this.inventoryCount.get(this.inventory.indexOf(item)) >= count &&
                item.isSellable() && item.isLootable()) {
            int index = this.inventory.indexOf(item);
            this.inventoryCount.set(index, this.inventoryCount.get(index) - count);
            if (this.inventoryCount.get(index) == 0) {
                this.inventory.remove(index);
                this.inventoryCount.remove(index);
            }
        } else {
            System.out.println("No such item in inventory or not enough of the given item"); // Fixme: May be needs to be written as an exception needs a double check
        }
    }

    public ArrayList<Weapon> getWeapons() {
        ArrayList<Weapon> newWeapons = new ArrayList<Weapon>();
        for (Item i : this.inventory) {
            if (i instanceof Weapon) {
                newWeapons.add((Weapon) i);
            }
        }
        newWeapons.sort(null);
        return newWeapons;
    }

    public ArrayList<Weapon> getMeleeWeapons() {
        ArrayList<Weapon> meleeWeapons = new ArrayList<Weapon>();
        for (Weapon i : getWeapons()) {
            if (!(i.getIsRanged())) {
                meleeWeapons.add(i);
            }
        }
        return meleeWeapons;
    }

    public ArrayList<Weapon> getRangedWeapons() {
        ArrayList<Weapon> rangedWeapons = new ArrayList<Weapon>();
        for (Weapon i : getWeapons()) {
            if (i.getIsRanged()) {
                rangedWeapons.add(i);
            }
        }
        return rangedWeapons;
    }

    public Weapon getWeaponWithMaxDamage() {
        ArrayList<Weapon> weapons = getWeapons();
        return weapons.get(0).clone();
    }

    public Weapon getWeaponWithMaxDamage(ArrayList<Weapon> weaponList) {
        return weaponList.get(0).clone();
    }

    public Weapon getRandomWeapon() {
        ArrayList<Weapon> weapons = getWeapons();
        int index = (int) (Math.random() * weapons.size());
        return weapons.get(index).clone();
    }

    public Weapon getRandomWeapon(ArrayList<Weapon> weaponList) {
        int index = (int) (Math.random() * weaponList.size());
        return weaponList.get(index).clone();
    }

    @Override
    public Entity clone() {
        try {
            Entity clone = (Entity) super.clone();
            clone.inventory = getInventory();
            clone.inventoryCount = getInventoryCount();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
