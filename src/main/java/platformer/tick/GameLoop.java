package platformer.tick;

import static platformer.PlatformerGame.FRAMES_PER_SECOND;
import static platformer.PlatformerGame.TICKS_PER_SECOND;

import platformer.PlatformerGame;
import platformer.model.state.GameState;
import platformer.window.GamePanel;

public class GameLoop implements Runnable {

    private final Thread gameThread;

    private final GamePanel panel;
    private GameState activeState;

    private transient volatile boolean running;

    public GameLoop(final GamePanel panel) {
        this.gameThread = new Thread(this);
        this.gameThread.setName("Game Thread");

        this.panel = panel;
        this.activeState = null;

        this.running = false;
    }

    public void start(final GameState state) {
        this.activeState = state;

        gameThread.start();
        running = true;
    }

    public void pause() {
        running = false;
    }

    public void resume() {
        running = true;
    }

    @Override
    public void run() { // Delta-Methode
        // Anzahl der Ticks und Frames pro Sekunde
        int tickCounter = 0, frameCounter = 0;

        // Intervalle für Ticks und Frames in Nanosekunden
        final double tickInterval = 1_000_000_000 / TICKS_PER_SECOND;
        final double renderInterval = 1_000_000_000 / FRAMES_PER_SECOND;

        // Delta-Werte für Ticks und Frames
        double deltaTick = 0, deltaRender = 0;

        // Startzeit in Nanosekunden, Timer in Millisekunden zum Zählen der Ticks und Frames pro Sekunde
        long startTime = System.nanoTime(), timer = System.currentTimeMillis();

        do {
            // Aktuelle Zeit in Nanosekunden
            final long currentTime = System.nanoTime();

            // AdventureGame.getInstance().debug("Delta tick: " + deltaTick);

            // die Differenz ergibt sich aus der aktuellen Zeit und der Startzeit
            // und wird durch die Tick-Intervall geteilt, um den Delta-Wert zu erhalten

            // Erhöht den Delta-Wert für Ticks um die Zeit seit dem letzten Tick
            deltaTick += (currentTime - startTime) / tickInterval; // liegt zwischen 0 und 1
            // Erhöht den Delta-Wert für Frames um die Zeit seit dem letzten Frame
            deltaRender += (currentTime - startTime) / renderInterval; // liegt zwischen 0 und 1

            // Startzeit wird auf die aktuelle Zeit gesetzt
            startTime = currentTime;

            if (deltaTick >= 1) { // Wenn größer oder gleich 1, dann wird ein Tick ausgeführt
                doTick();
                deltaTick--;
                tickCounter++;
            }

            if (deltaRender >= 1) { // Wenn größer oder gleich 1, dann wird ein Frame gerendert
                doRender();
                deltaRender--;
                frameCounter++;
            }

            if (System.currentTimeMillis() - timer > 1000) { // Wenn 1000 ms vergangen sind, TPS und FPS ausgeben
                PlatformerGame.getInstance().debug("FPS: " + frameCounter + ", TPS: " + tickCounter);
                frameCounter = 0;
                tickCounter = 0;
                timer += 1000;
            }
        } while (running);
    }

    private void doTick() {
        activeState.tick();
    }

    private void doRender() {
        panel.repaint();
    }
}