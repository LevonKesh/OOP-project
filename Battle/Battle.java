package Battle;

import Entity.Entity;

import java.util.ArrayList;

public class Battle {
    final int boardDimension = 10;
    private ArrayList<Cell> board;

    public Battle(ArrayList<Cell> board){
        this.board = board;
    }

    public ArrayList<Cell> getBoard() {
        return board;
    }

    public ArrayList<Entity> getEntities(){
        ArrayList<Entity> entities = new ArrayList<>();
        for (Cell cell: this.board)
            if (cell.getEntity() != null) entities.add(cell.getEntity());
        return entities;
    }

}
