package platformer.util;

import java.util.Set;

import platformer.model.GameAsset;

/**
 * Ein abstrakter {@link Loader}, der {@link GameAsset}s lädt.
 * 
 * @param <AssetType> der Typ der Assets, die geladen werden sollen
 * @author Jamil B.
 */
public abstract class Loader<AssetType extends GameAsset> {

    /**
     * Die geladenen Assets.
     */
    protected Set<AssetType> loadedAssets;

    /**
     * Erstellt einen neuen Loader.
     * 
     * @param assets die Assets, die geladen werden sollen
     */
    public Loader(Set<AssetType> assets) {
        this.loadedAssets = assets;
    }

    /**
     * Lädt die Assets.
     * 
     * @return die geladenen Assets
     */
    public abstract Set<AssetType> load();

    /**
     * Gibt die geladenen Assets zurück.
     * 
     * @return die geladenen Assets
     */
    public Set<AssetType> getLoadedAssets() {
        return loadedAssets;
    }

    /**
     * Gibt zurück, ob die Assets geladen wurden.
     * 
     * @return true, wenn die Assets geladen wurden
     */
    public boolean isInitialized() {
        return loadedAssets != null && !loadedAssets.isEmpty();
    }
}