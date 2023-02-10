package me.fearmyshotz.adventure.model.tile.type;

import me.fearmyshotz.adventure.util.Identifiable;

public enum GeneralTileType implements Identifiable {
    
    BACKGROUND(0, "Hintergrund"),
    INTERACTIVE(1, "Interaktiv"),

    ;

    private int id;
    private String name;

    GeneralTileType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}