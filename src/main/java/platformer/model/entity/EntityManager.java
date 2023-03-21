package platformer.model.entity;

import java.util.HashSet;

import platformer.PlatformerGame;
import platformer.manager.AssetManager;
import platformer.model.entity.loader.EntityLoader;

public class EntityManager extends AssetManager<Entity> {

    public EntityManager(PlatformerGame game, String filePath) {
        super(game);

        this.loader = new EntityLoader(new HashSet<Entity>(assets.values()), filePath);
        this.loader.load();
    }
    
    public HashSet<Entity> getLoadedEntities() {
        return new HashSet<Entity>(loader.getLoadedAssets());
    }    
}