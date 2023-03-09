package adventure.model.entity;

import java.util.Set;
import java.util.TreeSet;

import adventure.AdventureGame;
import adventure.manager.AssetManager;
import adventure.model.entity.loader.EntityLoader;

public class EntityManager extends AssetManager<Entity> {

    private final EntityLoader loader;

    public EntityManager(AdventureGame game, String filePath) {
        super(game);

        this.loader = new EntityLoader(new TreeSet<Entity>(assets.values()), filePath);
    }
    
    public Set<Entity> getLoadedEntities() {
        return loader.getLoadedAssets();
    }    
}