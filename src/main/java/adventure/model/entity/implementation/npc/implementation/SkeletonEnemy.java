package adventure.model.entity.implementation.npc.implementation;

import java.awt.Graphics;

import adventure.model.entity.implementation.npc.Enemy;

public class SkeletonEnemy extends Enemy {

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