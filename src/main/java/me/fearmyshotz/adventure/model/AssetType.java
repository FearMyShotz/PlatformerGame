package me.fearmyshotz.adventure.model;

import me.fearmyshotz.adventure.util.Identifiable;

public enum AssetType implements Identifiable {

    ENTITY(0),

    ;

    private int id;
    private String name;

    AssetType(int id) {
        this.id = id;
        this.name = name().toLowerCase();
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