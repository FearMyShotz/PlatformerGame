package me.fearmyshotz.adventure;

import org.jetbrains.annotations.NotNull;

import me.fearmyshotz.adventure.manager.implementation.TickManager;
import me.fearmyshotz.adventure.manager.implementation.WindowManager;

/**
 * @author Jamil B.
 */
public class AdventureGame {
    
    public static final int FRAMES_PER_SECOND = 120;
    public static final int TICKS_PER_SECOND = 200;

    private static @NotNull AdventureGame instance;

    private final boolean debug;

    private final WindowManager windowManager;
    private final TickManager tickManager;

    public AdventureGame() {
        this(false);
    }

    public AdventureGame(boolean debug) {
        this.debug = debug;

        this.windowManager = new WindowManager(this);
        this.tickManager = new TickManager(this);


        instance = this;
    }

    public static final @NotNull AdventureGame getInstance() {
        return instance;
    }

    public WindowManager getWindowManager() {
        return windowManager;
    }

    public TickManager getTickManager() {
        return tickManager;
    }

    public boolean isDebug() {
        return debug;
    }

}