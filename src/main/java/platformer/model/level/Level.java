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

/**
 * Eine Klasse, die ein Level repräsentiert. Ein Level besteht aus einer Map und dem Spieler.
 * 
 * @author Jamil B.
 */
public class Level extends GameAsset implements Ticking, Rendering {

    /**
     * Der Spieler, der in diesem Level spielt.
     */
    private final Player player;

    /**
     * Die X- und Y-Koordinate, an der der Spieler spawnen soll.
     */
    private final float spawnX;
    private final float spawnY;

    /**
     * Die Map, die in diesem Level gespielt wird.
     */
    private final Map map;
    
    /**
     * Eine Liste aller Entities, die in diesem Level existieren.
     */
    private final ArrayList<Entity> entities;

    /**
     * Erstellt ein neues Level.
     * 
     * @param id die ID des Levels.
     * @param name der Name des Levels.
     * @param namespace der Namespace des Levels.
     * @param key der Schlüssel des Levels.
     * @param map die Map, die in diesem Level gespielt wird.
     * @param entities eine Liste aller Entities, die in diesem Level existieren.
     */
    public Level(int id, String name, String namespace, String key, Map map, HashSet<Entity> entities) {
        super(id, name, namespace, key);

        this.player = entities.stream().filter(e -> e instanceof Player).map(e -> (Player) e).findFirst().orElse(null);
        this.spawnX = 192;
        this.spawnY = 896;
        this.map = map;
        this.entities = new ArrayList<Entity>(entities);
    }

    /**
     * Führt die Spiellogik allter {@link Entity}s in diesem Level aus.
     */
    @Override
    public void tick() {
        entities.forEach(Entity::tick);
    }

    /**
     * Rendert die Map sowie alle {@link Entity}s in diesem Level.
     */
    @Override
    public void render(Graphics g) {
        map.render(g);
        entities.forEach(e -> e.render(g));
    }

    /**
     * Gibt den Spieler zurück.
     * 
     * @return der Spieler.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Gibt die {@link Location} aus den X- und Y-Koordinaten zurück, an der der Spieler spawnen soll.
     * 
     * @return die {@link Location}, an der der Spieler spawnen soll.
     */
    public Location getSpawnLocation() {
        return new Location(spawnX, spawnY);
    }

    /**
     * Gibt die Map zurück, die in diesem Level gespielt wird.
     * 
     * @return die Map, die in diesem Level gespielt wird.
     */
    public Map getMap() {
        return map;
    }

    /**
     * Gibt eine Liste aller Entities zurück, die in diesem Level existieren.
     * 
     * @return eine Liste aller Entities, die in diesem Level existieren.
     */
    public ArrayList<Entity> getEntities() {
        return entities;
    }
}