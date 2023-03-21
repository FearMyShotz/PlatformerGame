package platformer.model.entity.implementation.npc.implementation;

import java.awt.Graphics;

import platformer.model.entity.implementation.npc.Enemy;

/**
 * Ein Gegner, der einen Bogen benutzt.
 * 
 * Wird derzeit nicht verwendet. (Stand: 21.03.2023)
 * 
 * @author Jamil B.
 * @see Enemy
 */
public class ArcherEnemy extends Enemy {

    /**
     * Erstellt einen neuen Gegner, der einen Bogen benutzt.
     * 
     * @param id Die ID des Gegners.
     * @param name Der Name des Gegners.
     * @param key Der Schlüssel des Gegners.
     */
    public ArcherEnemy(int id, String name, String key) {
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