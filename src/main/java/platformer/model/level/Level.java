package platformer.model.level;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashSet;

import platformer.model.GameAsset;
import platformer.model.entity.Entity;
import platformer.model.entity.implementation.player.Player;
import platformer.model.map.Map;
import platformer.tick.Rendering;
import platformer.tick.Ticking;
import platformer.util.Location;

public class Level extends GameAsset implements Ticking, Rendering {

    private final Player player;

    private final float spawnX;
    private final float spawnY;

    private final Map map;
    
    private final ArrayList<Entity> entities;

    public Level(int id, String name, String namespace, String key, Map map, HashSet<Entity> entities) {
        super(id, name, namespace, key);

        this.player = entities.stream().filter(e -> e instanceof Player).map(e -> (Player) e).findFirst().orElse(null);
        this.spawnX = 192;
        this.spawnY = 896;
        this.map = map;
        this.entities = new ArrayList<Entity>(entities);
    }

    @Override
    public void tick() {
        entities.forEach(Entity::tick);
    }

    @Override
    public void render(Graphics g) {
        map.render(g);
        entities.forEach(e -> e.render(g));
    }

    public Player getPlayer() {
        return player;
    }

    public Location getSpawnLocation() {
        return new Location(spawnX, spawnY);
    }

    public Map getMap() {
        return map;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }
}