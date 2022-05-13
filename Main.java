import Engine.*;

import Entity.*;
import Parsers.EnemyParser;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
//        ArrayList<Entity> entities = new ArrayList<Entity>(EnemyParser.getSelectedEnemies("Goblin"));
        Player player = new Player("Valod");
        player.addSkillPoints(5);
//        entities.add(player);
        new InventoryWindow(player);
//        new PlayerStatWindow(player);
//        new GameWindow(player);
    }
}
