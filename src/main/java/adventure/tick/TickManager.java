package adventure.tick;

import org.jetbrains.annotations.NotNull;

import adventure.AdventureGame;
import adventure.manager.Manager;
import adventure.window.GamePanel;

public class TickManager extends Manager implements Runnable {

    private final @NotNull Thread gameThread;

    private final GamePanel panel;

    private transient volatile boolean running;

    public TickManager(AdventureGame game) {
        super(game);

        this.running = false;

        this.panel = game.getWindowManager().getPanel();

        this.gameThread = new Thread(this);
        this.gameThread.setName("Game Thread");
        // this.gameThread.start();
    }

    @Override
    public void run() { // Delta-Methode
        // Intervalle für Ticks und Frames in Nanosekunden
        final double tickInterval = 1_000_000_000 / AdventureGame.TICKS_PER_SECOND;
        final double renderInterval = 1_000_000_000 / AdventureGame.FRAMES_PER_SECOND;

        // Delta-Werte für Ticks und Frames
        double deltaTick = 0, deltaRender = 0;

        // Anzahl der Ticks und Frames pro Sekunde
        int tickCounter = 0, frameCounter = 0;

        // Startzeit in Nanosekunden, Timer in Millisekunden zum Zählen der Ticks und Frames pro Sekunde
        long startTime = System.nanoTime(), timer = System.currentTimeMillis();

        while (gameThread != null && running) {
            // Aktuelle Zeit in Nanosekunden, wird für die Berechnung der Delta-Werte benötigt
            long currentTime = System.nanoTime();

            deltaTick += (currentTime - startTime) / tickInterval; // liegt zwischen 0 und 1
            deltaRender += (currentTime - startTime) / renderInterval; // liegt zwischen 0 und 1

            startTime = currentTime;

            if (deltaTick >= 1) { // Wenn größer oder gleich 1, dann wird ein Tick ausgeführt
                // ! Tick logic here
                // game.debug("Tick");
                doTick();
                tickCounter++;
                deltaTick--;
            }

            if (deltaRender >= 1) { // Wenn größer oder gleich 1, dann wird ein Frame gerendert
                // ! Render logic here
                // getGame().getWindowManager().getPanel().repaint();
                // game.debug("Render");
                doRender();
                frameCounter++;
                deltaRender--;
            }

            if (System.currentTimeMillis() - timer > 1000) {
                game.log("FPS: " + frameCounter + ", TPS: " + tickCounter);
                frameCounter = 0;
                tickCounter = 0;
                timer += 1000;
            }
        }
    }

    private void doTick() {
        game.getActiveState().tick();
    }

    private void doRender() {
        panel.repaint();
    }

    // @Override
    // public void run() {
    //     double tickInterval = 1_000_000_000.0 / AdventureGame.FRAMES_PER_SECOND;
    //     double nextTick = System.nanoTime() + tickInterval;

    //     int fpsCounter = 0;

    //     while (gameThread != null) {
    //         // ! Tick logic here
    //         getGame().getWindowManager().getPanel().repaint();

    //         double remaining = nextTick - System.nanoTime();
    //         long remainingMillis = (long) (remaining / 1_000_000.0);

    //         if (remaining < 0) continue; // Wenn es laggt, nicht warten

    //         try {
    //             Thread.sleep(remainingMillis);
    //             fpsCounter++;
    //         } catch (InterruptedException e) {
    //             e.printStackTrace();
    //         }
            
    //         nextTick += tickInterval;

    //         if (fpsCounter >= AdventureGame.FRAMES_PER_SECOND) {
    //             fpsCounter = 0;
    //             System.out.println("FPS: " + AdventureGame.FRAMES_PER_SECOND);
    //         }
    //     }
    // }

    public void start() {
        gameThread.start();
        running = true;
    }

    public void pause() {
        running = false;
    }

    public void resume() {
        running = true;
    }

    public Thread getGameThread() {
        return gameThread;
    }
}