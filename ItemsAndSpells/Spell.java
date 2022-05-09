package ItemsAndSpells;

import Actions.Action;

public class Spell {
    public enum SpellType {ATTACK, DEFENCE}

    private String name;
    private String description;
    private SpellType spellType;
    private int value;


    public Spell(String name, String description, SpellType spellType, int value) {
        this.name = name;
        this.description = description;
        this.spellType = spellType;
        this.value = value;
    }

    public Spell(Spell other) {
        this.name = other.name;
        this.description = other.description;
        this.value = other.value;
        this.spellType = other.spellType;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getValue() {
        return value;
    }

    public SpellType getSpellType() {
        return spellType;
    }
}
