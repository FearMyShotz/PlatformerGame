package me.fearmyshotz.adventure.model.entity.implementation.type;

import me.fearmyshotz.adventure.model.entity.EntityType;
import me.fearmyshotz.adventure.util.Identifiable;

public enum PlayerType implements EntityType, Identifiable {
    
    NOVICE(0, "Anfänger"),
    WARRIOR(1, "Krieger"),
    ARCHER(2, "Bogenschütze"),
    MAGE(3, "Magier"),

    ;

    private int id;
    private String name;

    PlayerType(int id, String name) {
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

    @Override
    public GeneralEntityType getType() {
        return GeneralEntityType.PLAYER;
    }

}