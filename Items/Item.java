package Items;

public class Item implements Cloneable {
    private String name;
    private String description;
    private int count = 0;
    private static boolean isSellable = true;

    public Item(String name, String description, int count) {
        this.name = name;
        this.description = description;
        this.count = count;
    }


    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public int getCount() {
        return this.count;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void changeCount(int c) {
        setCount(this.count + c);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
