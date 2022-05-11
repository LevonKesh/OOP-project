package Engine;

import Battle.*;
import Battle.Action;
import Entity.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BattleWindow extends JFrame {
    private BattleGrid battleGrid;
    private GridSquare[][] gridSquares = new GridSquare[10][10];
    private Position origin;
    private JPanel battlePanel = new JPanel();

    private class EndingListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            GridSquare gridSquare = (GridSquare) e.getSource();
            int[] coordinates = gridSquare.getCoordinates();
            gridClicked(coordinates);
        }
    }

    public BattleWindow(ArrayList<Entity> entities) {
        super("Battle");
        this.battleGrid = new BattleGrid(entities.toArray(new Entity[0]));
        this.setMinimumSize(new Dimension(800, 820));
        setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());

        battlePanel.setLayout(new GridLayout(10, 10));
        battlePanel.setPreferredSize(new Dimension(750, 750));
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                gridSquares[i][j] = new GridSquare(i, j);
                gridSquares[i][j].setHighlight(false);
                gridSquares[i][j].addActionListener(new EndingListener());
                battlePanel.add(gridSquares[i][j]);
            }
        }
        updateEntities();
        this.add(new JLabel());
        this.add(battlePanel);
        this.add(new JLabel());
        this.setVisible(true);
    }

    private void AImoves() {
        ArrayList<Position> enemyPositions = battleGrid.getEnemyPositions();
        for (Position position : enemyPositions) {
            try {
                battleGrid.evaluateSituation(position);
                this.setVisible(false);
                this.setVisible(true);
            } catch (PlayerDiedException e) {
                this.setVisible(false);
                new DeathWindow();
            }
        }
    }

    private void gridClicked(int[] coordinates) {
        if (this.origin == null) {
            this.origin = new Position(coordinates[0], coordinates[1]);
            if (battleGrid.isPlayer(this.origin) && battleGrid.availablePositions(this.origin) != null &&
                    battleGrid.availablePositions(this.origin).size() != 0) {
                Position[] availablePositions = battleGrid.availablePositions(this.origin).toArray(new Position[0]);
                for (Position i : availablePositions) {
                    gridSquares[i.getRow()][i.getCol()].setHighlight(true);

                }
            }
            else {
                this.origin = null;
            }
        } else {
            Position[] availablePositions = battleGrid.availablePositions(this.origin).toArray(new Position[0]);
            for (Position i : availablePositions) {
                gridSquares[i.getRow()][i.getCol()].setHighlight(false);
            }
            Position destination = new Position(coordinates[0], coordinates[1]);
            try {
                if (battleGrid.getCellAt(destination).isOccupied()) {
                    battleGrid.performAction(new Action(origin, destination, battleGrid.getPlayer().getChosenWeapon()));
                    this.setVisible(false);
                    this.setVisible(true);
                    AImoves();
                    updateEntities();

                } else if (!battleGrid.getCellAt(destination).isOccupied()) {
                    battleGrid.performMove(new Move(this.origin, destination));
                    AImoves();
                    updateEntities();
                }
            } catch (PlayerDiedException e) {

            }
            this.origin = null;
        }
    }

    private void updateEntities() {
        for (int i = 0; i < gridSquares.length; i++) {
            for (int j = 0; j < gridSquares[0].length; j++) {
                Cell cell = battleGrid.getCellAt(new Position(i, j));
                if (cell.getEntity() != null) {
                    gridSquares[i][j].setEntity(cell.getEntity().getName());
                    gridSquares[i][j].setHighlight(false);
                } else {
                    gridSquares[i][j].setEntity();
                    gridSquares[i][j].setHighlight(false);
                }
            }
        }
    }

    public class GridSquare extends JButton {

        private int x;
        private int y;

        public GridSquare(int x, int y) {
            super();
            this.x = x;
            this.y = y;
        }

        public int[] getCoordinates() {
            int[] coordinates = new int[2];
            coordinates[0] = this.x;
            coordinates[1] = this.y;
            return coordinates;
        }

        public void setEntity(String p) {
            switch (p) {
                case "Goblin":
                    this.setIcon(new ImageIcon("GFX\\RookW.png"));
                    break;
                default:
                    this.setIcon(new ImageIcon("GFX\\KingW.png"));
            }
        }

        public void setEntity() {
            this.setIcon(null);
        }

        public void setHighlight(boolean isHighlighted) {
            if (isHighlighted) {
                this.setBackground(Color.RED);
            } else {
                this.setBackground(Color.LIGHT_GRAY);
            }
        }
    }
}



