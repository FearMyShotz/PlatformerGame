package platformer.model.tile;

import java.util.TreeSet;

import platformer.model.spritesheet.Spritesheet;
import platformer.model.spritesheet.SpritesheetType;
import platformer.model.spritesheet.loader.SpritesheetLoader;
import platformer.model.tile.type.TileType;
import platformer.util.Initializable;

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

    public Tile getTileFromID(String id) {
        return loadedAssets.stream()
                .filter(tile -> tile.getTileType().getId() == Integer.valueOf(id))
                .findFirst()
                .orElse(null);
    }
}