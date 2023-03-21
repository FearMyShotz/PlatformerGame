package platformer.model.state;

import platformer.PlatformerGame;
import platformer.input.keyboard.KeyAction;
import platformer.tick.Rendering;
import platformer.tick.Ticking;

public abstract class GameState implements Ticking, Rendering {
    
    protected final PlatformerGame game;

    public GameState(PlatformerGame game) {
        this.game = game;
    }

    public PlatformerGame getGame() {
        return game;
    }

    public abstract void handleKeyAction(KeyAction action, int keyCode);

}