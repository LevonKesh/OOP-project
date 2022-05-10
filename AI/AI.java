package AI;

import Battle.BattleGrid;
import Battle.Position;

public interface AI extends AI_Constants {
    // BASIC, MAGE, DRAGON todo: maybe dragon needs to be under basic
//    private String AItype;


    public Strategy evaluateSituation(BattleGrid grid, Position positionOfMonster, Position positionOfPlayer); //{
//        Enemy enemy = grid.getEntity(positionOfMonster);
//        Entity.AItype entityType = enemy.getEntityType();
//        if (entityType.equals(AItype.MELEE)) {
//            if (!proximity check){ //todo: needs a method in Battle to check proximity here
//                return Strategy.GET_CLOSER;
//            }
//            else{
//                return Strategy.ATTACK_MELEE;
//            }
//        } else if (entityType.equals(AItype.RANGED)) {
//            if (!proximity check){ //todo: needs a method in Battle to check proximity here
//                return Strategy.ATTACK_RANGED;
//            }
//            else{
//                enemy.setEnemyAIType(AItype.MELEE);
//                evaluateSituation(grid, positionOfMonster, positionOfPlayer);
//            }
//        } else if (entityType.equals(AItype.MAGE)) {
//            if (!proximity check){ //todo: needs a method in Battle to check proximity here
//                return Strategy.USE_SPELL;
//            }
//            else{
//                enemy.setEnemyAIType(AItype.MELEE);
//                evaluateSituation(grid, positionOfMonster, positionOfPlayer);
//            }
//        }

//    Weapon weapon = enemy.getRandomWeapon();
//    }
}
