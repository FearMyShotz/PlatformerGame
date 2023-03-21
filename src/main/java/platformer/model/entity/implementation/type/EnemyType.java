package platformer.model.entity.implementation.type;

import platformer.model.ResourceKey;
import platformer.model.entity.Entity;
import platformer.model.entity.EntityType;
import platformer.util.Identifiable;

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