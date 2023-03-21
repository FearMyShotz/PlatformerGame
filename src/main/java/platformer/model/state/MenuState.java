package platformer.model.state;

import java.awt.Graphics;

import platformer.PlatformerGame;
import platformer.input.keyboard.KeyAction;

/**
 * Ein Spielzustand, welcher Spiellogik für Menüs enthält und dargestellt werden kann.
 * 
 * Wird derzeit nicht verwendet (Stand: 21.03.2023)
 * 
 * @author Jamil B.
 * @see Ticking
 * @see Rendering
 */
public class MenuState extends GameState {

    /**
     * Erstellt einen neuen Menü-Spielzustand.
     * 
     * @param game die Instanz des Spiels, in welchem sich der Spielzustand befindet
     */
    public MenuState(PlatformerGame game) {
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
     * @see GameState#handleKeyAction(KeyAction, int)
     */
    @Override
    public void handleKeyAction(KeyAction action, int keyCode) {
        
    }
}