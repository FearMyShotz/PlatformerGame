package platformer.tick;

/**
 * Eine Schnittstelle für alle Spielobjekte, die eine Spiellogik besitzen.
 * 
 * Erweitert die Schnittstelle {@link Runnable} um die Methode {@link #tick()}.
 * 
 * @author Jamil B.
 */
@FunctionalInterface
public interface Ticking extends Runnable {
    
    /**
     * Führt die Spiellogik aus.
     */
    void tick();

    /**
     * Überschreibt die {@link Runnable#run()}-Methode und führt die {@link #tick()}-Methode aus.
     */
    @Override
    default void run() {
        tick();
    }
}