package me.fearmyshotz.adventure.model.tile;

import java.util.Set;
import java.util.TreeSet;

import me.fearmyshotz.adventure.model.spritesheet.Spritesheet;
import me.fearmyshotz.adventure.model.spritesheet.SpritesheetType;
import me.fearmyshotz.adventure.model.tile.type.TileType;
import me.fearmyshotz.adventure.util.Initializable;
import me.fearmyshotz.adventure.util.SpritesheetLoader;

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
        Tile grassTop = new Tile.Builder()
                .setType(TileType.GRASS_TOP)
                .setImage(new TileImage.Builder()
                        .setX(1)
                        .setY(0)
                        .setTileImage(spritesheet.getSprite(1, 0))
                        .build())
                .build();
        registerAsset(grassTop);
        return loadedAssets;
    }

    public Set<Tile> getLoadedTiles() {
        return loadedAssets;
    }
}