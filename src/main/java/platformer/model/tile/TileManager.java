package platformer.model.tile;

import platformer.PlatformerGame;
import platformer.manager.AssetManager;
import platformer.model.ResourceKey;
import platformer.model.spritesheet.Spritesheet;
import platformer.model.spritesheet.SpritesheetType;

/**
 * Ein spezialisierter {@link AssetManager}, welcher Tiles verwaltet.
 * 
 * @author Jamil B.
 */
public class TileManager extends AssetManager<Tile> {

    /**
     * Erstellt einen neuen TileManager.
     * 
     * @param game das Spiel, für das der TileManager erstellt werden soll.
     */
    public TileManager(PlatformerGame game) {
        super(game);

        initialize(game);
    }

    /**
     * Initialisiert den TileManager.
     */
    @Override
    public void initialize(PlatformerGame game) {
        initTiles("tiles_32x32.png");
    }

    /**
     * Lädt die Tiles aus der angegebenen Datei.
     * 
     * @param fileName der Name der Datei, aus der die Tiles geladen werden sollen.
     */
    private void initTiles(String fileName) {
        if (loader != null) return;

        game.debug("Loading tiles from " + fileName);

        Spritesheet spritesheet = new Spritesheet(SpritesheetType.TILE, fileName, 32, 32);

        this.loader = new TileLoader(spritesheet, fileName);

        loader.getLoadedAssets().forEach(this::registerTile);
    }

    /**
     * Registriert die angegebene Tile.
     * 
     * @param tile die Tile, die registriert werden soll.
     */
    @SuppressWarnings("unchecked")
    private void registerTile(Tile tile) {
        assets.put((ResourceKey<Tile>) tile.getKey(), tile);
    }

    /**
     * Gibt die geladenen Tiles in der Konsole aus.
     */
    public void printLoadedTiles() {
        game.debug("Loaded tiles: " + assets.size());

        assets.forEach((key, value) -> System.out.println(key + " -> " + value));
    }

    /**
     * Gibt den TileLoader zurück.
     * 
     * @return der TileLoader.
     */
    @Override
    public TileLoader getLoader() {
        return (TileLoader) loader;
    }
}