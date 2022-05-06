package Actions;

public class Spell extends Action{
    public enum SpellType {ATTACK,DEFENCE};
    private SpellType spellType;

    // todo: needs constuctors ASAP
    public SpellType getSpellType() {
        return spellType;
    }
}
