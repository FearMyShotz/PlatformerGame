package platformer.tick;

import platformer.PlatformerGame;
import platformer.manager.Manager;

public class TickManager extends Manager {

    private final GameLoop gameLoop;

    public TickManager(PlatformerGame game) {
        super(game);

        this.gameLoop = new GameLoop(game.getWindowManager().getPanel());
    }

    public GameLoop getGameLoop() {
        return gameLoop;
    }
}