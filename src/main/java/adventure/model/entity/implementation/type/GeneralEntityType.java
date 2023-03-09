package adventure.model.entity.implementation.type;

import java.util.stream.Stream;

import adventure.model.ResourceKey;
import adventure.model.entity.Entity;
import adventure.model.tile.type.TileType;
import adventure.util.Identifiable;

public enum GeneralEntityType implements Identifiable {
    
    PLAYER(0, "Spieler", "player"),
    NPC(1, "Nicht-Spieler-Charakter", "archer", "knight", "mage", "warrior"),
    ITEM(2, "Gegenstand"),
    TILE(3, "Tile", Stream.of(TileType.values()).map(Identifiable::getName).toArray(String[]::new)),
    
    ;

    private int id;
    private String name;

    private String[] validNames;

    GeneralEntityType(int id, String name, String... validNames) {
        this.id = id;
        this.name = name;

        this.validNames = validNames;
    }

    public String[] getValidNames() {
        return validNames;
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

    public static GeneralEntityType of(String name) {
        return Stream.of(values())
            .filter(type -> name.contains(type.name().toLowerCase()))
            .findFirst()
            .orElse(null);
    }

    public static GeneralEntityType of(Identifiable i) {
        for (GeneralEntityType type : values()) {
            for (String name : type.getValidNames()) {
                if (i.getIdentifier().getAccessor().contains(name)) return type;
            }
        }
        return null;
    }
}