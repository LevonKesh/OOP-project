package ItemsAndSpells;

import java.util.Objects;

public class Item implements Cloneable {
    private String name;
    private String description;
    private int value;
    private static boolean isSellable = true;

    public Item(String name, String description, int value) {
        this.name = name;
        this.description = description;
        this.value = value;
    }


    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static boolean isSellable() {
        return isSellable;
    }

    public static void setIsSellable(boolean isSellable) {
        Item.isSellable = isSellable;
    }

    public Item clone(){
        try {
            return (Item) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(name, item.name) && Objects.equals(description, item.description);
    }

}
