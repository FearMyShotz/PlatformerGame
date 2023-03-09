package adventure.model.entity.implementation.npc;

import adventure.model.entity.Entity;
import adventure.util.Location;

public abstract class Enemy extends Entity {

    protected int id;

    // private final EntityState state;
    private Location goal;

    public Enemy(int id, String name, String key) {
        super(id, name, key);


    }

    public Location getGoal() {
        return goal;
    }

    public void walkToGoal() {

    }
}