package AI;

import Entity.Entity;
import Items.Weapon;

public abstract class AI implements AI_Constants {
    // BASIC, MAGE, DRAGON todo: maybe dragon needs to be under basic
//    private String AItype;


    public Strategy evaluateSituation(BattleGrid grid, Position positionOfMonster, Position positionOfPlayer) {
        Entity enemy = grid.getEntity(positionOfMonster)
        Entity.AItype entityType = enemy.getEntityType();
        if (entityType.equals(AItype.MELEE)) {
            if (!proximity check){ //todo: needs a method in Battle to check proximity here
                return Strategy.GET_CLOSER;
            }
            else{
                return Strategy.ATTACK_MELEE;
            }
        }
        else if

//    Weapon weapon = enemy.getRandomWeapon();
    }
