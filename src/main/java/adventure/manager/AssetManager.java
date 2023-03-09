package adventure.manager;

import java.util.Map;
import java.util.TreeMap;

import adventure.AdventureGame;
import adventure.model.GameAsset;
import adventure.model.ResourceKey;

public abstract class AssetManager<Asset extends GameAsset> extends Manager {

    private final adventure.model.AssetType type;

    protected final Map<ResourceKey<Asset>, Asset> assets;

    public AssetManager(AdventureGame game) {
        super(game);

        this.type = adventure.model.AssetType.valueOf(getClass().getSimpleName().replace("Manager", "").toUpperCase());
        this.assets = new TreeMap<ResourceKey<Asset>, Asset>();
    }

    public void registerAsset(ResourceKey<Asset> key, Asset asset) {
        assets.put(key, asset);
    }

    public TreeMap<ResourceKey<Asset>, Asset> getAssets() {
        return (TreeMap<ResourceKey<Asset>, Asset>) assets;
    }

    public adventure.model.AssetType getType() {
        return type;
    }
    
}