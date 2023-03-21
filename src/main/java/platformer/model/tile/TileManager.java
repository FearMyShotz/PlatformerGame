package platformer.model.tile;

import platformer.PlatformerGame;
import platformer.manager.AssetManager;
import platformer.model.ResourceKey;
import platformer.model.spritesheet.Spritesheet;
import platformer.model.spritesheet.SpritesheetType;

public class TileManager extends AssetManager<Tile> {

    public TileManager(PlatformerGame game) {
        super(game);

        initialize(game);
    }

    @Override
    public void initialize(PlatformerGame game) {
        initTiles("tiles_32x32.png");

        // printLoadedTiles();
    }

    private void initTiles(String fileName) {
        if (loader != null) return;

        game.debug("Loading tiles from " + fileName);

        Spritesheet spritesheet = new Spritesheet(SpritesheetType.TILE, fileName, 32, 32);

        this.loader = new TileLoader(spritesheet, fileName);

        loader.getLoadedAssets().forEach(this::registerTile);
    }

    @SuppressWarnings("unchecked")
    private void registerTile(Tile tile) {
        assets.put((ResourceKey<Tile>) tile.getKey(), tile);
    }

    public void printLoadedTiles() {
        game.debug("Loaded tiles: " + assets.size());

        assets.forEach((key, value) -> System.out.println(key + " -> " + value));
    }

    @Override
    public TileLoader getLoader() {
        return (TileLoader) loader;
    }
}