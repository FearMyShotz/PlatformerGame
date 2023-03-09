package adventure.model.level;

import java.util.Set;

import org.jetbrains.annotations.Nullable;

import adventure.model.GameAsset;
import adventure.model.entity.Entity;
import adventure.model.entity.implementation.player.Player;
import adventure.model.map.Map;
import adventure.tick.Rendering;
import adventure.tick.Ticking;

import java.awt.Graphics;

public class Level extends GameAsset implements Ticking, Rendering {

    private Player player;

    private Map map;
    
    private Set<Entity> entities;

    public Level(int id, String name, @Nullable String namespace, @Nullable String key, Map map, Set<Entity> entities) {
        super(id, name, namespace, key);

        this.player = entities.stream().filter(e -> e instanceof Player).map(e -> (Player) e).findFirst().orElse(null);
        this.map = map;
        this.entities = entities;
    }

    @Override
    public void tick() {
        entities.forEach(Entity::tick);
    }

    @Override
    public void render(Graphics graphics) {
        map.render(graphics);
        entities.forEach(e -> e.render(graphics));
    }

    public Player getPlayer() {
        return player;
    }

    public Map getMap() {
        return map;
    }

    public Set<Entity> getEntities() {
        return entities;
    }
}