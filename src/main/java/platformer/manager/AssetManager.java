package platformer.manager;

import java.util.TreeMap;

import platformer.PlatformerGame;
import platformer.model.GameAsset;
import platformer.model.ResourceKey;
import platformer.util.Loader;

/**
 * Der AssetManager ist der Manager, der für die Verwaltung von Assets zuständig ist.
 * 
 * @author Jamil B.
 * @see Manager
 * @see GameAsset
 */
public abstract class AssetManager<Asset extends GameAsset> extends Manager {

    /**
     * Der Typ des Assets.
     */
    private final platformer.model.AssetType type;

    /**
     * Die Zuordnung von {@link ResourceKey}-Schlüsseln zu Assets.
     */
    protected final TreeMap<ResourceKey<Asset>, Asset> assets;

    /**
     * Der Loader, der die Assets lädt.
     */
    protected Loader<Asset> loader;

    /**
     * Erstellt einen neuen AssetManager.
     * 
     * @param game Instanz der Hauptklasse des Spiels
     */
    public AssetManager(PlatformerGame game) {
        super(game);

        this.type = platformer.model.AssetType.valueOf(getClass().getSimpleName().replace("Manager", "").toUpperCase());
        this.assets = new TreeMap<ResourceKey<Asset>, Asset>();
    }

    /**
     * Registriert ein Asset, indem es der {@link #assets}-TreeMap hinzugefügt wird.
     * 
     * @param key der {@link ResourceKey}, unter dem das Asset registriert werden soll
     * @param asset das Asset, das registriert werden soll
     */
    public void registerAsset(ResourceKey<Asset> key, Asset asset) {
        assets.put(key, asset);
    }

    /**
     * Gibt die Zuordnung von {@link ResourceKey}-Schlüsseln zu Assets zurück.
     * 
     * @return die Zuordnung von {@link ResourceKey}-Schlüsseln zu Assets
     */
    public TreeMap<ResourceKey<Asset>, Asset> getAssets() {
        return (TreeMap<ResourceKey<Asset>, Asset>) assets;
    }

    /**
     * Gibt den Typ des Assets zurück.
     * 
     * @return der Typ des Assets
     */
    public platformer.model.AssetType getType() {
        return type;
    }

    /**
     * Gibt den Loader zurück, der die Assets lädt.
     * 
     * @return der Loader, der die Assets lädt
     */
    public Loader<Asset> getLoader() {
        return loader;
    }
}