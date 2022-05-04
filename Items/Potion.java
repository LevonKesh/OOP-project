package Items;

import Items.Item;

public class Potion extends Item {
    private int hitPoint;
    private static final boolean isSellable = true;

    public Potion(String name, String description, int hitPoint){
        super(name, description,1);
        this.hitPoint = hitPoint;
    }

    public int getHitPoint(){
        return this.hitPoint;
    }

    public void setHitPoint(int hitPoint){
        this.hitPoint = hitPoint;
    }
}
