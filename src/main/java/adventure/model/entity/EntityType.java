package adventure.model.entity;

import adventure.model.entity.implementation.type.GeneralEntityType;

public interface EntityType {
    
    int getId();
    
    String getName();

    GeneralEntityType getType();
}