package Battle;

import Entity.Entity;

import java.util.ArrayList;
import java.util.Random;

public class Board {
    private static final int dimension = 10;
    private final Cell[][] board = new Cell[dimension][dimension];

    public Board() {
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board.length; column++) {
                board[row][column] = new Cell();
            }
        }
    }

    public Cell getCell(int row, int column){
        return board[row][column];
    }
    public void putParticipantsRandomly(Entity[] participants) {
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

    public static int getDimension() {
        return dimension;
    }

    public void show() {
        //TODO Display board in a javafx window
    }
}
