package adventure.model.level;

import adventure.AdventureGame;
import adventure.manager.AssetManager;
import adventure.model.map.Map;

public class LevelManager extends AssetManager<Level> {

    private Level currentLevel;

    private String filePath;

    public LevelManager(AdventureGame game, String filePath) {
        super(game);

        this.filePath = filePath;
        initialize(game);
    }

    public void initialize(AdventureGame game) {
        Map defaultMap = new Map(game.getTileManager(), filePath + "default.map");

        this.currentLevel = new Level(
            assets.size(), 
            "Level", 
            "level", 
            "level" + assets.size(), 
            defaultMap, 
            game.getEntityManager().getLoadedEntities()
        );
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }
    
}