package me.fearmyshotz.adventure.tick;

/**
 * @author Jamil B.
 */
@FunctionalInterface
public interface Ticking extends Runnable {
    
    void tick();

    @Override
    default void run() {
        tick();
    }
}