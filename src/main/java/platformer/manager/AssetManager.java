package platformer.manager;

import java.util.TreeMap;

import platformer.PlatformerGame;
import platformer.model.GameAsset;
import platformer.model.ResourceKey;
import platformer.util.Loader;

public abstract class AssetManager<Asset extends GameAsset> extends Manager {

    private final platformer.model.AssetType type;

    protected final TreeMap<ResourceKey<Asset>, Asset> assets;

    protected Loader<Asset> loader;

    public AssetManager(PlatformerGame game) {
        super(game);

        this.type = platformer.model.AssetType.valueOf(getClass().getSimpleName().replace("Manager", "").toUpperCase());
        this.assets = new TreeMap<ResourceKey<Asset>, Asset>();
    }

    public void registerAsset(ResourceKey<Asset> key, Asset asset) {
        assets.put(key, asset);
    }

    public TreeMap<ResourceKey<Asset>, Asset> getAssets() {
        return (TreeMap<ResourceKey<Asset>, Asset>) assets;
    }

    public platformer.model.AssetType getType() {
        return type;
    }

    public Loader<Asset> getLoader() {
        return loader;
    }
}