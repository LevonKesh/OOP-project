package Battle;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BoardGUI {
    Pane pane = new Pane();

    private final int paneSize =  520;
    private final int cellSize = paneSize / Board.getDimension();


    public BoardGUI(Board board){
        int boardRow =0;
        int boardCol =0;
        for (int i = 0; i < paneSize; i+= cellSize) {
            for (int j = 0; j < paneSize; j+= cellSize) {
                Rectangle r = new Rectangle(i,j,cellSize,cellSize);
                r.setStroke(Color.WHITE);
                if (board.getCell(boardRow,boardCol).isOccupied()){
                    r.setFill(Color.RED);
                } else {
                    r.setFill(Color.BLACK);
                }
                pane.getChildren().add(r);
                boardCol++;
            }
            boardRow++;
            boardCol = 0;
        }
    }

    public Pane getPane() {
        return pane;
    }
}
