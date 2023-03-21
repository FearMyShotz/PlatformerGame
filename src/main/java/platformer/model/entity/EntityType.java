package platformer.model.entity;

import platformer.model.entity.implementation.type.GeneralEntityType;

public interface EntityType {
    
    int getId();
    
    String getName();

    GeneralEntityType getType();
}