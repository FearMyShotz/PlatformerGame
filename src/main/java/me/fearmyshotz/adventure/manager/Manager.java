package me.fearmyshotz.adventure.manager;

import me.fearmyshotz.adventure.AdventureGame;

public abstract class Manager {
    
    private final AdventureGame game;

    public Manager(AdventureGame game) {
        this.game = game;
    }

    public AdventureGame getGame() {
        return game;
    }
}