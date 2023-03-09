package adventure.model.tile;

import java.util.TreeMap;

import adventure.AdventureGame;
import adventure.manager.AssetManager;
import adventure.model.ResourceKey;
import adventure.model.spritesheet.Spritesheet;
import adventure.model.spritesheet.SpritesheetType;

public class TileManager extends AssetManager<Tile> {

    // private final TreeMap<ResourceKey<Tile>, Tile> loadedTiles;

    private TileLoader loader;

    public TileManager(AdventureGame game) {
        super(game);

        // this.loadedTiles = new TreeMap<ResourceKey<Tile>, Tile>();
        initialize(game);
    }

    @Override
    public void initialize(AdventureGame game) {
        // super.initialize(game);

        initTiles("tiles_32x32.png");

        // printLoadedTiles();
    }

    private void initTiles(String fileName) {
        if (loader != null) return;

        Spritesheet spritesheet = new Spritesheet(SpritesheetType.TILE, fileName, 32, 32);

        game.debug("Loading tiles from " + fileName);

        this.loader = new TileLoader(spritesheet, fileName);

        loader.getLoadedTiles().forEach(this::registerTile);
    }

    @SuppressWarnings("unchecked")
    private void registerTile(Tile tile) {
        assets.put((ResourceKey<Tile>) tile.getKey(), tile);
    }

    public TileLoader getLoader() {
        return loader;
    }

    public TreeMap<ResourceKey<Tile>, Tile> getLoadedTiles() {
        return new TreeMap<ResourceKey<Tile>, Tile>(assets);
    }

    @SuppressWarnings("unused")
    private void printLoadedTiles() {
        System.out.println("Loaded Tiles: " + assets.size());

        assets.forEach((key, value) -> System.out.println(key + " -> " + value));
    }
}