package ItemsAndSpells;

public class Weapon extends Item implements Comparable<Weapon>{
    private int damage;
    private boolean isRanged = false;
    private static boolean isSellable = true;

    public Weapon(String name, String description, int damage, boolean isRanged){
        super(name, description);
        this.damage = damage;
        this.isRanged = isRanged;
    }

    public int getDamage() {
        return this.damage;
    }

    public boolean getIsRanged(){
        return this.isRanged;
    }

    public void setDamage(int damage){
        this.damage = damage;
    }

    public void setIsRanged(boolean isRanged){
        this.isRanged = isRanged;
    }

    public int compareTo(Weapon other) {
        return this.damage - other.getDamage();
    }

    public Weapon clone() {
        return (Weapon) super.clone();
    }
}
