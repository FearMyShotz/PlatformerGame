package adventure.model.entity.implementation.type;

import adventure.model.ResourceKey;
import adventure.model.entity.Entity;
import adventure.model.entity.EntityType;
import adventure.util.Identifiable;

public enum EnemyType implements EntityType, Identifiable {
    
    SKELETON(50, "Skelett"),
    ARCHER(51, "Bogenschütze"),
    MAGE(52, "Magier"),

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
    public ResourceKey<Entity> getIdentifier() {
        return new ResourceKey.Builder<Entity>(Entity.NAMESPACE, name().toLowerCase()).build();
    }

    @Override
    public GeneralEntityType getType() {
        return GeneralEntityType.NPC;
    }

    
}