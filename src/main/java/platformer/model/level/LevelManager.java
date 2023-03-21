package platformer.model.level;

import platformer.PlatformerGame;
import platformer.manager.AssetManager;
import platformer.model.map.Map;

/**
 * Ein spezialisierter {@link AssetManager}, der {@link Level}s verwaltet.
 * 
 * @author Jamil B.
 * @see AssetManager
 */
public class LevelManager extends AssetManager<Level> {

    /**
     * Das aktuelle Level.
     */
    private Level currentLevel;

    /**
     * Der Pfad, in dem die Level-Datei gespeicht ist.
     */
    private String filePath;

    /**
     * Erstellt einen neuen LevelManager.
     * 
     * @param game das Spiel, in dem der LevelManager verwendet wird.
     * @param filePath der Pfad, in dem die Level-Datei gespeichert ist.
     */
    public LevelManager(PlatformerGame game, String filePath) {
        super(game);

        this.filePath = filePath;
        initialize(game);
    }

    /**
     * Initialisiert diesen LevelManager.
     * 
     * @param game die Spielinstanz, in der dieser Manager verwendet wird.
     */
    public void initialize(PlatformerGame game) {
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

    /**
     * Gibt das aktuelle Level zurück.
     * 
     * @return das aktuelle Level.
     */
    public Level getCurrentLevel() {
        return currentLevel;
    }
}