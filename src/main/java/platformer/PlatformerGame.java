package platformer;

import java.util.ArrayList;

import platformer.input.InputManager;
import platformer.manager.Manager;
import platformer.model.GameAsset;
import platformer.model.ResourceKey;
import platformer.model.animation.AnimationManager;
import platformer.model.entity.EntityManager;
import platformer.model.level.LevelManager;
import platformer.model.state.GameState;
import platformer.model.state.PlayState;
import platformer.model.tile.TileManager;
import platformer.tick.TickManager;
import platformer.util.Registry;
import platformer.window.WindowManager;

/**
 * Die Hauptklasse des Spiels. Sie verwaltet alle anderen verwaltenden Klassen.
 * 
 * @author Jamil B.
 */
public class PlatformerGame {

    /**
     * Die Anzahl der Bildaktualisierungen pro Sekunde.
     */
    public static final int FRAMES_PER_SECOND = 60;

    /**
     * Die Anzahl der Spiellogik-Aktualisierungen pro Sekunde.
     */
    public static final int TICKS_PER_SECOND = 200;

    /**
     * Die Instanz dieser Klasse.
     */
    private static PlatformerGame instance;

    /**
     * Wahrheitswert, ob das Spiel im Debug-Modus läuft.
     */
    private final boolean debug;

    /**
     * Das aktuelle Spielzustand.
     */
    private GameState activeState;

    /**
     * Der Eingabe-Manager.
     */
    private final InputManager inputManager;

    /**
     * Der Fenster-Manager.
     */
    private final WindowManager windowManager;

    /**
     * Der Tick-Manager.
     */
    private final TickManager tickManager;

    /**
     * Der Animation-Manager.
     */
    private final AnimationManager animationManager;

    /**
     * Der Tile-Manager.
     */
    private final TileManager tileManager;

    /**
     * Der Level-Manager.
     */
    private final LevelManager levelManager;
    
    /**
     * Der Entity-Manager.
     */
    private final EntityManager entityManager;

    /**
     * Die Liste aller verwaltenden Klassen.
     */
    private final ArrayList<Manager> managers = new ArrayList<Manager>();

    /**
     * Das Spiel-Registry.
     */
    private final Registry<ResourceKey<?>, GameAsset> gameRegistry;

    /**
     * Erstellt ein neues PlatformerGame mit deaktiviertem Debug-Modus.
     */
    public PlatformerGame() {
        this(false);
    }

    /**
     * Erstellt ein neues PlatformerGame.
     * 
     * @param debug Wahrheitswert, ob der Debug-Modus aktiviert werden soll.
     */
    public PlatformerGame(boolean debug) {
        this.debug = debug;

        instance = this;

        this.gameRegistry = new Registry<ResourceKey<?>, GameAsset>();

        this.inputManager = new InputManager(this);
        this.tileManager = new TileManager(this);
        this.windowManager = new WindowManager(this);
        this.tickManager = new TickManager(this);
        this.animationManager = new AnimationManager(this);
        this.entityManager = new EntityManager(this, "res/map/default.map");
        this.levelManager = new LevelManager(this, "res/map/");
        
        this.activeState = new PlayState(this);

        getManagers().forEach(m -> m.initialize(this));

        tickManager.getGameLoop().start(activeState);
    }

    /**
     * Gibt die Instanz dieser Klasse zurück.
     * 
     * @return die Instanz dieser Klasse
     */
    public static final PlatformerGame getInstance() {
        return instance;
    }

    /**
     * Gibt das Spiel-Registry zurück.
     * 
     * @return das Spiel-Registry
     */
    public Registry<ResourceKey<?>, GameAsset> getGameRegistry() {
        return gameRegistry;
    }

    /**
     * Gibt den Eingabe-Manager zurück.
     * 
     * @return der Eingabe-Manager
     */
    public InputManager getInputManager() {
        return inputManager;
    }

    /**
     * Gibt den Fenster-Manager zurück.
     * 
     * @return der Fenster-Manager
     */
    public WindowManager getWindowManager() {
        return windowManager;
    }

    /**
     * Gibt den Tick-Manager zurück.
     * 
     * @return der Tick-Manager
     */
    public TickManager getTickManager() {
        return tickManager;
    }

    /**
     * Gibt den Animation-Manager zurück.
     * 
     * @return der Animation-Manager
     */
    public AnimationManager getAnimationManager() {
        return animationManager;
    }

    /**
     * Gibt den Tile-Manager zurück.
     * 
     * @return der Tile-Manager
     */
    public TileManager getTileManager() {
        return tileManager;
    }

    /**
     * Gibt den Level-Manager zurück.
     * 
     * @return der Level-Manager
     */
    public LevelManager getLevelManager() {
        return levelManager;
    }

    /**
     * Gibt den Entity-Manager zurück.
     * 
     * @return der Entity-Manager
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * Gibt den aktiven Spielzustand zurück.
     * 
     * @return der aktive Spielzustand
     */
    public GameState getActiveState() {
        return activeState;
    }
    
    /**
     * Gibt die {@link ArrayList} aller verwaltenden Klassen zurück.
     */
    public ArrayList<Manager> getManagers() {
        return managers;
    }

    /**
     * Gibt zurück, ob der Debug-Modus aktiviert ist.
     * 
     * @return Wahrheitswert, ob der Debug-Modus aktiviert ist
     */
    public boolean isDebug() {
        return debug;
    }

    /**
     * Gibt eine Nachricht in die Konsole aus.
     * @param message die zu sendende Nachricht
     */
    public void log(String message) {
        System.out.println("[INFO] " + message);
    }

    /**
     * Gibt eine Nachricht in die Konsole aus, wenn der Debug-Modus aktiviert ist.
     * @param debug die zu sendende Nachricht
     */
    public void debug(String debug) {
        if (isDebug()) {
            System.out.println("[DEBUG] " + debug);
        }
    }
}