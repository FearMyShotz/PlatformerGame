package platformer.model.state;

import platformer.PlatformerGame;
import platformer.input.keyboard.KeyAction;
import platformer.tick.Rendering;
import platformer.tick.Ticking;

/**
 * Ein Spielzustand, welcher Spiellogik enthält und dargestellt werden kann.
 * 
 * @author Jamil B.
 * @see Ticking
 * @see Rendering
 */
public abstract class GameState implements Ticking, Rendering {
    
    /**
     * Die Instanz des Spiels, in welchem sich der Spielzustand befindet.
     */
    protected final PlatformerGame game;

    /**
     * Erstellt einen neuen Spielzustand.
     * 
     * @param game die Instanz des Spiels, in welchem sich der Spielzustand befindet
     */
    public GameState(PlatformerGame game) {
        this.game = game;
    }

    /**
     * Gibt die Instanz des Spiels zurück.
     * 
     * @return die Instanz des Spiels
     */
    public PlatformerGame getGame() {
        return game;
    }

    /**
     * Verarbeitet eine Tastenaktion.
     * 
     * @param action die Tastenaktion
     * @param keyCode der Tastencode
     */
    public abstract void handleKeyAction(KeyAction action, int keyCode);
}