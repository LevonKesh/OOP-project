package Entity;

import ItemsAndSpells.Item;

import java.util.ArrayList;

public class Enemy extends Entity {
    private int XP;
    public enum AItype {MELEE, RANGED, MAGE};
    private AItype enemyType;

    public Enemy() {
        super();
        enemyType = AItype.MELEE;
        XP = 100;
        setChosenWeapon();
    }

    public Enemy(String name, int armorClass, int hitPoints, int speed,
                 int strength, int dexterity, int constitution, int intelligence, int wisdom, int charisma,
                 ArrayList<Item> inventory, ArrayList<Integer> inventoryCount,
                 int XP, AItype enemyType) {
        super(name, armorClass, hitPoints, speed, strength, dexterity, constitution, intelligence, wisdom, charisma, inventory, inventoryCount);
        this.XP = XP;
        this.enemyType = enemyType;
        setChosenWeapon();
    }

    public AItype getEnemyAIType() {
        return this.enemyType;
    }

    public void setEnemyAIType(AItype entityType) {
        this.enemyType = entityType;
    }

    public int getXP() {
        return XP;
    }

    public void setXP(int XP) {
        this.XP = XP;
    }

    public void setChosenWeapon() {
        if (enemyType == AItype.RANGED) {
            setChosenWeapon(getRandomWeapon(getRangedWeapons()));
        }
        else {
            setChosenWeapon(getRandomWeapon(getMeleeWeapons()));
        }
    }

//    public ArrayList<Item> lootInventory() // TODO: needs to be written in engine or somewhere else; if not can be written here

    @Override
    public Enemy clone() {
        return (Enemy) super.clone();
    }
}
