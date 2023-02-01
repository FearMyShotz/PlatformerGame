package me.fearmyshotz.adventure.model.entity;

import me.fearmyshotz.adventure.model.entity.implementation.type.GeneralEntityType;

public interface EntityType {
    
    int getId();
    
    String getName();

    GeneralEntityType getType();
}