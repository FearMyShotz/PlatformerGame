package adventure.model.state;

import adventure.AdventureGame;
import adventure.input.keyboard.KeyAction;
import adventure.tick.Rendering;
import adventure.tick.Ticking;

public abstract class GameState implements Ticking, Rendering {
    
    protected final AdventureGame game;

    public GameState(AdventureGame game) {
        this.game = game;
    }

    public AdventureGame getGame() {
        return game;
    }

    public abstract void handleKeyAction(KeyAction action, int keyCode);

}