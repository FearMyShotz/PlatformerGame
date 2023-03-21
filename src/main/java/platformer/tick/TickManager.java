package platformer.tick;

import platformer.PlatformerGame;
import platformer.manager.Manager;

/**
 * Der TickManager verwaltet die GameLoop.
 * 
 * @author Jamil B.
 */
public class TickManager extends Manager {

    /**
     * Die GameLoop.
     */
    private final GameLoop gameLoop;

    /**
     * Erstellt einen neuen TickManager.
     * 
     * @param game die Instanz der Hauptklasse des Spiels
     */
    public TickManager(PlatformerGame game) {
        super(game);

        this.gameLoop = new GameLoop(game.getWindowManager().getPanel());
    }

    /**
     * Gibt die GameLoop zurück.
     * 
     * @return die GameLoop-Instanz
     */
    public GameLoop getGameLoop() {
        return gameLoop;
    }
}