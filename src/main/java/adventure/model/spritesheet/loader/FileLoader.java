package adventure.model.spritesheet.loader;

import java.util.Set;

import adventure.model.GameAsset;
import adventure.util.Loader;

public abstract class FileLoader<AssetType extends GameAsset> extends Loader<AssetType> {
    
    public FileLoader(Set<AssetType> assets) {
        super(assets);
    }

    public FileLoader() {
        super();
    }
}