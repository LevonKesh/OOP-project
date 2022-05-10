package Battle;

public abstract class Dice {
    public static int d20() {
        return (int)(Math.random() * 20) + 1;
    }
    public static int d6() {
        return (int)(Math.random() * 6) + 1;
    }
}
