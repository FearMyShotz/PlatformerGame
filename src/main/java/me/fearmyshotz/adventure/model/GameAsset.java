package me.fearmyshotz.adventure.model;

import org.jetbrains.annotations.Nullable;

public abstract class GameAsset {
    
    protected @Nullable ResourceKey<? extends GameAsset> key;

    protected int id;
    
    protected String name;
    
    protected String description;
    
    public GameAsset(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public String getDescription() {
        return description;
    }

    public void setKey(ResourceKey<? extends GameAsset> key) {
        this.key = key;
    }
    
    public ResourceKey<? extends GameAsset> getKey() {
        return key;
    }

}