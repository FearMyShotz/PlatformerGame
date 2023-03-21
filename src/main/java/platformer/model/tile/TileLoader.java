package platformer.model.tile;

import java.util.TreeSet;

import platformer.model.spritesheet.Spritesheet;
import platformer.model.spritesheet.SpritesheetType;
import platformer.model.spritesheet.loader.SpritesheetLoader;
import platformer.model.tile.type.TileType;
import platformer.util.Initializable;

/**
 * Ein spezialisierter {@link SpritesheetLoader}, welcher Tiles lädt.
 * 
 * @author Jamil B.
 */
public class TileLoader extends SpritesheetLoader<Tile> implements Initializable<String> {

    /**
     * Erstellt und initialisiert einen neuen TileLoader.
     * 
     * @param spritesheet das Spritesheet, welches die Tiles enthält.
     * @param fileName der Name der Datei, aus der die Tiles geladen werden sollen.
     */
    public TileLoader(Spritesheet spritesheet, String fileName) {
        super(new TreeSet<Tile>(), spritesheet, fileName);

        initialize(fileName);
    }

    /**
     * Initialisiert den TileLoader.
     */
    @Override
    public void initialize(String fileName) {
        if (!(matchesType.test(SpritesheetType.TILE)) || !(matchesFile.test("tiles_32x32.png"))) return;

        load();
    }

    /**
     * Lädt die Tiles aus dem Spritesheet.
     * 
     * @return die geladenen Tiles.
     */
    @Override
    public TreeSet<Tile> load() {
        for (TileType type : TileType.values()) {
            registerAsset(
                new Tile.Builder()
                    .setType(type)
                    .setImage(
                        new TileImage.Builder()
                            .setCoords(type.getTextureCoords())
                            .setTileImage(spritesheet.getSprite(type.getTextureCoords()))
                            .build()
                    )
                .build()
            );
        }
               
        return new TreeSet<Tile>(loadedAssets);
    }

    /**
     * Gibt ein Tile mit der angegebenen ID zurück.
     * 
     * @param id die ID des Tiles.
     * @return ein Tile mit der angegebenen ID.
     */
    public Tile getTileFromID(String id) {
        return loadedAssets.stream()
                .filter(tile -> tile.getTileType().getId() == Integer.valueOf(id))
                .findFirst()
                .orElse(null);
    }
}