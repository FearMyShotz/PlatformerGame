package me.fearmyshotz.adventure.model.spritesheet;

import me.fearmyshotz.adventure.util.Identifiable;

public enum SpritesheetType implements Identifiable {
    
    ENTITY(0, "Entity"),
    ITEM(1, "Item"),
    TILE(2, "Tile"),
    GUI(3, "GUI"),
    ;
    
    private int id;
    private String name;
    
    SpritesheetType(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    @Override
    public int getId() {
        return id;
    }
    
    @Override
    public String getName() {
        return name;
    }

}