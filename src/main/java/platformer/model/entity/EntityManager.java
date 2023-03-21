package platformer.model.entity;

import java.util.HashSet;

import platformer.PlatformerGame;
import platformer.manager.AssetManager;
import platformer.model.entity.loader.EntityLoader;

/**
 * Ein spezialisierter {@link AssetManager}, der {@link Entity}s verwaltet.
 * 
 * @author Jamil B.
 * @see AssetManager
 */
public class EntityManager extends AssetManager<Entity> {

    /**
     * Erstellt einen neuen EntityManager.
     * 
     * @param game die Spielinstanz, in der dieser Manager verwendet wird.
     * @param filePath der Pfad zur Datei, die geladen werden soll.
    */
    public EntityManager(PlatformerGame game, String filePath) {
        super(game);

        this.loader = new EntityLoader(new HashSet<Entity>(assets.values()), filePath);
        this.loader.load();
    }
    
    /**
     * Gibt eine Liste aller geladenen {@link Entity}s zurück.
     * 
     * @return eine Liste aller geladenen {@link Entity}s.
     */
    public HashSet<Entity> getLoadedEntities() {
        return new HashSet<Entity>(loader.getLoadedAssets());
    }    
}