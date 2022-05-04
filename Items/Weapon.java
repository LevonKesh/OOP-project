package Items;

import Items.Item;

public class Weapon extends Item {
    private int damage;
    private boolean isRanged = false;
    private static boolean isSellable = true;

    public Weapon(String name, String description, int damage, boolean isRanged){
        super(name, description,1);
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

    public void setISRanged(boolean isRanged){
        this.isRanged = isRanged;
    }
}
