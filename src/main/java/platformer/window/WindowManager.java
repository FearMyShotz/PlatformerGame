package platformer.window;

import platformer.PlatformerGame;
import platformer.manager.Manager;

/**
 * Ein spezialisierter {@link Manager}, der die Fenster verwaltet.
 * 
 * @author Jamil B.
 */
public class WindowManager extends Manager {
    
    /**
     * Das Panel, auf dem das Spiel gezeichnet wird.
     */
    private GamePanel panel;

    /**
     * Das Fenster, in dem das Spiel läuft.
     */
    private GameWindow window;

    /**
     * Erstellt ein neues WindowManager.
     * 
     * @param game die Instanz des Spiels.
     */
    public WindowManager(PlatformerGame game) {
        super(game);

        this.panel = new GamePanel();
        this.window = new GameWindow(panel);
    }

    /**
     * Gibt das GamePanel zurück.
     * 
     * @return das GamePanel
     */
    public GamePanel getPanel() {
        return panel;
    }

    /**
     * Gibt das GameWindow zurück.
     * 
     * @return das GameWindow
     */
    public GameWindow getWindow() {
        return window;
    }
}