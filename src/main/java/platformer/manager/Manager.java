package platformer.manager;

import platformer.PlatformerGame;
import platformer.util.Initializable;

public abstract class Manager implements Initializable<PlatformerGame> {
    
    protected final PlatformerGame game;

    public Manager(PlatformerGame game) {
        this.game = game;
        
        game.getManagers().add(this);
    }

    @Override
    public void initialize(PlatformerGame game) {
        game.log("Initializing " + getClass().getSimpleName() + "...");
    }

    public PlatformerGame getGame() {
        return game;
    }

    public boolean isInitialized() {
        return game.getManagers().contains(this);
    }
}