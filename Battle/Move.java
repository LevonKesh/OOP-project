package Battle;

public class Move {
    private Position origin;
    private Position destination;

    public Move(Position origin, Position destination) {
        this.origin = new Position(origin);
        this.destination = new Position(destination);
    }

    public Move(Move that) {
        this.origin = new Position(that.getOrigin());
        this.destination = new Position(that.getDestination());
    }

    public Position getOrigin() {
        return new Position(origin);
    }

    public Position getDestination() {
        return new Position(destination);
    }
}
