package platformer.util;

import java.util.Set;

import platformer.model.GameAsset;

public abstract class Loader<AssetType extends GameAsset> {

    protected Set<AssetType> loadedAssets;

    public Loader(Set<AssetType> assets) {
        this.loadedAssets = assets;
    }

    public abstract Set<AssetType> load();

    public Set<AssetType> getLoadedAssets() {
        return loadedAssets;
    }

    public boolean isInitialized() {
        return loadedAssets != null && !loadedAssets.isEmpty();
    }
}