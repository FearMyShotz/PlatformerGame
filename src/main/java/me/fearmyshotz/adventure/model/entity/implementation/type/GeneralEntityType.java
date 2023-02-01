package me.fearmyshotz.adventure.model.entity.implementation.type;

import me.fearmyshotz.adventure.util.Identifiable;

public enum GeneralEntityType implements Identifiable {
    
    PLAYER(0, "Spieler"),
    ENEMY(1, "Gegner"),
    
    ;

    private int id;
    private String name;

    GeneralEntityType(int id, String name) {
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