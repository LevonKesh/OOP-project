package Entity;

public class TradeImpossibleException extends Exception {
    public TradeImpossibleException() {
        super("Happened some error during trade");
    }

    public TradeImpossibleException(String message) {
        super(message);
    }
}
