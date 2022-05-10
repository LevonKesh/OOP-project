package Battle;

import ItemsAndSpells.*;

public class Action {
    private Position origin;
    private Position destination;
    private Weapon weapon;
    private Spell spell;

    public Action(Position origin, Position destination, Weapon weapon) {
        this.origin = new Position(origin);
        this.destination = new Position(destination);
        this.weapon = weapon;
    }

    public Action(Position origin, Position destination, Spell spell) {
        this.origin = new Position(origin);
        this.destination = new Position(destination);
        this.spell = spell;
    }

    public Action(Action that) {
        this.origin = new Position(that.getOrigin());
        this.destination = new Position(that.getDestination());
        this.weapon = that.weapon;
        this.spell = that.spell;
    }

    public Position getOrigin() {
        return new Position(origin);
    }

    public Position getDestination() {
        return new Position(destination);
    }

    public Weapon getWeapon() {
        if (weapon != null) return weapon.clone();
        else return null;
    }

    public Spell getSpell() {
        if (spell != null) return new Spell(spell);
        else return null;
    }
}
