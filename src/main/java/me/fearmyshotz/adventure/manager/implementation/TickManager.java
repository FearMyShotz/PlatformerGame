package me.fearmyshotz.adventure.manager.implementation;

import org.jetbrains.annotations.NotNull;

import me.fearmyshotz.adventure.AdventureGame;
import me.fearmyshotz.adventure.manager.Manager;

public class TickManager extends Manager implements Runnable {

    private final @NotNull Thread gameThread;

    public TickManager(AdventureGame game) {
        super(game);

        this.gameThread = new Thread(this);
        this.gameThread.setName("Game Thread");
        this.gameThread.start();
    }

    @Override
    public void run() {
        double tickInterval = 1_000_000_000.0 / AdventureGame.FRAMES_PER_SECOND;
        double nextTick = System.nanoTime() + tickInterval;

        int fpsCounter = 0;

        while (gameThread != null) {
            // ! Tick logic here
            getGame().getWindowManager().getPanel().repaint();

            double remaining = nextTick - System.nanoTime();
            long remainingMillis = (long) (remaining / 1_000_000.0);

            if (remaining < 0) continue; // Wenn es laggt, nicht warten

            try {
                Thread.sleep(remainingMillis);
                fpsCounter++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            nextTick += tickInterval;

            if (fpsCounter >= AdventureGame.FRAMES_PER_SECOND) {
                fpsCounter = 0;
                System.out.println("FPS: " + AdventureGame.FRAMES_PER_SECOND);
            }
        }
    }

    public void start() {
        gameThread.start();
    }

    public Thread getGameThread() {
        return gameThread;
    }
}