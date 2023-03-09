package adventure.model.tile.type;

import adventure.model.ResourceKey;
import adventure.model.tile.Tile;
import adventure.util.Identifiable;

public enum GeneralTileType implements Identifiable {
    
    BACKGROUND(0, "Hintergrund"),
    INTERACTIVE(1, "Interaktiv"),

    ;

    private int id;
    private String name;

    GeneralTileType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public ResourceKey<Tile> getIdentifier() {
        return new ResourceKey.Builder<Tile>(Tile.NAMESPACE, name().toLowerCase()).build();
    }

}