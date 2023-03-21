package platformer.model.entity.implementation.npc.implementation;

import java.awt.Graphics;

import platformer.model.entity.implementation.npc.Enemy;

/**
 * Ein Gegner, der einen Skelett darstellt.
 * 
 * Wird derzeit nicht verwendet. (Stand: 21.03.2023)
 * 
 * @author Jamil B.
 * @see Enemy
 */
public class SkeletonEnemy extends Enemy {

    /**
     * Erstellt einen neuen Gegner, der einen Skelett darstellt.
     * 
     * @param id Die ID des Gegners.
     * @param name Der Name des Gegners.
     * @param key Der Schlüssel des Gegners.
     */
    public SkeletonEnemy(int id, String name, String key) {
        super(id, name, key);
    }

    @Override
    public void render(Graphics g) {
        throw new UnsupportedOperationException("Unimplemented method 'render'");
    }

    @Override
    public void tick() {
        throw new UnsupportedOperationException("Unimplemented method 'tick'");
    }
}