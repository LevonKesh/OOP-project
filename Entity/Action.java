package Entity;

public class Action {
    private String name;

    public Action() {
        name = null;
    }

    public Action(Action that) {
        this.name = that.name;
    }
}
