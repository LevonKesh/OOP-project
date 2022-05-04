package Battle;

import Entity.Entity;

import java.util.ArrayList;

public class Battle {
    final int boardDimension = 10;
    private final Board battleBoard = new Board();

    public Battle(Entity[] participants){
        battleBoard.putParticipants(participants);
    }

    public Board getBoard() {
        return battleBoard;
    }

    public ArrayList<Entity> getParticipants() {
        return battleBoard.getParticipants();
    }

    public void showMoves(Entity entity){
        //TODO Print moves with javafx
    }
}
