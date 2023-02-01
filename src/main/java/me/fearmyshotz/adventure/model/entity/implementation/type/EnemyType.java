package me.fearmyshotz.adventure.model.entity.implementation.type;

import me.fearmyshotz.adventure.model.entity.EntityType;
import me.fearmyshotz.adventure.util.Identifiable;

public enum EnemyType implements EntityType, Identifiable {
    
    GOBLIN(0, "Kobold"),
    SKELETON(1, "Skelett"),

    ;

    private int id;
    private String name;

    EnemyType(int id, String name) {
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
        return GeneralEntityType.ENEMY;
    }
}