import Engine.BattleWindow;
import Engine.GameWindow;
import Engine.StartingWindow;

import Entity.*;
import Parsers.EnemyParser;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Entity> entities = new ArrayList<Entity>(EnemyParser.getSelectedEnemies("Goblin"));
        Player player = new Player("sdf");
        entities.add(player);
        new BattleWindow(entities);
    }
}
