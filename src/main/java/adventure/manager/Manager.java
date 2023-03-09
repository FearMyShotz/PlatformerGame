package adventure.manager;

import adventure.AdventureGame;
import adventure.util.Initializable;

public abstract class Manager implements Initializable<AdventureGame> {
    
    protected final AdventureGame game;

    public Manager(AdventureGame game) {
        this.game = game;
        
        game.getManagers().add(this);
    }

    @Override
    public void initialize(AdventureGame game) {
        game.log("Initializing " + getClass().getSimpleName() + "...");
    }

    public AdventureGame getGame() {
        return game;
    }

    public boolean isInitialized() {
        return game.getManagers().contains(this);
    }
}