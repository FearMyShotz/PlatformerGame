package platformer.model.spritesheet.loader;

import java.util.AbstractSet;
import java.util.function.Predicate;

import platformer.model.GameAsset;
import platformer.model.spritesheet.Spritesheet;
import platformer.model.spritesheet.SpritesheetType;
import platformer.util.Loader;

public abstract class SpritesheetLoader<AssetType extends GameAsset> extends Loader<AssetType> {

    protected final Predicate<SpritesheetType> matchesType;
    protected final Predicate<String> matchesFile;

    protected final Spritesheet spritesheet;

    protected final String fileName;

    public SpritesheetLoader(AbstractSet<AssetType> assets, Spritesheet sheet, String file) {
        super(assets);

        this.spritesheet = sheet;
        this.fileName = file;

        this.matchesType = (providedType) -> spritesheet.getType() == providedType;
        this.matchesFile = (providedFileName) -> fileName.equals(providedFileName);
    }

    protected final void registerAsset(AssetType asset) {
        loadedAssets.add(asset);
    }

    protected final void registerAssets(Iterable<AssetType> assets) {
        assets.forEach(this::registerAsset);
    }

    public final Spritesheet getSpritesheet() {
        return spritesheet;
    }

    public final String getFileName() {
        return fileName;
    }
}