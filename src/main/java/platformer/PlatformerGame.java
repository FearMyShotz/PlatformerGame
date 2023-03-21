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
 * @author Jamil B.
 */
public class PlatformerGame {

    public static final int FRAMES_PER_SECOND = 60;
    public static final int TICKS_PER_SECOND = 200;

    private static PlatformerGame instance;

    // private final Logger logger;

    private final boolean debug;

    private GameState activeState;

    private final InputManager inputManager;

    private final WindowManager windowManager;
    private final TickManager tickManager;
    private final AnimationManager animationManager;
    private final TileManager tileManager;
    private final LevelManager levelManager;
    private final EntityManager entityManager;

    private final ArrayList<Manager> managers = new ArrayList<Manager>();

    private final Registry<ResourceKey<?>, GameAsset> gameRegistry;

    public PlatformerGame() {
        this(false);
    }

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

    public InputManager getInputManager() {
        return inputManager;
    }

    public static final PlatformerGame getInstance() {
        return instance;
    }

    public Registry<ResourceKey<?>, GameAsset> getGameRegistry() {
        return gameRegistry;
    }

    public WindowManager getWindowManager() {
        return windowManager;
    }

    public TickManager getTickManager() {
        return tickManager;
    }

    public AnimationManager getAnimationManager() {
        return animationManager;
    }

    public TileManager getTileManager() {
        return tileManager;
    }

    public LevelManager getLevelManager() {
        return levelManager;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public GameState getActiveState() {
        return activeState;
    }
    
    public ArrayList<Manager> getManagers() {
        return managers;
    }

    public boolean isDebug() {
        return debug;
    }

    public void log(String message) {
        System.out.println("[INFO] " + message);
    }

    public void debug(String debug) {
        if (isDebug()) {
            System.out.println("[DEBUG] " + debug);
        }
    }
}