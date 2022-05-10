package Battle;

import java.util.ArrayList;
import java.util.Arrays;

import static Battle.BattleGrid.GRID_DIMENSION;

public class Position {
    private int row;
    private int col;

    public Position() {
        row = 0;
        col = 0;
    }

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Position(Position other) {
        this.row = other.row;
        this.col = other.col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        if (isValid(row)) {
            this.row = row;
        }
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        if (isValid(col)) {
            this.col = col;
        }
    }

    public void setRowAndCol(int row, int col) {
        if (isValid(row) && isValid(col)) {
            this.row = row;
            this.col = col;
        }
    }

    private static boolean isValid(int number) {
        return number >= 0 && number < GRID_DIMENSION;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position that = (Position) o;
        return row == that.row && col == that.col;
    }

    public static ArrayList<Position> appendPositionsToArray(ArrayList<Position> arr, Position... positions) {
        arr.addAll(Arrays.asList(positions));
        return arr;
    }
}
