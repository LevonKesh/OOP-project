package Battle;

import Entity.*;
import ItemsAndSpells.Spell;

import java.util.ArrayList;
import java.util.Random;

public class BattleGrid {
    public static final int GRID_DIMENSION = 10;
    private Cell[][] grid = new Cell[GRID_DIMENSION][GRID_DIMENSION];

    public BattleGrid(Entity[] participants) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new Cell();
            }
        }

        putParticipants(participants);
    }

    public void putParticipants(Entity[] participants) {
        Random generator = new Random();
        for (Entity entity : participants) {
            int x;
            int y;
            do {
                x = generator.nextInt(10);
                y = generator.nextInt(10);
            } while (grid[x][y].isOccupied());
            grid[x][y].setEntity(entity);
        }
    }

    public Cell getCellAt(Position p) {
        if (p != null) {
            return grid[p.getRow()][p.getCol()];
        }
        return null;
    }

    public Cell[][] getBoard() {
        Cell[][] newgrid = new Cell[GRID_DIMENSION][GRID_DIMENSION];
        for (int i = 0; i < GRID_DIMENSION; i++) {
            for (int j = 0; j < GRID_DIMENSION; j++) {
                newgrid[i][j] = new Cell(grid[i][j]);
            }
        }
        return newgrid;
    }

    public ArrayList<Entity> getParticipants() {
        ArrayList<Entity> entities = new ArrayList<>();
        for (Cell[] row : grid) {
            for (Cell cell : row) {
                if (cell.getEntity() != null) {
                    entities.add(cell.getEntity());
                }
            }
        }
        return entities;
    }

    public ArrayList<Enemy> getEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();
        for (Cell[] row : grid) {
            for (Cell cell : row) {
                if (cell.getEntity() != null && cell.getEntity() instanceof Enemy) {
                    enemies.add((Enemy) cell.getEntity());
                }
            }
        }
        return enemies;
    }

    public ArrayList<Position> getEnemyPositions() {
        ArrayList<Position> positions = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j].getEntity() != null && grid[i][j].getEntity() instanceof Enemy) {
                    positions.add(new Position(i, j));
                }
            }
        }
        return positions;
    }

    public Player getPlayer() {
        for (Cell[] row : grid) {
            for (Cell cell : row) {
                if (cell.getEntity() != null && cell.getEntity() instanceof Player) {
                    return (Player) cell.getEntity();
                }
            }
        }
        return null; // Shall not happen, putting here to keep compiler happy
    }

    public Position getPlayerPosition() {
        for (int i = 0; i < GRID_DIMENSION; i++) {
            for (int j = 0; j < GRID_DIMENSION; j++) {
                if (grid[i][j] != null && grid[i][j].getEntity() != null && grid[i][j].getEntity() instanceof Player) {
                    return new Position(i, j);
                }
            }
        }
        return null; // Shall not happen, putting here to keep compiler happy
    }

    public boolean isPlayer(Position position) {
        return position != null && this.grid[position.getRow()][position.getCol()].getEntity() instanceof Player;
    }

    public boolean isEmpty(Position position) {
        return position != null && !this.grid[position.getRow()][position.getCol()].isOccupied();
    }

    public ArrayList<Position> availablePositions(Position origin) {
        if (origin == null || isEmpty(origin)) {
            return null;
        } else {
            ArrayList<Position> availablePositions = new ArrayList<>();
            int possibleDistance = grid[origin.getRow()][origin.getCol()].getEntity().getSpeed();
            for (int i = 0; i < GRID_DIMENSION; i++) {
                for (int j = 0; j < GRID_DIMENSION; j++) {
                    Position possiblePosition = new Position(i, j);
                    if (distanceCalculator(origin, possiblePosition) <= possibleDistance &&
                            !origin.equals(possiblePosition) && !grid[i][j].isOccupied()) {
                        availablePositions.add(possiblePosition);
                    }
                }
            }
            return availablePositions;
        }
    }

    public boolean performMove(Move move) {
        if (move != null) {
            Position o = move.getOrigin();
            Position d = move.getDestination();

            if (!isEmpty(o) && isEmpty(d)) {
                if (availablePositions(o).contains(d)) {
                    grid[d.getRow()][d.getCol()] = getCellAt(o);
                    grid[o.getRow()][o.getCol()] = null;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean playerAttack(Action action) { // TODO: needs to be written in engine; may be omitted
        try {
            return performAction(action);
        }
        catch (PlayerDiedException e) {
            return false; //not possible
        }
    }

    public boolean performAction(Action action) throws PlayerDiedException{
        if (action != null) {
            Position o = action.getOrigin();
            Position d = action.getDestination();
            if (!isEmpty(o) && !isEmpty(d)) {
                Entity attacker = getCellAt(o).getEntity();
                Entity defender = getCellAt(d).getEntity();
                if (action.getWeapon() != null) {
                    int damage = attacker.getChosenWeapon().getDamage() + attacker.getStrength() + Dice.d6() -
                            defender.getArmorClass() - defender.getConstitution();
                    if (damage < 0) damage = 5;
                    defender.setHitPoints(defender.getHitPoints() - damage);
                    if (defender.getHitPoints() <= 0) {
                        if (defender instanceof Player) {
                            gameOver();
                        } else {
                            grid[d.getRow()][d.getCol()] = null;
                        }
                    }
                    return true;
                } else {
                    Mage mage = (Mage) attacker;
                    Spell spell = mage.getRandomSpell();
                    if (spell.getSpellType() == Spell.SpellType.ATTACK && (defender instanceof Player)) {
                        int damage = spell.getValue() + attacker.getIntelligence() + Dice.d6() -
                                defender.getArmorClass() - defender.getConstitution();
                        if (damage < 5) damage = 5;
                        defender.setHitPoints(defender.getHitPoints() - damage);
                        if (defender.getHitPoints() <= 0) {
                            gameOver();
                        }
                        return true;
                    } else if (spell.getSpellType() == Spell.SpellType.DEFENCE && !(defender instanceof Player)) {
                        defender.setHitPoints(defender.getHitPoints() + spell.getValue() + Dice.d6() + attacker.getIntelligence());
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void evaluateSituation(Position positionOfEnemy) throws PlayerDiedException {
        Position positionOfPlayer = getPlayerPosition();
        Enemy enemy = (Enemy) getCellAt(positionOfEnemy).getEntity();
        Enemy.AItype entityType = enemy.getEnemyAIType();
        if (entityType.equals(Enemy.AItype.MELEE)) {
            if (isNotBordering(positionOfEnemy, positionOfPlayer)) {
                comeCloserToPlayer(positionOfEnemy);
            } else {
                performAction(new Action(positionOfEnemy, positionOfPlayer, enemy.getChosenWeapon()));
            }
        } else if (entityType.equals(Enemy.AItype.RANGED)) {
            if (isNotBordering(positionOfEnemy, positionOfPlayer)) {
                performAction(new Action(positionOfEnemy, positionOfPlayer, enemy.getChosenWeapon()));
            } else {
                enemy.setEnemyAIType(Enemy.AItype.MELEE);
                evaluateSituation(positionOfEnemy);
            }
        } else if (entityType.equals(Enemy.AItype.MAGE)) {
            Mage mage = (Mage) enemy;
            if (isNotBordering(positionOfEnemy, positionOfPlayer)) {
                performAction(new Action(positionOfEnemy, positionOfPlayer, mage.getRandomSpell()));
            } else {
                enemy.setEnemyAIType(Enemy.AItype.MELEE);
                evaluateSituation(positionOfEnemy);
            }
        }
    }

    public boolean comeCloserToPlayer(Position o) {
        ArrayList<Position> availablePositions = availablePositions(o);
        Position playerPosition = getPlayerPosition();
        Position bestPosition = o;
        for (Position i : availablePositions) {
            if (distanceCalculator(playerPosition, i) < distanceCalculator(playerPosition, bestPosition)) {
                bestPosition = i;
            }
        }

        return performMove(new Move(o, bestPosition));
    }

    public boolean isNotBordering(Position o, Position d) {
        if (o != null && d != null) {
            if ((Math.abs(o.getRow() - d.getRow()) == 1) && (Math.abs(o.getCol() - d.getCol()) == 1)) {
                return false;
            }
        }
        return true;
    }

    public void gameOver() throws PlayerDiedException {
        throw new PlayerDiedException();
    }

    private double distanceCalculator(Position o, Position d) {
        return Math.sqrt(Math.pow(o.getRow() - d.getRow(), 2) + Math.pow(o.getCol() - d.getCol(), 2));
    }
}
