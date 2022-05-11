package Entity;

import ItemsAndSpells.Item;
import ItemsAndSpells.Spell;

import java.util.ArrayList;

public class Mage extends Enemy {

    private ArrayList<Spell> spells;

    public Mage() {
        super();
        setEnemyAIType(AItype.MAGE);
        setXP(500);
        this.spells = null;
    }

    public Mage(String name, int armorClass, int hitPoints, int speed,
                int strength, int dexterity, int constitution, int intelligence, int wisdom, int charisma,
                ArrayList<Item> inventory, ArrayList<Integer> inventoryCount,
                int XP, AItype enemyType, ArrayList<Spell> spells) {
        super(name, armorClass, hitPoints, speed, strength, dexterity, constitution, intelligence, wisdom, charisma,
                inventory, inventoryCount, XP, enemyType);
        setEnemyAIType(AItype.MAGE);
        this.spells = spells;
    }


    public ArrayList<Spell> getSpells() {
        ArrayList<Spell> newSpells = new ArrayList<>();
        for (int i = 0; i < spells.size(); i++) {
            newSpells.add(i, spells.get(i).clone());
        }
        return newSpells;
    }

    public Spell getRandomSpell() {
        int index = (int) (Math.random() * spells.size());
        return spells.get(index);
    }

    @Override
    public Mage clone() {
        Mage clone = (Mage) super.clone();
        clone.spells = getSpells();
        return clone;
    }
}
