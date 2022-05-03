public class Money extends Item{
    private int count = 100;
    private boolean isSellable = false;

    public Money(String name, String description, int count){
        super(name, description);
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count){
        this.count = count;
    }

    public void changeCount(int c){
        setCount(this.count + c);
    }
}
