package Battle;

public class PlayerDiedException extends Exception{
    public PlayerDiedException() {
        super("You died.");
    }

    public PlayerDiedException(String message) {
        super(message);
    }
}
