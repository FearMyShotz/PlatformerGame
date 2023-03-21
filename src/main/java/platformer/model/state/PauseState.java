package platformer.model.state;

import java.awt.Graphics;

import platformer.PlatformerGame;
import platformer.input.keyboard.KeyAction;

/**
 * Ein Spielzustand, welcher Spiellogik für Pausen enthält und dargestellt werden kann.
 * 
 * Derzeit nicht implementiert (Stand: 21.03.2023)
 * 
 * @author Jamil B.
 * @see Ticking
 * @see Rendering
 */
public class PauseState extends GameState {

    /**
     * Erstellt einen neuen Pausen-Spielzustand.
     * 
     * @param game die Instanz des Spiels, in welchem sich der Spielzustand befindet
     */
    public PauseState(PlatformerGame game) {
        super(game);
    }

    /**
     * Führt die Spiellogik für einen Tick aus.
     * 
     * Nicht implementiert.
     * 
     * @see Ticking#tick()
     */
    @Override
    public void tick() {
        
    }

    /**
     * Zeichnet den Spielzustand.
     * 
     * Nicht implementiert.
     * 
     * @see Rendering#render(Graphics)
     * @param g das Graphics-Objekt, mit welchem gezeichnet wird
     */
    @Override
    public void render(Graphics g) {
        
    }

    /**
     * Verarbeitet eine Tastenaktion.
     * 
     * Nicht implementiert.
     * 
     * @param action die Tastenaktion
     * @param keyCode der Tastencode
     */
    @Override
    public void handleKeyAction(KeyAction action, int keyCode) {
        
    }
}