package platformer.manager;

import platformer.PlatformerGame;
import platformer.util.Initializable;

/**
 * Der Manager ist die Oberklasse aller Manager. Sie sind für die Verwaltung von Objekten zuständig.
 * 
 * @author Jamil B.
 * @see Initializable
 */
public abstract class Manager implements Initializable<PlatformerGame> {
    
    /**
     * Die Instanz der Hauptklasse des Spiels.
     */
    protected final PlatformerGame game;

    /**
     * Erstellt einen neuen Manager.
     * 
     * @param game Instanz der Hauptklasse des Spiels
     */
    public Manager(PlatformerGame game) {
        this.game = game;
        
        game.getManagers().add(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(PlatformerGame game) {
        game.log("Initializing " + getClass().getSimpleName() + "...");
    }

    /**
     * Gibt die Instanz der Hauptklasse des Spiels zurück.
     * 
     * @return die Instanz der Hauptklasse des Spiels
     */
    public PlatformerGame getGame() {
        return game;
    }

    /**
     * Gibt zurück, ob der Manager initialisiert wurde.
     * 
     * @return true, wenn der Manager initialisiert wurde, sonst false
     */
    public boolean isInitialized() {
        return game.getManagers().contains(this);
    }
}