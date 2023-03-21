package platformer.model.entity.implementation.npc;

import platformer.model.entity.Entity;
import platformer.util.Location;

/**
 * Eine spezialisierte {@link Entity}, die als Gegner fungiert.
 * 
 * Derzeit nicht implementiert. (Stand: 21.03.2023)
 * 
 * @author Jamil B.
 */
public abstract class Enemy extends Entity {

    /**
     * Die ID des Gegners.
     */
    protected int id;

    /**
     * Das Ziel des Gegners.
     */
    private Location goal;

    /**
     * Erstellt einen neuen Gegner.
     * 
     * @param id Die ID des Gegners.
     * @param name Der Name des Gegners.
     * @param key Der Schlüssel des Gegners.
     */
    public Enemy(int id, String name, String key) {
        super(id, name, key);
    }

    /**
     * Gibt das Ziel des Gegners zurück.
     * 
     * @return Das Ziel des Gegners.
     */
    public Location getGoal() {
        return goal;
    }

    /**
     * Setzt das Ziel des Gegners und bewegt ihn dorthin.
     * 
     * @param goal Das neue Ziel des Gegners.
     */
    public void walkToGoal() {

    }
}