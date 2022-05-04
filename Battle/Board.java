package Battle;

import Entity.Entity;

import java.util.ArrayList;
import java.util.Random;

public class Board {
    private static final int dimension = 5;
    private Cell[][] board;

    public Board() {
        board = new Cell[10][10];
    }

    public void putParticipants(Entity[] participants) {
        Random generator = new Random();
        for (Entity entity : participants) {
            int x;
            int y;
            do {
                x = generator.nextInt(0, 9);
                y = generator.nextInt(0, 9);
            } while (!board[x][y].isOccupied());
            board[x][y].setEntity(entity);
        }
    }

    public ArrayList<Entity> getParticipants() {
        ArrayList<Entity> entities = new ArrayList<>();
        for (Cell[] row : board)
            for (Cell cell : row)
                if (cell.getEntity() != null) entities.add(cell.getEntity());
        return entities;
    }

    public void show() {
        //TODO Display board in a javafx window
    }
}
