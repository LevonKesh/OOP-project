package Battle;

import Entity.Entity;

public class Cell {
    private boolean occupied;
    private Entity entity;

    public Cell(){
        this.entity = null;
        this.occupied = false;
    }

    public Cell(Entity entity){
        this.entity = entity;
        this.occupied = true;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
        this.occupied = true;
    }
}
