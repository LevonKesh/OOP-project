package Battle;

import Entity.Entity;

import java.util.ArrayList;

public class Battle {
    private static boolean initiated = true;
    private final Board board = new Board();

    public Battle(Entity[] participants){
        board.putParticipantsRandomly(participants);
    }

    public static boolean isInitiated() {
        return initiated;
    }

    public static void setInitiated(boolean initiated) {
        Battle.initiated = initiated;
    }

    public Board getBoard() {
        return board;
    }

    public ArrayList<Entity> getParticipants() {
        return board.getParticipants();
    }

    public void showMoves(Entity entity){
        //TODO Print moves with javafx
    }
}
