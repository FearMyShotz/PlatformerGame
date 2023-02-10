package me.fearmyshotz.adventure.manager.implementation;

import java.util.TreeMap;
import java.util.TreeSet;

import me.fearmyshotz.adventure.AdventureGame;
import me.fearmyshotz.adventure.manager.Manager;
import me.fearmyshotz.adventure.model.ResourceKey;
import me.fearmyshotz.adventure.model.spritesheet.Spritesheet;
import me.fearmyshotz.adventure.model.spritesheet.SpritesheetType;
import me.fearmyshotz.adventure.model.tile.Tile;
import me.fearmyshotz.adventure.model.tile.TileLoader;

public class TileManager extends Manager {

    private final TreeMap<ResourceKey<Tile>, Tile> loadedTiles;

    private TileLoader loader;

    public TileManager(AdventureGame game) {
        super(game);

        this.loadedTiles = new TreeMap<ResourceKey<Tile>, Tile>();
    }

    @Override
    public void initialize(AdventureGame game) {
        super.initialize(game);

        initTiles("tiles_32x32.png");
    }
    
    private void initTiles(String fileName) {
        Spritesheet spritesheet = new Spritesheet(SpritesheetType.TILE, fileName, 32, 32);

        this.loader = new TileLoader(spritesheet, fileName);

        loader.getLoadedTiles().forEach(this::registerTile);
        
    }

    @SuppressWarnings("unchecked")
    private void registerTile(Tile tile) {
        loadedTiles.put((ResourceKey<Tile>) tile.getKey(), tile);
    }

    public TreeMap<ResourceKey<Tile>, Tile> getLoadedTiles() {
        return loadedTiles;
    }

    public TreeSet<Tile> getLoadedTilesSet() {
        return new TreeSet<Tile>(loadedTiles.values());
    }
}