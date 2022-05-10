package Battle;

import Entity.Entity;

public class Cell {
    private boolean occupied;
    private Entity entity;
//    private String otherObjectType; // todo: may be created if we have more time; shold represent somthing like trees and objects on the grid

    public Cell(){
        this.entity = null;
        this.occupied = false;
    }

    public Cell(Entity entity){
        this.entity = entity;
        this.occupied = true;
    }

    public Cell(Cell other) {
        this.occupied = other.occupied;
        this.entity = other.entity.clone();
    }


    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
        this.occupied = true;
    }

    public boolean isOccupied() {
        return occupied;
    }
}
