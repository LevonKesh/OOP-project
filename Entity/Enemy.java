package Entity;

import ItemsAndSpells.Item;

import java.util.ArrayList;

public class Enemy extends Entity {
    private int XP;
    private AItype enemyType;

    public Enemy() {
        super();
        enemyType = AItype.MELEE;
        XP = 100;
    }

    public Enemy(String name, int armorClass, int hitPoints, int speed,
                 int strength, int dexterity, int constitution, int intelligence, int wisdom, int charisma,
                 ArrayList<Item> inventory, ArrayList<Integer> inventoryCount,
                 int XP, AItype enemyType) {
        super(name, armorClass, hitPoints, speed, strength, dexterity, constitution, intelligence, wisdom, charisma, inventory, inventoryCount);
        this.XP = XP;
        this.enemyType = enemyType;
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
}
