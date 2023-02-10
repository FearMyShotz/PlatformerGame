package me.fearmyshotz.adventure.manager.implementation;

import org.jetbrains.annotations.NotNull;

import me.fearmyshotz.adventure.AdventureGame;
import me.fearmyshotz.adventure.manager.Manager;

public class TickManager extends Manager implements Runnable {

    private final @NotNull Thread gameThread;

    private transient volatile boolean running;

    public TickManager(AdventureGame game) {
        super(game);

        this.running = true;

        this.gameThread = new Thread(this);
        this.gameThread.setName("Game Thread");
        this.gameThread.start();
    }

    @Override
    public void run() { // Delta-Methode
        long startTime = System.nanoTime();

        // Intervalle für Ticks und Frames in Nanosekunden
        final double tickInterval = 1_000_000_000 / AdventureGame.TICKS_PER_SECOND;
        final double renderInterval = 1_000_000_000 / AdventureGame.FRAMES_PER_SECOND;

        // Delta-Werte für Ticks und Frames
        double deltaTick = 0, deltaRender = 0;

        // Anzahl der Ticks und Frames pro Sekunde
        int tickCounter = 0, frameCounter = 0;

        // Timer zum Zählen der Ticks und Frames pro Sekunde
        long timer = System.currentTimeMillis();

        while (gameThread != null && running) {
            long currentTime = System.nanoTime();

            deltaTick += (currentTime - startTime) / tickInterval; // liegt zwischen 0 und 1
            deltaRender += (currentTime - startTime) / renderInterval; // liegt zwischen 0 und 1

            startTime = currentTime;

            if (deltaTick >= 1) { // Wenn größer oder gleich 1, dann wird ein Tick ausgeführt
                // ! Tick logic here
                tickCounter++;
                deltaTick--;
            }

            if (deltaRender >= 1) { // Wenn größer oder gleich 1, dann wird ein Frame gerendert
                // ! Render logic here
                // getGame().getWindowManager().getPanel().repaint();
                frameCounter++;
                deltaRender--;
            }

            if (System.currentTimeMillis() - timer > 1000) {
                game.debug("FPS: " + frameCounter + ", TPS: " + tickCounter);
                frameCounter = 0;
                tickCounter = 0;
                timer += 1000;
            }
        }
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
    }

    public Thread getGameThread() {
        return gameThread;
    }
}