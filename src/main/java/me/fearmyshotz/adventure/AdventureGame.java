package me.fearmyshotz.adventure;

import java.util.HashSet;
import java.util.Set;

import org.jetbrains.annotations.NotNull;

import me.fearmyshotz.adventure.manager.Manager;
import me.fearmyshotz.adventure.manager.implementation.AnimationManager;
import me.fearmyshotz.adventure.manager.implementation.TickManager;
import me.fearmyshotz.adventure.manager.implementation.WindowManager;

/**
 * @author Jamil B.
 */
public class AdventureGame {

    public static final int FRAMES_PER_SECOND = 60;
    public static final int TICKS_PER_SECOND = 200;

    private static @NotNull AdventureGame instance;

    // private final Logger logger;

    private final boolean debug;

    // private final LogManager logManager;
    private final WindowManager windowManager;
    private final TickManager tickManager;
    private final AnimationManager animationManager;

    private final Set<Manager> managers = new HashSet<>();

    public AdventureGame() {
        this(false);
    }

    public AdventureGame(boolean debug) {
        this.debug = debug;

        // this.logger = Logger.getLogger("AdventureGame");
        // Logger-Einstellungen

        // this.logManager = new LogManager(this);
        this.windowManager = new WindowManager(this);
        this.tickManager = new TickManager(this);
        this.animationManager = new AnimationManager(this);

        getManagers().forEach(m -> m.initialize(this));

        instance = this;
    }

    public static final @NotNull AdventureGame getInstance() {
        return instance;
    }

    // public Logger getLogger() {
    //     return logger;
    // }

    public WindowManager getWindowManager() {
        return windowManager;
    }

    public TickManager getTickManager() {
        return tickManager;
    }

    public AnimationManager getAnimationManager() {
        return animationManager;
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