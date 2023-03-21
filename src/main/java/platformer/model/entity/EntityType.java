package platformer.model.entity;

import platformer.model.entity.implementation.type.GeneralEntityType;

/**
 * Eine Schnittstelle, die die grundlegenden Eigenschaften eines Entity-Typs beschreibt.
 * 
 * @author Jamil B.
 * @see GeneralEntityType
 */
public interface EntityType {
    
    int getId();
    
    String getName();

    GeneralEntityType getType();
}