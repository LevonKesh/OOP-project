package Battle;

public class PlayerDiedException extends Exception{
    public PlayerDiedException() {
        super("Player died.");
    }

    public PlayerDiedException(String message) {
        super(message);
    }
}
