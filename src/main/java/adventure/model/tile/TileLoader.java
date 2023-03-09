package adventure.model.tile;

import java.util.Set;
import java.util.TreeSet;

import adventure.model.spritesheet.Spritesheet;
import adventure.model.spritesheet.SpritesheetType;
import adventure.model.spritesheet.loader.SpritesheetLoader;
import adventure.model.tile.type.TileType;
import adventure.util.Initializable;

public class TileLoader extends SpritesheetLoader<Tile> implements Initializable<String> {

    public TileLoader(Spritesheet spritesheet, String fileName) {
        super(new TreeSet<Tile>(), spritesheet, fileName);

        initialize(fileName);
    }

    @Override
    public void initialize(String fileName) {
        if (!(matchesType.test(SpritesheetType.TILE)) || !(matchesFile.test("tiles_32x32.png"))) return;

        load();
    }

    @Override
    public Set<Tile> load() {
        for (TileType type : TileType.values()) {
            Tile tile = new Tile.Builder()
                    .setType(type)
                    .setImage(new TileImage.Builder()
                            .setCoords(type.getTextureCoords())
                            .setTileImage(spritesheet.getSprite(type.getTextureCoords()))
                            .build())
                    .build();
            registerAsset(tile);
        }
               
        return loadedAssets;
    }

    public Tile getTileFromID(String id) {
        return loadedAssets.stream()
                .filter(tile -> tile.getTileType().getId() == Integer.valueOf(id))
                .findFirst()
                .orElse(null);
    }

    public Set<Tile> getLoadedTiles() {
        return loadedAssets;
    }
}