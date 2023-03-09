package adventure;

import java.util.HashSet;
import java.util.Set;

import org.jetbrains.annotations.NotNull;

import adventure.input.InputManager;
import adventure.manager.Manager;
import adventure.model.GameAsset;
import adventure.model.ResourceKey;
import adventure.model.animation.AnimationManager;
import adventure.model.entity.EntityManager;
import adventure.model.level.LevelManager;
import adventure.model.state.GameState;
import adventure.model.state.PlayState;
import adventure.model.tile.TileManager;
import adventure.tick.TickManager;
import adventure.util.Registry;
import adventure.window.WindowManager;

/**
 * @author Jamil B.
 */
public class AdventureGame {

    public static final int FRAMES_PER_SECOND = 120;
    public static final int TICKS_PER_SECOND = 200;

    private static @NotNull AdventureGame instance;

    // private final Logger logger;

    private final boolean debug;

    private GameState activeState;

    // private final LogManager logManager;
    private final InputManager inputManager;

    private final WindowManager windowManager;
    private final TickManager tickManager;
    private final AnimationManager animationManager;
    private final TileManager tileManager;
    // WIP 
    private final LevelManager levelManager;
    private final EntityManager entityManager;

    private final Set<Manager> managers = new HashSet<Manager>();

    private final Registry<ResourceKey<?>, GameAsset> gameRegistry;

    public AdventureGame() {
        this(false);
    }

    public AdventureGame(boolean debug) {
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

        // Tests

        tickManager.start();
    }

    public InputManager getInputManager() {
        return inputManager;
    }

    public static final @NotNull AdventureGame getInstance() {
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
    
    public Set<Manager> getManagers() {
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