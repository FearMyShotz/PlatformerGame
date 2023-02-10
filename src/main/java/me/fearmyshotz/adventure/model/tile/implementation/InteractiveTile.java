package me.fearmyshotz.adventure.model.tile.implementation;

import me.fearmyshotz.adventure.model.tile.Tile;

public abstract class InteractiveTile extends Tile {

    protected boolean interacted;

    public InteractiveTile(int id, String name, String description) {
        super(id, name, description);

        this.interacted = false;
    }
    
    public abstract void onInteract();
    
}