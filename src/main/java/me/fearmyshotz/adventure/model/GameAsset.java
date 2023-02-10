package me.fearmyshotz.adventure.model;

import org.jetbrains.annotations.Nullable;

import me.fearmyshotz.adventure.util.Identifiable;

public abstract class GameAsset implements Identifiable {
    
    protected @Nullable ResourceKey<? extends GameAsset> key;

    protected int id;
    
    protected String name;
    
    protected @Nullable String description;
    
    public GameAsset(int id, String name, @Nullable String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    
    @Override
    public int getId() {
        return id;
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    public @Nullable String getDescription() {
        return description;
    }

    public boolean hasDescription() {
        return description != null;
    }

    public void setKey(ResourceKey<? extends GameAsset> key) {
        this.key = key;
    }
    
    public ResourceKey<? extends GameAsset> getKey() {
        return key;
    }

}