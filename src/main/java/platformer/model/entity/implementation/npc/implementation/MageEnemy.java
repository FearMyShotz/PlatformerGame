package platformer.model.entity.implementation.npc.implementation;

import java.awt.Graphics;

import platformer.model.entity.implementation.npc.Enemy;

public class MageEnemy extends Enemy {

    public MageEnemy(int id, String name, String key) {
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